package com.drewett.words;

import com.drewett.words.repository.WordRepositoryCacheFactory;
import com.drewett.words.repository.WordRepositoryFactory;
import com.drewett.words.service.WordsService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WordsTestConfiguration {

    @Bean
    WordRepositoryFactory getWordRepositoryFactory() {
        return new WordRepositoryFactory();
    }

    @Bean
    WordRepositoryCacheFactory getWordRepositoryCacheFactory() {
        return new WordRepositoryCacheFactory(getWordRepositoryFactory());
    }

    @Bean
    WordsService getWordService(){
        return new WordsService(getWordRepositoryCacheFactory());
    }
}
