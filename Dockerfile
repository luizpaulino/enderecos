FROM amazoncorretto:17-alpine-jdk
MAINTAINER baeldung.com
COPY target/monitoramento-0.0.1-SNAPSHOT.jar monitoramento-0.0.1-SNAPSHOT.jar
COPY application_docker.yml application.yml
ENTRYPOINT ["java","-jar","/monitoramento-0.0.1-SNAPSHOT.jar"]