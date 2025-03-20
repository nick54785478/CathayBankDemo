FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_FILE
ENV TZ Asia/Taipei
COPY /target/cathay-demo-0.0.1-SNAPSHOT.jar cathay-demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/cathay-demo.jar"]