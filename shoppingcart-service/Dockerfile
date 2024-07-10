FROM gradle:jdk11
WORKDIR /app
COPY . .
RUN ./gradlew build
EXPOSE 8082
ENV NAME=shopping-cart
CMD ["java", "-jar", "/app/build/libs/shoppingcart-service-0.0.1-SNAPSHOT-plain.jar"]

