package com.digdes.school;

import java.util.*;

public class ParserService {
    private String query;
    private String operation;
    private String[] splitQuery;

    private boolean isValues, isWhere;
    public Query getQuery(String query) throws Exception {
        this.query = query;
        this.splitQuery = query.split(" ");
        this.operation = splitQuery[0].toUpperCase();
        isValues = isValuesKeyword(this.query);
        isWhere = isWhere();
        return Query
                .builder()
                .setOperation(getOperation().orElseThrow(() -> new Exception("Operation Not Found")))
                .setIsValues(isValues)
                .setValues(isValues ? parseValues() : null)
                .setWhere(isWhere)
                .setWhereValues(isWhere ? parseWhereRoot() : null)
                .build();
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
        String stringValues;
        if (query.toUpperCase().contains("WHERE")) {
            stringValues = query.substring(query.toUpperCase().indexOf("VALUES") + "VALUES".length(), query.toUpperCase().indexOf("WHERE")).trim();
        }
        else {
            stringValues = query.substring(query.toUpperCase().indexOf("VALUES") + "VALUES".length(), query.toUpperCase().length()).trim();
        }
        Arrays.stream(stringValues.split("(,|, | , | ,)")).forEach(i -> {
            i = i.trim();
            var pair = i.split( "=");
            values.put(pair[0].toUpperCase().trim(), translateType(pair[1].trim()));
        });
        return values;
    }

    private BooleanTree parseWhereRoot() {
        String valuesSubstring = query.substring(query.toUpperCase().indexOf("WHERE") + "WHERE".length(),
                                                 query.length()).trim();
        BooleanTree parsedOrTree = parseByOr(valuesSubstring);
        BooleanTree parsedAndTree = walkAroundTreeForAnd(parsedOrTree);
        return parsedAndTree;
    }

    private BooleanTree walkAroundTreeForAnd(BooleanTree tree) {
        if (tree.getData() == LogicalOperations.OR) {
            tree.setLeft(walkAroundTreeForAnd(tree.getLeft()));
            tree.setRight(walkAroundTreeForAnd(tree.getRight()));
        }
        if (tree.getData() instanceof String) {
            return parseByAnd((String) tree.getData());
        }
        return tree;
    }

    private BooleanTree parseByOr(String query) {
        BooleanTree booleanTree = new BooleanTree();
        booleanTree.setRight(new BooleanTree());
        booleanTree.setLeft(new BooleanTree());
        var split = query.split(" OR | or ");
        if (split.length == 1) {
            booleanTree.setData(query);
            return booleanTree;
        }
        for (var expression : split) {
            if (booleanTree.getData() == null
                    && booleanTree.getLeft().getData() == null
                    && booleanTree.getRight().getData() == null) {
                booleanTree.setData(LogicalOperations.OR);
                booleanTree.getLeft().setData(expression);
            } else if (booleanTree.getRight().getData() == null
                    && booleanTree.getLeft().getData() != null
                    && booleanTree.getData() != null) {
                booleanTree.getRight().setData(expression);
            } else if (booleanTree.getData() != null
                    && booleanTree.getLeft().getData() != null
                    && booleanTree.getRight().getData() != null) {
                var newVertex = new BooleanTree(LogicalOperations.OR);
                newVertex.setLeft(booleanTree);
                newVertex.setRight(new BooleanTree(expression));
                booleanTree = newVertex;
            }
        }
        return booleanTree;
    }

    private BooleanTree parseByAnd(String query) {
        BooleanTree booleanTree = new BooleanTree();
        booleanTree.setRight(new BooleanTree());
        booleanTree.setLeft(new BooleanTree());
        var split = query.split(" AND | and ");
        if (split.length == 1) {
            booleanTree.setData(query);
            return booleanTree;
        }
        for (var expression : split) {
            if (booleanTree.getData() == null
                    && booleanTree.getLeft().getData() == null
                    && booleanTree.getRight().getData() == null) {
                booleanTree.setData(LogicalOperations.AND);
                booleanTree.getLeft().setData(expression);
            } else if (booleanTree.getRight().getData() == null
                    && booleanTree.getLeft().getData() != null
                    && booleanTree.getData() != null) {
                booleanTree.getRight().setData(expression);
            } else if (booleanTree.getData() != null
                    && booleanTree.getLeft().getData() != null
                    && booleanTree.getRight().getData() != null) {
                var newVertex = new BooleanTree(LogicalOperations.AND);
                newVertex.setLeft(booleanTree);
                newVertex.setRight(new BooleanTree(expression));
                booleanTree = newVertex;
            }
        }
        return booleanTree;
    }

    public static Object translateType(String atom) {
        if (atom.trim().equals("true")) {
            return true;
        }
        if (atom.trim().equals("false")) {
            return false;
        }
        if (atom.trim().matches("(-?\\d+\\.\\d+)")) {
            return Double.parseDouble(atom);
        }
        if (atom.trim().matches("(-?[\\d]+)")) {
            return Long.parseLong(atom);
        }
        if (atom.trim().matches("('.+')")) {
            return atom;
        }
        if (atom.trim().equals("null")) {
            return "null";
        }
        return null;
    }
}
