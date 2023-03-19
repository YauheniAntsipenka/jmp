FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

LABEL ${LABEL_GROUP}="backend"
LABEL description = "backend"

COPY target/backend-1.0.jar /backend-1.0.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/backend-1.0.jar"]
