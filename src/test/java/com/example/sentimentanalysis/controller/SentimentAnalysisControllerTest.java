package com.example.sentimentanalysis.controller;

import com.example.sentimentanalysis.model.SentimentResponse;
import com.example.sentimentanalysis.service.SentimentAnalysisService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SentimentAnalysisController.class)
class SentimentAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SentimentAnalysisService sentimentAnalysisService;

    @Test
    void testAnalyzeSentiment() throws Exception {
        SentimentResponse mockResponse = new SentimentResponse("POSITIVE", 0.85, "This is a great day!");
        when(sentimentAnalysisService.analyzeSentiment(anyString())).thenReturn(mockResponse);

        mockMvc.perform(post("/api/sentiment/analyze")
                .contentType(MediaType.TEXT_PLAIN)
                .content("This is a great day!"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentiment").value("POSITIVE"))
                .andExpect(jsonPath("$.score").value(0.85))
                .andExpect(jsonPath("$.text").value("This is a great day!"));
    }
}
