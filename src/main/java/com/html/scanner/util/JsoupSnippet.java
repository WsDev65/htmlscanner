package com.html.scanner.util;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class JsoupSnippet {

    private Logger log = Logger.getLogger(JsoupSnippet.class.getName());
    private static final String CHARSET_NAME = "utf8";


    public Optional<Element> findElementById(File htmlFile, String targetElementId) {
        Optional<Document> doc = getDocument(htmlFile);
        return doc.map(document -> document.getElementById(targetElementId));
    }

    public Optional<Document> getDocument(File htmlFile) {
        try {
            return Optional.of(Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath()));
        } catch (IOException e) {
            log.log(Level.SEVERE, String.format("Error reading [%s] file %s", htmlFile.getAbsolutePath(), e.toString()));
            return Optional.empty();
        }

    }


}