#Dockerfile for api-gateway-service

FROM openjdk:11
EXPOSE  8765
COPY ./target/api-gateway-service-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]