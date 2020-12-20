package com.github.emut.native2ascii;

import java.io.*;


public class FileConverter {
    static void Convert(BufferedReader inputFile, StringBuilder sb, FileParameters fp) throws IOException {
        while (true) {
            String line = inputFile.readLine();
            if (line == null)
                break;
            if(line.startsWith(fp.commentPrefix)) {
                sb.append(line);
                continue;   //ignore commented lines
            }
            char[] lineChars = line.toCharArray();
            boolean hasConvertedChar = false;
            StringBuilder sbline = new StringBuilder();
            for (int i = 0; i < lineChars.length; ++i) {
                int codePoint = Character.codePointAt(lineChars, i);
                if (codePoint < 128) {
                    sbline.append(lineChars[i]);
                } else {
                    hasConvertedChar = true;
                    String temp = "\\u" + String.format("%04x", codePoint);
                    sbline.append(temp);
                }
                i += Character.charCount(codePoint) - 1;
            }
            sbline.append('\n');
            if (hasConvertedChar) {
                //insert original line as a comment on top of new line
                sb.append(fp.commentPrefix);
                sb.append(line);
                sb.append(fp.commentPostfix);
                sb.append('\n');
            }
            sb.append(sbline.toString());
        }
    }
}
