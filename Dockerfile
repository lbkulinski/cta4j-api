FROM amazoncorretto:21

WORKDIR /app

COPY target/wtl-coaching-vod-api-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
