FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY /build/libs/bot-1.0.jar build/

WORKDIR /app/build
EXPOSE 8080
ENTRYPOINT java -jar bot-1.0.jar