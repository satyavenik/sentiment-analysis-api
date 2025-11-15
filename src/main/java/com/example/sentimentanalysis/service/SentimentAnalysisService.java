package com.example.sentimentanalysis.service;

import com.example.sentimentanalysis.model.SentimentResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    private final ChatClient chatClient;

    public SentimentAnalysisService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public SentimentResponse analyzeSentiment(String text) {
        String prompt = String.format(
            "Analyze the sentiment of the following text and respond in JSON format with two fields: " +
            "\"sentiment\" (one of: POSITIVE, NEGATIVE, or NEUTRAL) and \"score\" (a confidence value between 0.0 and 1.0).\n\n" +
            "Text: %s\n\n" +
            "Respond only with valid JSON, no other text.",
            text
        );
        
        String response = chatClient.prompt()
            .user(prompt)
            .call()
            .content();

        // Parse the JSON response
        return parseResponse(response, text);
    }

    private SentimentResponse parseResponse(String jsonResponse, String originalText) {
        try {
            // Simple JSON parsing (extract sentiment and score)
            String sentiment = extractJsonValue(jsonResponse, "sentiment");
            String scoreStr = extractJsonValue(jsonResponse, "score");
            Double score = scoreStr != null ? Double.parseDouble(scoreStr) : 0.5;

            if (sentiment != null) {
                return new SentimentResponse(sentiment.toUpperCase(), score, originalText);
            }
        } catch (Exception e) {
            // Fall through to fallback
        }

        // Fallback if JSON parsing fails
        String sentiment = jsonResponse.contains("POSITIVE") ? "POSITIVE" :
                         jsonResponse.contains("NEGATIVE") ? "NEGATIVE" : "NEUTRAL";
        return new SentimentResponse(sentiment, 0.5, originalText);
    }

    private String extractJsonValue(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return null;

        int colonIndex = json.indexOf(":", keyIndex);
        int startIndex = colonIndex + 1;

        // Skip whitespace and quotes
        while (startIndex < json.length() && (json.charAt(startIndex) == ' ' || json.charAt(startIndex) == '"')) {
            startIndex++;
        }

        // Find end (comma, closing brace, or quote)
        int endIndex = startIndex;
        while (endIndex < json.length()) {
            char c = json.charAt(endIndex);
            if (c == '"' || c == ',' || c == '}' || c == '\n') break;
            endIndex++;
        }

        return json.substring(startIndex, endIndex).trim();
    }
}
