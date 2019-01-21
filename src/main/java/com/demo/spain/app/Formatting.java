package com.demo.spain.app;

public enum Formatting {
    TYPE_1(","), TYPE_2(" ; ");

    private String separator;

    Formatting(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return this.separator;
    }

}
