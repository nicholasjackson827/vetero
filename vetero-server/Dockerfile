FROM openjdk:11.0.6-jre
ARG JAR_FILE=target/vetero-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]