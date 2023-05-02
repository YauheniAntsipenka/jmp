FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

LABEL ${LABEL_GROUP}="two"
LABEL description = "two"

COPY target/two-1.0.jar /two-1.0.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/two-1.0.jar"]
