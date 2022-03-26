package com.drewett.words.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Word Repository File injection Unit Test
 *
 * Testing around design of Word Repository
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class WordRepositoryCached5LetterFileTest {

    @Mock
    private WordRepository fileWordRepository;

    @Mock
    private WordRepository cachedWordRepository;


    @BeforeEach
    public void setup()  {
        List<String> testWords = new ArrayList<>();
        testWords.add("hello");
        testWords.add("abbey");

        Mockito.when(fileWordRepository.retrieveAllWords()).thenReturn(testWords);

        cachedWordRepository = new CachedWordRepository(fileWordRepository);
    }

    @Test
    public void retrieveWordByIndex() {

         String indexedWord = cachedWordRepository.retrieveWordByIndex(1);
        assert indexedWord != null;
        assertEquals(5, indexedWord.length(), "Length should be 5");
        assertEquals( "abbey",  indexedWord, "Should match word");
    }

    @Test
    public void TestWordStorageWordExists() {
        boolean wordExists = cachedWordRepository.wordExists("abbey");
        assert wordExists;
    }

    @Test
    public void TestWordStorageWordNotExists() {
        boolean wordExists = cachedWordRepository.wordExists("abbdsadsaey");
        assert !wordExists;
    }

    @Test
    public void TestWordStorageWordGetAll() {
        List<String> words = cachedWordRepository.retrieveAllWords();
        System.out.println(words);
    }
}
