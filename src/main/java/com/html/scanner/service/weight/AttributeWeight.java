package com.html.scanner.service.weight;

import com.html.scanner.util.DefaultAttributesWeightEnum;
import com.html.scanner.util.Pair;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AttributeWeight {

    private static final Logger log = Logger.getLogger(AttributeWeight.class.getName());

    private Map<String, Integer> attributesWeight;


    public AttributeWeight(Attributes attributes) {
        attributesWeight = attributes
                .asList()
                .stream()
                .map(Attribute::getKey)
                .map(attributeToAttributeWeightPair())
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }


    /**
     *
     * @param attributeName attribute name,
     * @return weight for attribute, if attribute not present, return default value 5
     */
    public  Integer getWeight(String attributeName){
        return attributesWeight.getOrDefault(attributeName, 5);
    }

    private Function<String, Pair<String, Integer>> attributeToAttributeWeightPair() {
        return attribute -> {
            try {
                return new Pair<>(attribute, DefaultAttributesWeightEnum.valueOf(attribute.toUpperCase()).getWeight());
            } catch (IllegalArgumentException e) {
                log.warning( String.format("Weight for attribute %s , not found, return default ", attribute));
                return new Pair<>(attribute, 10);
            }
        };
    }
}
