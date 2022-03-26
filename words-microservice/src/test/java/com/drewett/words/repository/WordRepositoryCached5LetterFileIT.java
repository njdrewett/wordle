package com.drewett.words.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Word Repository File injection Test
 *
 * Testing around design of Word Repository
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WordRepositoryCached5LetterFileIT {

    private WordRepository wordRepository;

    @Value("${wordFileRepository.filepostfix:-letter-words.txt}")
    private String filePostFix;

    @Value("${wordFileRepository.rootFolder:words}")
    private String wordsFolder;

    @BeforeEach
    public void setup()  {
        WordRepository fileWordRepository = new FileWordRepository(5, wordsFolder, filePostFix);
        wordRepository = new CachedWordRepository(fileWordRepository);
    }

    @Test
    public void retrieveWordByIndex() {
        String indexedWord = wordRepository.retrieveWordByIndex(8);
        assert indexedWord != null;
        assertEquals( indexedWord.length(), 5, "Length should be 5");
    }

    @Test
    public void TestWordStorageWordExists() {
        boolean wordExists = wordRepository.wordExists("abbey");
        assert wordExists;
    }

    @Test
    public void TestWordStorageWordNotExists() {
        boolean wordExists = wordRepository.wordExists("abbdsadsaey");
        assert !wordExists;
    }

    @Test
    public void TestWordStorageWordGetAll() {
        List<String> words = wordRepository.retrieveAllWords();
        System.out.println(words);
    }
}
