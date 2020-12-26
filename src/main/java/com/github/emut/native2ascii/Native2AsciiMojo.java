package com.github.emut.native2ascii;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)
public class Native2AsciiMojo extends AbstractMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        for (FileParameters fileParameter : conversionParameters.fileParameters) {
            try {
                FileConverter.convert(fileParameter);
            } catch (IOException e) {
                getLog().error("Could not convert file:" + fileParameter.inputFile);
            }
        }
    }

    @Parameter
    ConversionParameters conversionParameters;

}
