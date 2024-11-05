FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the app
FROM openjdk:11-jre-slim
COPY --from=build /app/target/ModuloEquipageB-0.0.1-SNAPSHOT.jar /usr/local/lib/ModuloEquipageB.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/ModuloEquipageB.jar"]
