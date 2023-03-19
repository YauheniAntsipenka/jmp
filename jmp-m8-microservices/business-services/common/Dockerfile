FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

LABEL ${LABEL_GROUP}="common"
LABEL description = "common"

COPY target/common-1.0.jar /common-1.0.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/common-1.0.jar"]
