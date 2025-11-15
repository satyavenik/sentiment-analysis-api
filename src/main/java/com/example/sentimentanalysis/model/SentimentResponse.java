package com.example.sentimentanalysis.model;

public class SentimentResponse {
    private String sentiment;
    private Double score;
    private String text;

    public SentimentResponse() {
    }

    public SentimentResponse(String sentiment, Double score, String text) {
        this.sentiment = sentiment;
        this.score = score;
        this.text = text;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

