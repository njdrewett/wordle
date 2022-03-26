package com.drewett.words.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class WordRepositoryFactory {

    @Value("${wordFileRepository.filepostfix:-letter-words.txt}")
    private String filePostFix;

    @Value("${wordFileRepository.rootFolder:words}")
    private String wordsFolder;

    public WordRepositoryFactory() {
        super();
    }

    public WordRepository getWordRepository(final int numberOfWords) {
        if(numberOfWords == 0) {
            return new StubWordRepository();
        }
        return new FileWordRepository(numberOfWords, wordsFolder, filePostFix  );
    }
}
