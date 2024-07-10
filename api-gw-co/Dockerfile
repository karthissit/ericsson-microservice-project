FROM gradle:jdk11
WORKDIR /app
COPY . .
RUN ./gradlew build
EXPOSE 8761
ENV NAME=api-gw-co
CMD ["java", "-jar", "/app/build/libs/api-gw-co-0.0.1-SNAPSHOT-plain.jar"]
