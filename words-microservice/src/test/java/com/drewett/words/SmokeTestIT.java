package com.drewett.words;

import com.drewett.words.controller.WordsRestController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTestIT {

    @Autowired
    private WordsRestController controller;

    @Test
    public void contextLoads() {
        Assertions.assertThat(controller).isNotNull();
    }
}