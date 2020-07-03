package com.crud.library.book.controller;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(final String message) {
        super(message);
    }
}
