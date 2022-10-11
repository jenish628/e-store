FROM openjdk:17
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]



#FROM maven:latest as builder
#WORKDIR /build
#RUN rm -rf *
#COPY . .
#RUN mvn clean install -DskipTests
#
#FROM openjdk:17.0.2-jdk as deploy
#MAINTAINER JENISH
#WORKDIR /app
#COPY --from=builder /build/target/*.jar batchjob.jar
#CMD ["java","-jar","batchjob.jar"]



