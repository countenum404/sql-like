package com.digdes.school.operations;

public class OperationNotFoundException extends Exception {
    public OperationNotFoundException() {
        super("Operation not found");
    }
}
