FROM openjdk:17-jdk AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

COPY src src

RUN chmod +x ./gradlew

RUN ./gradlew clean build -x test

FROM openjdk:17-jdk
VOLUME /tmp

COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
