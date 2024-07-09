# Use an official Gradle runtime as a parent image
FROM gradle:jdk11

# Set the working directory in the container
WORKDIR /app

# Copy the local code to the container
COPY . .

# Build the JAR file
RUN ./gradlew build

# Make port 8080 available outside the container
EXPOSE 8080

# Define environment variable
ENV NAME=api-gw-co

# Run the jar file when the container launches
CMD ["java", "-jar", "/app/build/libs/api-gw-co.jar"]
