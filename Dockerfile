FROM maven:3.8.6-amazoncorretto-11 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY . .
RUN mvn clean install

##FROM arm64v8/openjdk:11
FROM gcr.io/distroless/java
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]