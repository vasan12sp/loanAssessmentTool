# Use a lightweight Java runtime
FROM eclipse-temurin:17-jre-jammy
LABEL authors="vasan"

# Working directory inside container
WORKDIR /app

# Copy your built JAR into the container
COPY target/loanAssessmentTool-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
