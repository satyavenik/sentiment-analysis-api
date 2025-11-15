# Sentiment Analysis API

This repository provides a small Spring Boot service exposing a sentiment analysis endpoint.

## What I added
- `postman_collection.json` — Postman collection to import the `POST /api/sentiment/analyze` request.
- `README.md` (this file) — usage and examples.

## Prerequisites
- Java 17+ and Maven installed and on PATH
- (Optional) `OPENAI_API_KEY` environment variable if the app is configured to use OpenAI via Spring AI

## Run the application
From the project root run:

```cmd
mvn spring-boot:run
```

The server listens on port 8080 (configured in `src/main/resources/application.properties`).

## Import Postman collection
1. Open Postman -> File -> Import -> Choose Files -> select `postman_collection.json` in the project root.
2. The collection includes a single request `Analyze Sentiment` configured for `POST http://localhost:8080/api/sentiment/analyze`.

## Examples

The API returns a JSON response with:
- **sentiment**: One of `POSITIVE`, `NEGATIVE`, or `NEUTRAL`
- **score**: Confidence value between 0.0 and 1.0 (higher means more confident)
- **text**: The original text that was analyzed

### Example 1: Negative Sentiment

**Request (Windows `cmd.exe`):**
```cmd
curl -X POST "http://localhost:8080/api/sentiment/analyze" -H "accept: */*" -H "Content-Type: application/json" -d "\"I went to store and staff is rude and un welcoming\""
```

**Response:**
```json
{
  "sentiment": "NEGATIVE",
  "score": 0.85,
  "text": "I went to store and staff is rude and un welcoming"
}
```

### Example 2: Positive Sentiment

**Request (Windows `cmd.exe`):**
```cmd
curl -X POST "http://localhost:8080/api/sentiment/analyze" -H "accept: */*" -H "Content-Type: application/json" -d "\"This repo is so helpful and creative! The documentation is excellent and the API works perfectly.\""
```

**Response:**
```json
{
  "sentiment": "POSITIVE",
  "score": 0.92,
  "text": "This repo is so helpful and creative! The documentation is excellent and the API works perfectly."
}
```

### Notes:
- The request body is a JSON string (hence the escaped quotes `\"...\"`).
- If your API expects a different JSON shape (e.g., `{ "text": "..." }`), adjust the Postman body and the curl `-d` accordingly.
- Higher scores indicate stronger confidence in the sentiment classification.

## Troubleshooting
- If the server does not start, confirm Java and Maven versions and check logs printed by `mvn spring-boot:run`.
- If using OpenAI integration ensure `OPENAI_API_KEY` is set in your environment (the project uses `spring.ai.openai.api-key` in `application.properties`).

---
Generated: automatically added Postman collection and usage README.

