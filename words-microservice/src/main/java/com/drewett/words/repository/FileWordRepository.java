package com.drewett.words.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
public class FileWordRepository implements WordRepository {

    private final String wordsFolder;

    private final String filePostFix;

    private final int numberOfLetters;

    public FileWordRepository(final int numberOfLetters,
                              final String wordsFolder,
                              final String filePostFix )  {
        super();
        this.numberOfLetters = numberOfLetters;
        this.filePostFix = filePostFix;
        this.wordsFolder = wordsFolder;
    }

    private List<String> readWordsFromFile() {
        final String fileName = concatFilePathAndName();
        log.info("Reading {}", fileName);
        List<String> words = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource(fileName);
        try (InputStream inputStream = resource.getInputStream()) {
            final Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            final BufferedReader br = new BufferedReader(reader);
            return br.lines().collect(Collectors.toList());
            //return Files.readAllLines(Paths.get(resource.getInputStream().getPath()) );
        } catch (IOException exception) {
            log.error("Exception reading " + fileName, exception);
            throw new WordRepositoryException("Error loading " + numberOfLetters + "Word file", exception);
        }
    }

    private String concatFilePathAndName() {
        StringBuilder stringBuilder = new StringBuilder();
        if(wordsFolder != null && !wordsFolder.trim().isBlank()) {
            stringBuilder.append(wordsFolder);
            stringBuilder.append(File.separator);
        }
        stringBuilder.append(numberOfLetters);
        stringBuilder.append(filePostFix);
        return stringBuilder.toString();
    }

    @Override
    public List<String> retrieveAllWords() {
        return readWordsFromFile();
    }

    @Override
    public String retrieveWordByIndex(final int index) {
        log.debug("RetrieveWordByIndex {} ", index);

        String word = retrieveAllWords().get(index);

        log.debug("Returned Word {} ", word);
        return word;
    }

    @Override
    public boolean wordExists(String word) {
        log.debug("wordExists {} ", word);

        boolean wordExists = retrieveAllWords().contains(word);

        log.debug("Word exists {} ", wordExists);
        return wordExists;
    }

    @Override
    public int retrieveNumberOfWords() {
        log.debug("retrieveNumberOfWords ");

        int numberOfWords = retrieveAllWords().size();

        log.debug("retrieveNumberOfWords Result {} ", numberOfWords);

        return numberOfWords;
    }
}
