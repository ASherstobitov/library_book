package com.aleksei.library_book.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message) {

        super(message);
    }
}
