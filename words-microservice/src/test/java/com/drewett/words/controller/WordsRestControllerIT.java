package com.drewett.words.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WordsRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TestGet5LetterWord() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/words/random/5")).andDo(print()).andExpect(status().isOk()).andReturn();

        final String word = mvcResult.getResponse().getContentAsString();
        assert word != null;
        assert word.length() == 5;
    }

    @Test
    public void TestGet7LetterWord() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/words/random/7")).andDo(print()).andExpect(status().isOk()).andReturn();

        final String word = mvcResult.getResponse().getContentAsString();
        assert word.length() == 7;
    }

}
