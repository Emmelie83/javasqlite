package com.emmeliejohansson.data.enums;

public enum CustomerEnums {
    isVip("Yes"),
    isNotVip("");

    public final String label;

    private CustomerEnums(String label) {
        this.label = label;
    }
}
