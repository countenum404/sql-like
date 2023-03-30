package com.digdes.school;

public enum Operations {

    SELECT("SELECT"),
    INSERT_VALUES("INSERT VALUES"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String representation;

    Operations() {

    }

    Operations(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return this.representation;
    }
}
