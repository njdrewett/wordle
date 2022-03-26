package com.drewett.words.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Word Repository Test
 */
public class WordRepositoryStubTest {

    private WordRepository wordRepository;

    @BeforeEach
    public void setup() {
        wordRepository = new StubWordRepository();
    }

    @Test
    public void retrieveWordByIndex() {
        String word = wordRepository.retrieveWordByIndex(1);
        assert word != null;
        assertEquals( word.length(), 5, "Length should be 5");
    }

    @Test
    public void TestWordStorageWordExists() {
        boolean wordExists = wordRepository.wordExists("abbey");
        assert wordExists == true;
    }
}
