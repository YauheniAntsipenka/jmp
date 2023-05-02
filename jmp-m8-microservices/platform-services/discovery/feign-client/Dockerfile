FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

LABEL ${LABEL_GROUP}="feign-client"
LABEL description = "feign-client"

COPY target/feign-client-1.0.jar /feign-client-1.0.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "/feign-client-1.0.jar"]
