package com.drewett.words.repository;

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
public class WordRepositoryFactoryIT {

    private WordRepositoryFactory wordRepositoryFactory;

    @Autowired
    WordRepositoryFactoryIT(final WordRepositoryFactory wordRepositoryFactory) {
        this.wordRepositoryFactory = wordRepositoryFactory;
    }

    @BeforeEach
    public void setup() {

    }

    @Test
    public void retrieveWordByIndex() {
        final WordRepository wordRepository = wordRepositoryFactory.getWordRepository(5);
        final String word = wordRepository.retrieveWordByIndex(1);
        assert word != null;
        assertEquals( word.length(), 5, "Length should be 5");
    }

    @Test
    public void TestWordStorageWordExists() {
        final WordRepository wordRepository = wordRepositoryFactory.getWordRepository(5);
        boolean wordExists = wordRepository.wordExists("abbey");
        assert wordExists == true;
    }
}
