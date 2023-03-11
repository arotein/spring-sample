FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 12345

CMD ["java", "-jar", "/app/app.jar"]