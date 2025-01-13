FROM openjdk:17-jdk-slim
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw clean package -DskipTests
RUN ls -la target/

ENTRYPOINT ["java", "-jar", "target/Warehouse-0.0.1-SNAPSHOT.jar"]
