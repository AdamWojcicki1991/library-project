package com.crud.library.borrow.controller;

public class ProcessCanNotBeExecutedException extends Exception {
    public ProcessCanNotBeExecutedException(final String message) {
        super(message);
    }
}
