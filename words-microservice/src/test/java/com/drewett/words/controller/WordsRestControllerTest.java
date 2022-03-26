package com.drewett.words.controller;

import com.drewett.words.service.WordsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class WordsRestControllerTest {

    private WordsRestController wordsRestController;

    @Mock
    private WordsService wordsService;

    @BeforeEach
    public void setup() {
        wordsRestController = new WordsRestController(wordsService);
    }

    @Test
    public void TestGet5LetterWord() {
        Mockito.when(wordsService.retrieveRandomWord(5)).thenReturn("Hello");

        ResponseEntity<String> word = wordsRestController.retrieveRandomWord(5);

        assert word.getStatusCode().value() == HttpStatus.OK.value();
        assert word.getBody().length() == 5;
        assert "Hello".equals(word.getBody());
    }

    @Test
    public void TestGet6LetterWord() {
        Mockito.when(wordsService.retrieveRandomWord(6)).thenReturn("Hellos");

        ResponseEntity<String> word = wordsRestController.retrieveRandomWord(6);

        assert word.getStatusCode().value() == HttpStatus.OK.value();
        assert word.getBody().length() == 6;
    }

    @Test
    public void TestIsAWord() {
        Mockito.when(wordsService.isAWord("word")).thenReturn(true);

        ResponseEntity<Boolean> isAWord = wordsRestController.isAWord("word");

        assert isAWord.getStatusCode().value() == HttpStatus.OK.value();
        assert Boolean.TRUE.equals(isAWord.getBody());
    }


}
