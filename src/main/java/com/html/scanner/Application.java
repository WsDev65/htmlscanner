package com.html.scanner;

import com.html.scanner.service.snippet.SnippetService;
import com.html.scanner.service.snippet.XmlSnippetService;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;





import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        Options options = new Options();

        Option id = new Option("id", "id", true, "id source file");
        id.setRequired(true);
        options.addOption(id);

        Option originalPath = new Option("op", "original-path", true, "original path to file");
        originalPath.setRequired(true);
        options.addOption(originalPath);
        Option snippetPath = new Option("sp", "snippet-path", true, "snippet path to file");
        snippetPath.setRequired(true);
        options.addOption(snippetPath);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String inputFilePath = cmd.getOptionValue("original-path");
        String outputFilePath = cmd.getOptionValue("snippet-path");
        String idValue = cmd.getOptionValue("id");

        SnippetService ss = new XmlSnippetService(inputFilePath, outputFilePath);

        ss.initialize();
        ss.setOriginalId(idValue);


        ss.getSimilarElement().ifPresent(s -> {
            System.out.printf("We found same element %s \n", s);
            System.exit(0);
        });

        System.out.println("We didn't find same element ");
    }
}
