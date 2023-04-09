package com.digdes.school.operations;

import com.digdes.school.ParserService;

import java.util.Arrays;
import java.util.Map;

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
                var is = switcher(operation, comparable.get(keyValue[0]), ParserService.getType(keyValue[1]));
                System.out.println(keyValue + " : " + is);
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
                return OperationFunctions.like((String) left, (String) right);
            }
            case ILIKE -> {
                return OperationFunctions.ilike((String) left, (String) right);
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

}
