package com.drewett.words.repository;

import java.util.List;

public interface WordRepository {

    /**
     * Retrieve a Word by line number
     */
    String retrieveWordByIndex(int index);

    /**
     * Returns True if Word exists
      */
    boolean wordExists(final String word);

    /**
     * Return all words as a List of strings.
     * @return
     */
    List<String> retrieveAllWords();

    /**
     * Get max number of words
     */
    int retrieveNumberOfWords();

}
