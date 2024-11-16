FROM gradle:jdk11
WORKDIR /app
COPY . .
RUN ./gradlew build
EXPOSE 8
ENV NAME=api-gw-co
CMD ["java", "-jar", "/app/build/libs/service-registry-0.0.1-SNAPSHOT-plain.jar"]