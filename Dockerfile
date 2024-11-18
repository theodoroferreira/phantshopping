FROM eclipse-temurin:17 AS build

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

COPY src src

RUN chmod +x ./gradlew

FROM eclipse-temurin:17
VOLUME /tmp

COPY --from=build build/libs/*.jar phantshopping-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
