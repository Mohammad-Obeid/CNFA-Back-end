FROM openjdk:17-jdk
COPY ./target/CNFABackend-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","CNFABackend-0.0.1-SNAPSHOT.jar"]