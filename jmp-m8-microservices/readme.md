to repackage jar: mvn clean install spring-boot:repackage
eureka server: http://localhost:8761/

get all avaliable tags: http://localhost:7101/api/v1/tags/name
graph example: http://localhost:7101/api/v1/graph?q=name,ssCpuUser,:eq,:avg

Download Java SE Development Kit 8 according to your OS and processor’s architecture.
Install Java Development Kit according to JDK installation instructions (see also PATH and CLASSPATH).
Download Apache Maven 3.6.0 according to your OS and processor’s architecture.
Install Apache Maven according to installation instructions.
 Install Docker 8.x+ .
 Install Docker Compose 12.x+.
Create micro-services maven project with business-services and platform-services modules:


8. Create platform-services maven project with discovery and apigateway modules:



9. Elaborate discovery module according to guide Eureka Discovery Service.

10. Elaborate apigateway module according to guide Zuul Proxy Service.

11. Create business-services maven project with common, one, two and two-api modules:



12. Elaborate common module according to guide Archaius Configuration Service.

13. Elaborate one module as REST Service integrated with Servo according to guide Servo Metrics Aggregation Service (see also Eureka Discovery Service).

14. Elaborate two module as REST Service integrated with Servo according to guide Servo Metrics Aggregation Service (see also Eureka Discovery Service).

15. Integrate Docker file into all Spring Boot applications using template:



and maven plugin:



16. Elaborate docker compose:



17. Start services and demonstrate Discovery Service Registry, Requests via API Gateway, Zipkin Metrics and REST API requests.
