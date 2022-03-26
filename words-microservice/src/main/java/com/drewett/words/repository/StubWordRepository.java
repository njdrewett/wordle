package com.drewett.words.repository;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StubWordRepository implements WordRepository {

    private final List<String> words;

    public StubWordRepository() {
        words = new ArrayList<>();
        initialiseStore();
    }
    
    public void initialiseStore() {
        words.add("words");
        words.add("abbey");
        words.add("slept");
        words.add("drawn");
        words.add("petty");
        words.add("solar");
    }


    @Override
    public String retrieveWordByIndex(final int index) {
        log.debug("retrieveWordByIndex {}", index);

        String value = words.get(index);

        log.debug("retrieveWordByIndex Return {}", value);

        return value;
    }

    @Override
    public boolean wordExists(final String word) {
        log.debug("wordExists {}", word);

        boolean wordExists = words.contains(word);

        log.debug("wordExists {}", wordExists);

        return wordExists;
    }

    @Override
    public List<String> retrieveAllWords() {
        return words;
    }

    @Override
    public int retrieveNumberOfWords() {
        log.debug("retrieveNumberOfWords ");

        int numberOfWords = retrieveAllWords().size();

        log.debug("retrieveNumberOfWords Result {} ", numberOfWords);

        return numberOfWords;
    }

}
