package com.html.scanner.util;

public enum DefaultAttributesWeightEnum {
    CLASS(10),
    ID(10),
    HREF(40),
    TITLE(10),
    REL(10),
    ONCLICK(10),
    TEXT(10);



    DefaultAttributesWeightEnum(Integer weight){
        this.weight = weight;
    }


    private Integer weight;

    public Integer getWeight() {
        return weight;
    }
}
