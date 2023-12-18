FROM gcr.io/distroless/java17-debian12

COPY ./build/libs/book-service-0.0.1.jar book-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","book-service.jar"]