package com.digdes.school.operations;

import java.util.regex.Pattern;

public enum LogicalOperations {
    AND("AND"),
    OR("OR"),
    EQUALS("="),
    NOT_EQUALS("!="),
    LIKE("like"),
    ILIKE("ilike"),
    MORE_EQUALS(">="),
    LESS_EQUALS("<="),
    MORE(">"),
    LESS("<");

    public boolean isEqualTypes(Object first,
                                       Object second) {
        return first.getClass().equals(second.getClass());
    }

    public boolean isLongOrDouble(Object first,
                          Object second) {
        return (first instanceof Long || first instanceof Double)
                &&
               (second instanceof Long || second instanceof Double);
    }

    private String representation;

    LogicalOperations(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
