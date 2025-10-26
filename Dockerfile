FROM amazoncorretto:25-alpine3.19

ENV JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=40 -XX:MaxRAMPercentage=60 -XX:+ExitOnOutOfMemoryError \
  -XX:MaxMetaspaceSize=96m -XX:ReservedCodeCacheSize=64m"

WORKDIR /app

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
