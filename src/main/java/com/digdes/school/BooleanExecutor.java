package com.digdes.school;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class BooleanExecutor {

    private Map<String, Object> comparable;
    private LogicalOperations operation;

    public void setComparable(Map<String, Object> comparable) {
        this.comparable = comparable;
    }

    public boolean execute(BooleanTree tree) throws Exception {
        if (tree.getData().equals(LogicalOperations.AND)) {
            var left = execute(tree.getLeft());
            var right = execute(tree.getRight());
            return left && right;
        }
        else if (tree.getData().equals(LogicalOperations.OR)) {
            var left = execute(tree.getLeft());
            var right = execute(tree.getRight());
            return left || right;
        }
        else {
            if (tree.getData() instanceof String) {
                String string = (String) tree.getData();
                Arrays.stream(LogicalOperations.values())
                        .forEach(op -> {
                            if (string.contains(op.toString())){
                                operation = op;
                            }
                        });
                var keyValue = string.split(operation.toString());
                var mapValue = Optional.ofNullable(comparable.get(keyValue[0].trim()))
                        .orElseThrow(() -> new Exception("Column " + keyValue[0].trim() + " is absent"));
                var queryValue = ParserService.translateType(keyValue[1].trim());
                this.typesCheck(operation, mapValue, queryValue);
                var is = switcher(operation, mapValue, queryValue);
                return is;
            }
            return false;
        }
    }

    public boolean switcher(LogicalOperations operation, Object left, Object right) throws Exception {
        switch (operation) {
            case EQUALS -> {
                return OperationFunctions.isEquals(left, right);
            }
            case NOT_EQUALS -> {
                return OperationFunctions.isNotEquals(left, right);
            }
            case LIKE -> {
                return OperationFunctions.like((String) right, (String) left);
            }
            case ILIKE -> {
                return OperationFunctions.ilike((String) right, (String) left);
            }
            case MORE_EQUALS -> {
                return OperationFunctions.moreOrEquals(left, right);
            }
            case LESS_EQUALS -> {
                return OperationFunctions.lowerOrEquals(left, right);
            }
            case MORE -> {
                return OperationFunctions.moreThan(left, right);
            }
            case LESS -> {
                return OperationFunctions.lowerThan(left, right);
            }
        }
        return false;
    }

    private void typesCheck(LogicalOperations operation, Object left, Object right) throws Exception {
        switch (operation) {
            case LIKE, ILIKE -> {
                if (!(left instanceof String)){
                    OperationFunctions.throwUnsupportedTypeException(left.getClass().getTypeName(), operation.toString());
                }
                if (!(right instanceof String)){
                    OperationFunctions.throwUnsupportedTypeException(right.getClass().getTypeName(), operation.toString());
                }
            }
            case LESS, LESS_EQUALS, MORE, MORE_EQUALS -> {
                if (!(left instanceof Long || left instanceof Double)) {
                    OperationFunctions.throwUnsupportedTypeException(left.getClass().getTypeName(), operation.toString());
                }
                if (!(right instanceof Long || right instanceof Double)) {
                    OperationFunctions.throwUnsupportedTypeException(right.getClass().getTypeName(), operation.toString());
                }
            }
        }
    }
}
