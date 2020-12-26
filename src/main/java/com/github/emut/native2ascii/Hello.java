package com.github.emut.native2ascii;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.util.ArrayList;

public class Hello {
    public static void main(String[] args){
        ConversionParameters cp = new ConversionParameters();
        FileParameters fp = new FileParameters();
//        fp.inputFile = "in.properties";
        fp.inputFile = "out.properties";
        fp.outputFile = "out.properties";
        ArrayList<FileParameters> alfp = new ArrayList<FileParameters>();
        alfp.add(fp);
        cp.fileParameters = alfp;

        Native2AsciiMojo native2AsciiMojo = new Native2AsciiMojo();
        native2AsciiMojo.conversionParameters = cp;
        try {
            native2AsciiMojo.execute();
        } catch (MojoExecutionException e) {
            e.printStackTrace();
        } catch (MojoFailureException e) {
            e.printStackTrace();
        }

    }
}
