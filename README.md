# sentiment-analysis-api

This project uses a Large Language Model (LLM) to detect sentiment in any input text and classifies it as POSITIVE, NEGATIVE, or NEUTRAL. Demonstrates how to build an AI-powered REST API with prompt engineering, reusable templates, and Java integration using Spring AI.

## Technologies Used

- **Spring Boot 3.5.7** - Application framework
- **Spring AI** - ChatClient for LLM integration
- **OpenAPI/Swagger** - API documentation
- **Maven** - Build tool
- **Java 17** - Programming language

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- OpenAI API Key (for LLM integration)

## Configuration

Before running the application, set your OpenAI API key as an environment variable:

```bash
export OPENAI_API_KEY=your-api-key-here
```

Or you can set it in the `application.properties` file.

## Building the Project

```bash
mvn clean install
```

## Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

## API Endpoints

### Analyze Sentiment

**Endpoint:** `POST /api/sentiment/analyze`

**Request Body:** Plain text string

**Example:**

```bash
curl -X POST http://localhost:8080/api/sentiment/analyze \
  -H "Content-Type: text/plain" \
  -d "This is a wonderful day!"
```

**Response:** `POSITIVE`, `NEGATIVE`, or `NEUTRAL`

## Project Structure

```
src/
├── main/
│   ├── java/com/example/sentimentanalysis/
│   │   ├── SentimentAnalysisApplication.java       # Main application class
│   │   ├── controller/
│   │   │   └── SentimentAnalysisController.java    # REST controller
│   │   └── service/
│   │       └── SentimentAnalysisService.java       # Service layer with ChatClient
│   └── resources/
│       └── application.properties                   # Application configuration
└── test/
    └── java/com/example/sentimentanalysis/
        └── controller/
            └── SentimentAnalysisControllerTest.java # Controller tests
```

## Features

- REST API endpoint for sentiment analysis
- Integration with OpenAI's LLM via Spring AI ChatClient
- Swagger/OpenAPI documentation
- Unit tests for controller layer
- Maven build configuration
