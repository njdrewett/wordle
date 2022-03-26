package com.drewett.words.repository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CachedWordRepository implements WordRepository {

    private List<String> words;

    public CachedWordRepository(final WordRepository wordRepository) {
        super();
        words = wordRepository.retrieveAllWords();
    }

    @Override
    public String retrieveWordByIndex(int index) {
        log.debug("retrieveWordByIndex {} ", index );

        final String word = words.get(index);

        log.debug("retrieveWordByIndex return {} ", word);
        return word;
    }

    @Override
    public boolean wordExists(String word) {
        log.debug("wordExists {} ", word );

        final boolean wordExists = words.contains(word);

        log.debug("wordExists Response {} ", word );

        return wordExists;
    }

    @Override
    public List<String> retrieveAllWords() {
        return words;
    }

    @Override
    public int retrieveNumberOfWords() {
        return words.size();
    }
}
