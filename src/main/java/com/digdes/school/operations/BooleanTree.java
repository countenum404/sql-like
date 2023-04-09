package com.digdes.school.operations;

import java.util.Map;

public class BooleanTree {
    private BooleanTree left;
    private BooleanTree right;
    private Object data;

    private String key;

    private Object value;

    public BooleanTree getLeft() {
        return left;
    }

    public void setLeft(BooleanTree left) {
        this.left = left;
    }

    public BooleanTree getRight() {
        return right;
    }

    public void setRight(BooleanTree right) {
        this.right = right;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public BooleanTree(BooleanTree left, BooleanTree right, LogicalOperations operation) {
        this.left = left;
        this.right = right;
        this.data = operation;
    }

    public BooleanTree(String data) {
        this.data = data;
    }

    public BooleanTree(LogicalOperations operation) {
        this.data = operation;
    }

    public BooleanTree() {

    }
}
