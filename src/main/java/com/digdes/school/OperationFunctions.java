package com.digdes.school;

import java.util.regex.Pattern;

public class OperationFunctions {
    public static void throwUnsupportedTypeException(String classname, String operator) throws Exception {
        throw new Exception("Operator " + operator + " doesn't support type of " + classname);
    }

    public static boolean isEquals(Object first,
                                   Object second) {
        return first.equals(second);
    }

    public static boolean isNotEquals(Object first,
                                      Object second) {
        return !first.equals(second);
    }

    public static boolean like(String pattern,
                               String string) {
        string = string.replaceAll("'", "").trim();
        pattern = pattern.replaceAll("'", "").trim();
        if (pattern.startsWith("%") && pattern.endsWith("%")){
            return string.contains(pattern.replaceAll("%", ""));
        }
        if(pattern.startsWith("%")) {
            return string.endsWith(pattern.replace("%", ""));
        }
        if (pattern.endsWith("%")) {
            return string.startsWith(pattern.replace("%", ""));
        }
        return string.contains(pattern);
    }

    public static boolean ilike(String pattern,
                                String string) {
        return like(pattern.toUpperCase(), string.toUpperCase());
    }

    public static boolean moreOrEquals(Object first,
                                       Object second) throws Exception {
        if (!(first instanceof Long || first instanceof Double) &&
            !(second instanceof Long || second instanceof Double)) {
                throw new Exception("");
        }

        if (first instanceof Long && second instanceof Long) {
            return (Long) first >= (Long) second;
        }

        if (first instanceof Double && second instanceof Double) {
            return (Double) first >= (Double) second;
        }

        if (first instanceof Long && second instanceof Double) {
            return (Long) first >= (Double) second;
        }

        if (first instanceof Double && second instanceof Long) {
            return (Double) first >= (Long) second;
        }
        return false;
    }


    public static boolean lowerOrEquals(Object first,
                                        Object second) throws Exception {
        if (!(first instanceof Long || first instanceof Double) &&
                !(second instanceof Long || second instanceof Double)) {
            throw new Exception("");
        }

        if (first instanceof Long && second instanceof Long) {
            return (Long) first <= (Long) second;
        }

        if (first instanceof Double && second instanceof Double) {
            return (Double) first <= (Double) second;
        }

        if (first instanceof Long && second instanceof Double) {
            return (Long) first <= (Double) second;
        }

        if (first instanceof Double && second instanceof Long) {
            return (Double) first <= (Long) second;
        }
        return false;
    }


    public static boolean moreThan(Object first,
                                   Object second) throws Exception {
        if (!(first instanceof Long || first instanceof Double) &&
                !(second instanceof Long || second instanceof Double)) {
            throw new Exception("");
        }

        if (first instanceof Long && second instanceof Long) {
            return (Long) first > (Long) second;
        }

        if (first instanceof Double && second instanceof Double) {
            return (Double) first > (Double) second;
        }

        if (first instanceof Long && second instanceof Double) {
            return (Long) first > (Double) second;
        }

        if (first instanceof Double && second instanceof Long) {
            return (Double) first > (Long) second;
        }
        return false;
    }


    public static boolean lowerThan(Object first,
                                    Object second) throws Exception {
        if (!(first instanceof Long || first instanceof Double) &&
                !(second instanceof Long || second instanceof Double)) {
            throw new Exception("");
        }

        if (first instanceof Long && second instanceof Long) {
            return (Long) first < (Long) second;
        }

        if (first instanceof Double && second instanceof Double) {
            return (Double) first < (Double) second;
        }

        if (first instanceof Long && second instanceof Double) {
            return (Long) first < (Double) second;
        }

        if (first instanceof Double && second instanceof Long) {
            return (Double) first < (Long) second;
        }
        return false;
    }

}
