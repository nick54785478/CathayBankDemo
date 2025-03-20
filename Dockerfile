FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_FILE=/target/cathay-bank-demo-0.0.1-SNAPSHOT.jar
ENV TZ Asia/Taipei
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
