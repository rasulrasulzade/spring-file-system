FROM maven:3.8.6-amazoncorretto-11

WORKDIR /app

COPY pom.xml /app/pom.xml
COPY . .

RUN mvn clean install

CMD mvn spring-boot:run