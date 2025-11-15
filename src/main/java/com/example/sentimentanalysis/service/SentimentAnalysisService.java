package com.example.sentimentanalysis.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    private final ChatClient chatClient;

    public SentimentAnalysisService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String analyzeSentiment(String text) {
        String prompt = String.format(
            "Analyze the sentiment of the following text and respond with only one word: POSITIVE, NEGATIVE, or NEUTRAL.\n\nText: %s",
            text
        );
        
        return chatClient.prompt()
            .user(prompt)
            .call()
            .content();
    }
}
