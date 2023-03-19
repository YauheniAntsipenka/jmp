FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

LABEL ${LABEL_GROUP}="eureka-server"
LABEL description = "eureka-server"

COPY target/eureka-server-1.0.jar /eureka-server-1.0.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/eureka-server-1.0.jar"]
