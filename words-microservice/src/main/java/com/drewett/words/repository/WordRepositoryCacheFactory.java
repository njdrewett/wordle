package com.drewett.words.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class WordRepositoryCacheFactory {

    private WordRepositoryFactory wordRepositoryFactory;

    @Autowired
    public WordRepositoryCacheFactory(final WordRepositoryFactory wordRepositoryFactory) {
        super();
        this.wordRepositoryFactory = wordRepositoryFactory;
    }

    public WordRepository getWordRepository(final int numberOfWords) {
        final WordRepository wordRepository = wordRepositoryFactory.getWordRepository(numberOfWords);
        final WordRepository cachedWordRepository = new CachedWordRepository(wordRepository);
        return cachedWordRepository;
    }
}
