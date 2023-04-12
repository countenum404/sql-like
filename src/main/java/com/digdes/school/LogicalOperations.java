package com.digdes.school;


public enum LogicalOperations {
    AND("AND"),
    OR("OR"),
    EQUALS("="),
    MORE(">"),
    LESS("<"),
    MORE_EQUALS(">="),
    LESS_EQUALS("<="),
    NOT_EQUALS("!="),
    LIKE("like"),
    ILIKE("ilike");

    private String representation;

    LogicalOperations(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
