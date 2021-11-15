package com.products.demo;

public enum Category {
    ALL("Wszystkie", "ALL", "wszystkie"),
    GROCERIES("Artykuły spożywcze", "GROCERIES", "spozywcze"),
    HOUSEHOLD_ITEMS("Artykuły gosp. dom.", "HOUSEHOLD_ITEMS", "domowe"),
    ANOTHER("Inne", "ANOTHER", "inne");


    private String polishTranslation;
    private String original;
    private String linkName;


    Category(String polishTranslation, String original, String linkName) {
        this.polishTranslation = polishTranslation;
        this.original = original;
        this.linkName = linkName;
    }

    @Override
    public String toString() {
        return polishTranslation;
    }

    public String getLinkName() {
        return linkName;
    }

    public String getOriginal() {
        return original;
    }
}
