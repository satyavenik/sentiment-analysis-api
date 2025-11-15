package com.example.sentimentanalysis.controller;

import com.example.sentimentanalysis.service.SentimentAnalysisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SentimentAnalysisController.class)
class SentimentAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SentimentAnalysisService sentimentAnalysisService;

    @Test
    void testAnalyzeSentiment() throws Exception {
        when(sentimentAnalysisService.analyzeSentiment(anyString())).thenReturn("POSITIVE");

        mockMvc.perform(post("/api/sentiment/analyze")
                .contentType(MediaType.TEXT_PLAIN)
                .content("This is a great day!"))
                .andExpect(status().isOk())
                .andExpect(content().string("POSITIVE"));
    }
}
