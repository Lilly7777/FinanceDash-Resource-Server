package com.financedash.resourceserver.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(String message) {
        super("Category with ID: " + message + " doesn't exist");
    }
}
