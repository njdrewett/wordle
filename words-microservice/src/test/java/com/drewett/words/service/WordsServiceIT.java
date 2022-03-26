package com.drewett.words.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Word Repository Test
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WordsServiceIT {

    private WordsService wordsService;

    @Autowired
    WordsServiceIT(final WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @BeforeEach
    public void setup() {

    }

    @Test
    public void retrieveRandomWord() {
        // cannot accurately test randomness. Shouldn't be a test at this level
        final String randomWord = wordsService.retrieveRandomWord(5);

        // but should never be null and should be same length
        assert randomWord != null;
        assert randomWord.length() == 5;

    }

    @Test
    public void isAWordTrue() {
        final boolean isAWord = wordsService.isAWord("Abbey");

        assert true == isAWord;
    }

    @Test
    public void isAWordFalse() {
        final boolean isAWord = wordsService.isAWord("dsads");

        assert false == isAWord;
    }


}
