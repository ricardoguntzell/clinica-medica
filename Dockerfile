FROM openjdk:17.0.1-jdk-slim

COPY target/clinica-medica-api-0.0.1-SNAPSHOT.jar /app/clinica-medica-api.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/clinica-medica-api.jar"]