FROM openjdk:17-jdk
VOLUME /tmp

COPY --from=build build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
