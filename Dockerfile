FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} serauds-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/serauds-0.0.1-SNAPSHOT.jar"]