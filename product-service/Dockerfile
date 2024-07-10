FROM gradle:jdk11
WORKDIR /app
COPY . .
RUN ./gradlew build
EXPOSE 8080
ENV NAME=product-service
CMD ["java", "-jar", "/app/build/libs/product-service-0.0.1-SNAPSHOT-plain.jar"]
