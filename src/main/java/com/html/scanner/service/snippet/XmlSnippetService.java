package com.html.scanner.service.snippet;

import com.html.scanner.service.weight.AttributeWeight;
import com.html.scanner.service.weight.ComparatorElement;
import com.html.scanner.util.JsoupSnippet;

import com.html.scanner.util.Pair;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.Comparator;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class XmlSnippetService implements SnippetService {

    private Logger log = Logger.getLogger(XmlSnippetService.class.getName());


    private String pathOriginalFile;
    private String pathOfScannerFile;

    private File originFile;
    private File scannerFile;

    private String id;

    private JsoupSnippet idSnippet = new JsoupSnippet();

    public XmlSnippetService(String pathOriginalFile, String pathOfScannerFile) {
        this.pathOriginalFile = pathOriginalFile;
        this.pathOfScannerFile = pathOfScannerFile;
    }

    @Override
    public void initialize() {
        originFile = new File(pathOriginalFile);
        scannerFile = new File(pathOfScannerFile);
    }

    @Override
    public void setOriginalId(String id) {
        this.id = id;
    }

    @Override
    public Optional<String> getSimilarElement()  {
        Optional<Element> elementOptional = idSnippet.findElementById(originFile, id);

        if (!elementOptional.isPresent())
            throw new IllegalArgumentException(String.format("Not found element by id %s", id));

        Element originalElement = elementOptional.get();

        Comparator<Map<String, String>> comparatorElement = new ComparatorElement(new AttributeWeight(originalElement.attributes()));


        Map<String, String> originalAttributes = originalElement
                .attributes()
                .asList()
                .stream()
                .map(attribute -> new Pair<>(attribute.getKey(), attribute.getValue()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        Optional<Document> doc = idSnippet.getDocument(scannerFile);

        Integer weight = 0;
        String elementString = null;

        if (doc.isPresent()) {
            for (Element element : doc.get().getAllElements()) {
                Integer newWeight = comparatorElement.compare(originalAttributes,  element.attributes()
                        .asList()
                        .stream()
                        .map(attribute -> new Pair<>(attribute.getKey(), attribute.getValue()))
                        .collect(Collectors.toMap(Pair::getKey, Pair::getValue)));

                if (newWeight > weight) {
                    weight = newWeight;
                    elementString = element.toString();
                }
            }

            log.log(Level.INFO, String.format("Weight : %s \t OuterHtml : %s", weight, elementString));

            return Optional.ofNullable(elementString);
        }


        log.warning("Something went wrong");
        return Optional.empty();
    }


}
