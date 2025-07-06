# 🚀 Spring Boot Example – AWS EC2 Deployment with Docker & GitHub Actions

This is a simple Spring Boot backend application deployed on an **AWS EC2 instance** using **Docker** and automated using **GitHub Actions (CI/CD)**.

🔗 **Live Backend URL**: [http://13.62.53.79:8080](http://13.62.53.79:8080)  
🔗 **Paired Frontend App**: [http://13.62.53.79:3000](http://13.62.53.79:3000)

---

## 📦 Project Structure

```bash
.
├── src/                      # Java source code
├── target/                   # Compiled .jar file (after build)
├── Dockerfile                # Docker config to build backend image
├── pom.xml                   # Maven config
└── .github/
    └── workflows/
        └── cicd.yml          # GitHub Actions CI/CD pipeline
```

## ⚙️ Technologies Used

- Java 17
- Spring Boot
- Maven
- Docker
- GitHub Actions (CI/CD)
- AWS EC2 (Ubuntu 24.04)
- Self-hosted GitHub Runner

## 📋 Features

- ✅ REST API backend with Spring Boot
- ✅ Dockerized backend app
- ✅ Auto CI/CD pipeline via GitHub Actions
- ✅ Deploys to AWS EC2 using Docker
- ✅ Integrated with a React frontend (hosted on same EC2 instance)

## 🚀 Deployment Setup

### 🐳 Dockerfile

Builds and runs the backend Spring Boot .jar using the Eclipse Temurin JDK:

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/springboot-example.jar springboot-example.jar
EXPOSE 8080
CMD ["java", "-jar", "springboot-example.jar"]
```

### ⚙️ GitHub Actions CI/CD Workflow (.github/workflows/cicd.yml)

Automates:
- Maven build
- Docker image creation
- Docker Hub push
- Remote deploy to EC2 (via self-hosted runner)

```yaml
name: CICD

on:
  push:
    branches: [feature/EC2-B]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout source
        uses: actions/checkout@v3
      - name: Setup Java
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Build Project
        run: mvn clean install -DskipTests
      - name: Login to Docker Hub
        run: docker login -u ${{ secrets.DOCKER_USERNAME }} -p ${{ secrets.DOCKER_PASSWORD }}
      - name: Build Docker Image
        run: docker build -t dilusharanasinghe/springboot-example .
      - name: Push Image
        run: docker push dilusharanasinghe/springboot-example:latest

  deploy:
    needs: build
    runs-on: [self-hosted, aws-ec2]
    steps:
      - name: Pull latest image
        run: docker pull dilusharanasinghe/springboot-example:latest
      - name: Remove old container
        run: docker rm -f EC2 springboot-example-container || true
      - name: Run new container
        run: docker run -d -p 8080:8080 --name EC2 springboot-example-container dilusharanasinghe/springboot-example
```

### 🔐 GitHub Secrets

| Key              | Value                           |
|------------------|---------------------------------|
| DOCKER_USERNAME  | Your DockerHub username         |
| DOCKER_PASSWORD  | DockerHub access token (not password) |

### ☁️ AWS EC2 Setup Summary

- **EC2 OS**: Ubuntu 24.04
- **Installed**: Docker, Java, GitHub Runner
- **Self-hosted runner**: Configured and running in background
- **Docker container**: Exposes port 8080

## 🖥️ Running Locally (Optional)

```bash
# Build the project
./mvnw clean package

# Run the JAR
java -jar target/springboot-example.jar
```

## 🧑‍💻 Author

Dilusha Ranasingha  
🔗 [GitHub](https://github.com/Dilusha-Ranasingha/EC2-Springboot-Example)

## 📝 License

This project is licensed under the MIT License.
