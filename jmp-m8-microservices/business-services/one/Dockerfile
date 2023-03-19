FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

LABEL ${LABEL_GROUP}="one"
LABEL description = "one"

COPY target/one-1.0.jar /one-1.0.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/one-1.0.jar"]
