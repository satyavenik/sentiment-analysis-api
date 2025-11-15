package com.example.sentimentanalysis.controller;

import com.example.sentimentanalysis.model.SentimentResponse;
import com.example.sentimentanalysis.service.SentimentAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sentiment")
@Tag(name = "Sentiment Analysis", description = "API for analyzing sentiment of text")
public class SentimentAnalysisController {

    private final SentimentAnalysisService sentimentAnalysisService;

    public SentimentAnalysisController(SentimentAnalysisService sentimentAnalysisService) {
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    @PostMapping("/analyze")
    @Operation(summary = "Analyze sentiment", description = "Analyzes the sentiment of the provided text and returns sentiment, confidence score, and original text")
    public ResponseEntity<SentimentResponse> analyzeSentiment(@RequestBody String text) {
        SentimentResponse response = sentimentAnalysisService.analyzeSentiment(text);
        return ResponseEntity.ok(response);
    }
}
