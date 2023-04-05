package com.digdes.school.operations;

import java.util.Map;

public class BooleanExecutor {

    private Map<String, Object> comparable;

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
            //System.out.println("comparing " + tree.getKey() + "=" + comparable.get(tree.getKey()).getClass() + " isEquals " + tree.getValue().getClass());
            var is = switcher((LogicalOperations) tree.getData(),
                                                comparable.get(tree.getKey()),
                                                tree.getValue());
            //System.out.println(is);
            return is;
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
