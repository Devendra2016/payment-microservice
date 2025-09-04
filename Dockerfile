# ===== FIRST STAGE: BUILD =====
# Use a full JDK image to compile the application
FROM eclipse-temurin:21-jdk-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and the parent POM
COPY mvnw .
COPY .mvn/ .mvn/
COPY pom.xml .
RUN chmod +x mvnw
# Copy the module POMs
COPY domain/pom.xml domain/
COPY application/pom.xml application/
COPY infrastructure/pom.xml infrastructure/

# This step downloads all dependencies.
# It's a separate step to leverage Docker layer caching.
# As long as the POMs don't change, this layer is cached.
RUN ./mvnw dependency:go-offline -B

# Copy the actual source code
COPY domain/src/ domain/src/
COPY application/src/ application/src/
COPY infrastructure/src/ infrastructure/src/
ENV TZ=Asia/Kolkata
# Package the application, skipping tests (they run in CI)
RUN ./mvnw package -DskipTests

# ===== SECOND STAGE: RUNTIME =====
# Use a slim JRE image for the final runtime
FROM eclipse-temurin:21-jre-alpine

# Create a non-root user for security (best practice)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Copy the packaged JAR from the builder stage
COPY --from=builder /app/infrastructure/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point to run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]