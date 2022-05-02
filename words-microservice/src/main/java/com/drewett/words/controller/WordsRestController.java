package com.drewett.words.controller;

import com.drewett.words.service.WordsService;
import com.drewett.words.service.WordsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/words")
public class WordsRestController {
    private final WordsService wordsService;

    @Autowired
    WordsRestController(final WordsService wordsService) {
        super();
        this.wordsService = wordsService;
    }

    @GetMapping(path="/random/{numberOfLetters}")
    public @ResponseBody ResponseEntity<String> retrieveRandomWord(@PathVariable("numberOfLetters") int numberOfLetters) {
        log.info("retrieveRandomWord {} ", numberOfLetters);

        final String word = wordsService.retrieveRandomWord(numberOfLetters);

        log.info("retrieveRandomWord Response {}" , numberOfLetters);

        return new ResponseEntity<>(word, HttpStatus.OK);
    }

    @GetMapping(path="/isAWord/{word}")
    public @ResponseBody ResponseEntity<Boolean> isAWord(@PathVariable("word") String word) {
        log.info("isAWord {} ", word);

        final boolean isAWord = wordsService.isAWord(word);

        log.info("isAWord Response {}" , isAWord);

        return new ResponseEntity<>(isAWord, HttpStatus.OK);
    }

}
