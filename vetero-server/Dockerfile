FROM maven:3.6.3-jdk-11-openj9 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -Dmaven.test.skip=true package -q
FROM openjdk:11.0.6-jre
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/vetero-1.0-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "vetero-1.0-SNAPSHOT.jar"]