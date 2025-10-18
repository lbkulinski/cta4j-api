FROM alpine:latest

RUN apk add --no-cache openjdk21

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk

RUN java -version

WORKDIR /app

COPY target/cta4j-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
