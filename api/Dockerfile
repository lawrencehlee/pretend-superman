FROM eclipse-temurin:17-jdk-alpine as build

WORKDIR /api
COPY . .
RUN ./gradlew assemble

FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080
COPY --from=build /api/build/libs .
ENTRYPOINT ["java", "-jar", "api-0.1-all.jar"]