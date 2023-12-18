FROM gcr.io/distroless/java17-debian12

COPY ./build/libsbook-service-0.0.1-SNAPSHOT.jar book-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","book-service.jar"]