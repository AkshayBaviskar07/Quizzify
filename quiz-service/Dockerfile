FROM openjdk:17
ADD target/quiz-service-0.0.1-SNAPSHOT.jar akshaybaviskar2804/quiz-service
ENTRYPOINT ["java", "-jar", "akshaybaviskar2804/quiz-service"]
EXPOSE 8082
ENV SPRING_PROFILES_ACTIVE=docker

