package com.github.emut.native2ascii;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)
public class Native2AsciiMojo extends AbstractMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hi!");
        for (FileParameters fileParameter : conversionParameters.fileParameters) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(fileParameter.inputFile));
            } catch (FileNotFoundException e) {
                getLog().error("Can not open file:" + fileParameter.inputFile);
                continue;
            }
            String outputFile = (fileParameter.outputFile == null ? fileParameter.inputFile : fileParameter.outputFile);
            StringBuilder sb = new StringBuilder();
            try {
                FileConverter.Convert(reader, sb, fileParameter);
            } catch (IOException e) {
                getLog().error("Could not convert file:" + fileParameter.inputFile);
            }
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(outputFile));
                writer.write(sb.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                getLog().error("Can not open file for writing:" + outputFile);
                continue;
            }

        }
    }

    @Parameter
    ConversionParameters conversionParameters;

}
