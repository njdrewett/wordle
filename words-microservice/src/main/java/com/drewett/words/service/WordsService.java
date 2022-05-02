package com.drewett.words.service;

public interface WordsService {

     String retrieveRandomWord(int numberOfLetters);

     boolean isAWord(final String word);
}
