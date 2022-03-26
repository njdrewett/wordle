FROM amazoncorretto:17
RUN mkdir -p /opt/words-microservice
ARG JAR_FILE=target/words-microservice-*.jar
COPY ${JAR_FILE} /opt/words-microservice/app.jar
ENTRYPOINT ["java","-jar","/opt/words-microservice/app.jar"]
EXPOSE 8080
