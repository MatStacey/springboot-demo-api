FROM openjdk:8-jdk-alpine
COPY target/springboot-api-0.0.1-SNAPSHOT.jar springboot-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/springboot-api-0.0.1-SNAPSHOT.jar"]