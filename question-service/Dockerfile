FROM openjdk:17
ADD target/question-service-0.0.1-SNAPSHOT.jar akshaybaviskar2804/question-service
ENTRYPOINT ["java", "-jar" , "akshaybaviskar2804/question-service"]
EXPOSE 8081
ENV SPRING_PROFILES_ACTIVE=docker