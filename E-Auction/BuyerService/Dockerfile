#Dockerfile for sellerservice

FROM openjdk:11
EXPOSE  8084
COPY ./target/BuyerService-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]