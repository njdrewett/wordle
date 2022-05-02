package com.drewett.words.service;

import com.drewett.words.repository.WordRepository;
import com.drewett.words.repository.WordRepositoryCacheFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WordsServiceImpl implements WordsService {

    private WordRepositoryCacheFactory wordRepositoryCacheFactory;

    @Autowired
    public WordsServiceImpl(final WordRepositoryCacheFactory wordRepositoryCacheFactory) {
        this.wordRepositoryCacheFactory = wordRepositoryCacheFactory;
    }


    /**
     * Retrieve a random word and return it to the caller
     * @param numberOfLetters for the word
     * @return the Random Word
     */
    public String retrieveRandomWord(int numberOfLetters) {
        log.debug("retrieveRandomWord {} ", numberOfLetters);

        final WordRepository wordRepository = wordRepositoryCacheFactory.getWordRepository(numberOfLetters);

        final int randomIndex = (int)Math.floor(Math.random()*(wordRepository.retrieveNumberOfWords()-1));

        final String word = wordRepository.retrieveWordByIndex(randomIndex);

        log.debug("retrieveRandomWord Result{} ", numberOfLetters);
        return word;
    }

    /**
     * Check whether a word exists
     * @param word for the word
     * @return true if the word exists, otherwise false
     */
    public boolean isAWord(final String word) {

        log.debug("isAWord {} ", word);

        assert word != null;

        final WordRepository wordRepository = wordRepositoryCacheFactory.getWordRepository(word.length());

        final boolean exists = wordRepository.wordExists(word.toLowerCase());

        log.debug("isAWord Result{} ", exists);
        return exists;
    }


}
