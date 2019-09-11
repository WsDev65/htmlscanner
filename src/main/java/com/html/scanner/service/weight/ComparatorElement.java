package com.html.scanner.service.weight;

import java.util.Comparator;
import java.util.Map;
import java.util.function.ToIntFunction;

public class ComparatorElement implements Comparator<Map<String, String>> {


    private AttributeWeight attributesWeight;


    public ComparatorElement(AttributeWeight attributesWeight) {
        this.attributesWeight = attributesWeight;
    }

    /**
     * Compares its two arguments for equals by attributes.  Returns a sum weights of equal attributes<p>
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a positive integer value. This value it is a sum of a weight attributes.
     */
    @Override
    public int compare(Map<String, String> o1, Map<String, String> o2) {
        return o1
                .entrySet()
                .stream()
                .mapToInt(getElementsToSumWeight(o2))
                .sum();


    }

    private ToIntFunction<Map.Entry<String, String>> getElementsToSumWeight(Map<String, String> o2) {
        return nameValue -> {
            String name = nameValue.getKey().toLowerCase();
            String value = nameValue.getValue().toLowerCase();

            String o2Value = o2.get(name);
            int weight = 0;

            if (o2Value != null) {
                weight = attributesWeight.getWeight(name);
                if (value.equals(o2Value.toLowerCase())) weight += 10;
                else if (value.contains(o2Value)) weight += 5;
            }


            return weight;
        };
    }
}
