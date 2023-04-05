package com.digdes.school;

import com.digdes.school.operations.OperationKeywords;
import com.digdes.school.operations.OperationNotFoundException;
import com.digdes.school.query.Query;

import java.util.*;

public class ParserService {
    private String query;
    private String operation;
    private String[] splitQuery;

    private boolean isValues, isWhere;
    public Query getQuery(String query) throws OperationNotFoundException {
        this.query = query;
        this.splitQuery = query.split(" ");
        this.operation = splitQuery[0].toUpperCase();
        isValues = isValuesKeyword(this.query);
        isWhere = isWhere();
        return Query
                .builder()
                .setOperation(getOperation().orElseThrow(OperationNotFoundException::new))
                .setIsValues(isValues)
                .setValues(isValues ? parseValues() : null)
                .setWhere(isWhere)
                .setWhereValues(isWhere ? parseWhere() : null)
                .build();
    }

    private List<Map<String, Object>> parseWhere() {
        return null;
    }

    private Optional<OperationKeywords> getOperation() {
        return Optional.of(OperationKeywords.valueOf(operation));
    }

    private boolean isValuesKeyword(String query) {
        return query.contains("values") || query.contains("VALUES");
    }

    private boolean isWhere() {
        return query.contains("WHERE") || query.contains("where");
    }

    private Map<String, Object> parseValues() {
        Map<String, Object> values = new Hashtable<>();
        String q = query.toUpperCase();
        String stringValues;
        if (q.contains("WHERE")) {
            stringValues = query.substring(query.indexOf("VALUES") + "VALUES".length(), q.indexOf("WHERE"));
        }
        else {
            stringValues = query.substring(query.indexOf("VALUES") + "VALUES".length(), q.length());
        }
        Arrays.stream(stringValues.split("(,|, | , | ,)")).forEach(i -> {
            i = i.trim();
            var pair = i.split("=");
            values.put(pair[0].trim(), getType(pair[1].trim()));
        });
        return values;
    }

    public Map<String, Object> getConditions() {
        Map<String, Object> conditions = new Hashtable<>();

        return conditions;
    }

    public static Object getType(String atom) {

        if (atom.equals("true") || atom.equals("false")) {
            return Boolean.getBoolean(atom);
        }
        if (atom.matches("(-?\\d+\\.\\d+)")) {
            return Double.parseDouble(atom);
        }
        if (atom.matches("(-?[\\d]+)")) {
            return Long.parseLong(atom);
        }
        if (atom.matches("('.+')")) {
            return atom;
        }
        return null;
    }

}
