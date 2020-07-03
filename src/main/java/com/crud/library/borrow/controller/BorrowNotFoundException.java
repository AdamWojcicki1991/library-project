package com.crud.library.borrow.controller;

public class BorrowNotFoundException extends Exception {
    public BorrowNotFoundException(final String message) {
        super(message);
    }
}
