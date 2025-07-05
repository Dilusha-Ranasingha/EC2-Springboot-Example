FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/EC2-Springboot-Example-0.0.1-SNAPSHOT.jar EC2-Springboot-Example-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "EC2-Springboot-Example-0.0.1-SNAPSHOT.jar"]