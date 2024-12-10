FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . ./

RUN chmod +x mvnw

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
