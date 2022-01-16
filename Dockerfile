FROM openjdk:8
ADD build/libs/lloyds-bank-api-exercise-0.0.1-SNAPSHOT.jar lloyds-bank-api-exercise-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "lloyds-bank-api-exercise-0.0.1-SNAPSHOT.jar"]