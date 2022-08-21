#Dockerfile for sellerservice

FROM openjdk:11
EXPOSE  8083
COPY ./target/SellerService-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]