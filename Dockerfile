FROM amazoncorretto:25-alpine3.19

ENV JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=40 -XX:MaxRAMPercentage=60 -XX:+ExitOnOutOfMemoryError \
  -XX:MaxMetaspaceSize=96m -XX:ReservedCodeCacheSize=64m"

WORKDIR /app

COPY target/cta4j-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
