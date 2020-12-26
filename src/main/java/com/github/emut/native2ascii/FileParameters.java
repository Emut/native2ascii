package com.github.emut.native2ascii;

import org.apache.maven.plugins.annotations.Parameter;

public class FileParameters {
    @Parameter
    String inputFile;
    @Parameter
    String outputFile;
    @Parameter
    String commentPrefix = "#";
    @Parameter
    String commentPostfix = "";
    @Parameter
    boolean commentOriginal = true;

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getCommentPrefix() {
        return commentPrefix;
    }

    public void setCommentPrefix(String commentPrefix) {
        this.commentPrefix = commentPrefix;
    }

    public String getCommentPostfix() {
        return commentPostfix;
    }

    public void setCommentPostfix(String commentPostfix) {
        this.commentPostfix = commentPostfix;
    }
}
