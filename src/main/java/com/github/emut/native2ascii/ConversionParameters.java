package com.github.emut.native2ascii;

import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

public class ConversionParameters {
    @Parameter
    List<FileParameters> fileParameters;
}
