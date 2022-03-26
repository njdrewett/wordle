package com.drewett.words.repository;

public class WordRepositoryException extends RuntimeException {

    private WordRepositoryException() {
        super();
    }

    public WordRepositoryException(final String message) {
        super(message);
    }

    public WordRepositoryException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
