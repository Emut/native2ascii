package com.github.emut.native2ascii;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;


public class FileConverter {

    private static final String CONVERSION_PREFIX = "[n2a]";

    public static void convert(FileParameters fp) throws IOException {
        List<String> lines = FileUtils.readLines(new File(fp.inputFile), Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();
        String commentAndConversionPrefix = fp.commentPrefix + CONVERSION_PREFIX;
        for (int lineNumber = 0; lineNumber < lines.size(); ++lineNumber) {
            String line = lines.get(lineNumber);
            if (line.startsWith(commentAndConversionPrefix)) {
                String convertedComment = convert(line.substring(commentAndConversionPrefix.length()));
                if (lineNumber >= lines.size() - 1 ||
                        !convertedComment.equals(lines.get(lineNumber + 1))) {
                    //comment does not match converted version
                    sb.append(line).append('\n');
                    sb.append(convertedComment).append('\n');
                } else {
                    //comment and its converted version are the same
                    sb.append(line).append('\n');
                    sb.append(lines.get(lineNumber + 1)).append('\n');
                }
                //skip next line
                ++lineNumber;
                continue;
            }
            if (line.startsWith(fp.commentPrefix)) {
                sb.append(line);
                sb.append('\n');
                continue;   //ignore commented lines
            }
            String converted = convert(line);
            if (converted != line) {
                //insert original line as a comment on top of new line
                sb.append(fp.commentPrefix);
                sb.append(CONVERSION_PREFIX);
                sb.append(line);
                sb.append(fp.commentPostfix);
                sb.append('\n');
            }
            sb.append(converted);
            sb.append('\n');
        }
        String outputFile = fp.outputFile != null ? fp.outputFile : fp.inputFile;
        FileUtils.write(new File(outputFile), sb.toString(), Charset.defaultCharset());
    }

    private static String convert(String input) {
        char[] lineChars = input.toCharArray();
        boolean hasConvertedChar = false;
        StringBuilder sbline = new StringBuilder();
        for (int i = 0; i < lineChars.length; ++i) {
            int codePoint = Character.codePointAt(lineChars, i);
            if (codePoint < 128) {
                sbline.append(lineChars[i]);
            } else {
                hasConvertedChar = true;
                char[] chars = Character.toChars(codePoint);
                for (char c : chars) {
                    sbline.append("\\u").append(String.format("%04X", (int) c));
                }
            }
            //skip forward if char is multi byte
            i += Character.charCount(codePoint) - 1;
        }
        if (hasConvertedChar)
            return sbline.toString();
        return input;
    }

    private FileConverter() {
    }
}
