FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

LABEL ${LABEL_GROUP}="eureka-client"
LABEL description = "eureka-client"

COPY target/eureka-client-1.0.jar /eureka-client-1.0.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "/eureka-client-1.0.jar"]
