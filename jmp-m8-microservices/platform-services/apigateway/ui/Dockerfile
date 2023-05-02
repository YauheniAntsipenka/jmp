FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

LABEL ${LABEL_GROUP}="ui"
LABEL description = "ui"

COPY target/ui-1.0.jar /ui-1.0.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/ui-1.0.jar"]
