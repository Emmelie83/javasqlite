package com.emmeliejohansson.data.enums;

public enum CustomerEnums {
    isVip("Yes"),
    isNotVip("");

    public final String label;

    CustomerEnums(String label) {
        this.label = label;
    }
}
