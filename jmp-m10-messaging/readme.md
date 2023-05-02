Prerequisits: "Event REST" application
1.	Create maven project with 4 modules:
o	event-service-api
o	event-service-dto
o	event-service-impl
o	event-service-rest
2.	event-service-dto module should contain Event class with following fields:
o	eventId : Long
o	title : String
o	place : String
o	speaker : String
o	eventType : enum (WORKSHOP, TECH_TALK)
o	dateTime : LocalDateTime
3.	event-service-api module should contain EventService interface with following methods:
o	Event createEvent(Event event);
o	Event updateEvent(Long id, Event event);
o	Event getEvent(Long id);
o	Event deleteEvent(Long id);
o	List getAllEvents();
4.	event-service-impl module should contain EventServiceImpl which implements EventService interface. Feel free to use any event storage (filesystem, any db, in memory db, nosql) up to your preference and choice.
5.	event-service-rest module should contain EventServiceController which provides REST API interface according to 2nd or 3rd level of REST API maturity.
7.	Implement Application class with @SpringBootApplication annotation and main method.
Practical Task
Extend "Event REST" application to send messages to three different messaging systems:
1.	Kafka
2.	RabbitMQ
3.	ActiveMQ
4.	Add one more interface to event-service-api:
public interface EventMessaging {
    void createEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(Long id);
}
5.	Inject optionally (@Autowired(required = false)) EventMessaging in EventServiceImpl and call in corresponding methods in createEvent, updateEvent and deleteEvent.
6.	While application run with one of messaging systems (Kafka, RabbitMQ of ActiveMQ) we suggest use spring framework profiles. Below tasks are based on profiles approach.
o	kafka
o	rabbit
o	activemq
7.	EventServiceImpl should use that messaging system specified by active spring framework profile
8.	Create one more module event-messaging
9.	Add dependency in event-messaging to event-service-api
10.	For each messaging system create and use six topics/queues.
o	Three for notification of create, update, delete events from EventServiceImpl:
1.	create-event-notification
2.	update-event-notification
3.	delete-event-notification

o	Three for listening from messaging systems and calling corresponding methods in EventService:
1.	create-event-request
2.	update-event-request
3.	delete-event-request

Kafka
Follow instruction in Kafka Quickstart Guide to install and execute Kafka
1.	Create six above topics in Kafka using the same guide:
o	kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic create-event-notification
2.	Check that topics were created successfully:
o	kafka-topics --list --bootstrap-server localhost:9092
3.	Add necessary for Kafka dependencies to event-messaging/pom.xml:
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka-test</artifactId>
    <scope>test</scope>
</dependency>
4.	Implement kafka.EventConsumer and call corresponding methods of EventService:
@Component
@Profile("kafka")
public class EventConsumer {
    @KafkaListener
    public void createEvent(List<Event> events)
    @KafkaListener
    public void updateEvent(List<Event> events)
    @KafkaListener
    public void deleteEvent(List<Identifier> eventIds)
}
5.	Implement kafka.EventProducer that is called from EventServiceImpl that should send messages to Kafka:
@Component
@Profile("kafka")
public class EventProducer implements EventMessaging
6.	Check that create, update, delete REST operations send messages to Kafka.
7.	Create, update, delete events in Event Service by sending messages to Kafka.
Rabbit MQ
Follow instruction in Downloading and Installing RabbitMQ.
Use Rabbit MQ Web Console: http://localhost:15672/
1.	Add necessary for Rabbit MQ dependencies to event-messaging/pom.xml:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.amqp</groupId>
    <artifactId>spring-rabbit-test</artifactId>
    <scope>test</scope>
</dependency>
2.	Implement rabbit.EventConsumer and call corresponding methods of EventService:
@Component
@Profile("rabbit")
public class EventConsumer {
    @RabbitListener
    public void createEvent(Event event)
    @RabbitListener
    public void updateEvent(Event event)
    @RabbitListener
    public void deleteEvent(Identifier eventId)
}
3.	Implement rabbit.EventProducer that is called from EventServiceImpl that should send messages to RabbitMQ:
@Component
@Profile("rabbit")
public class EventProducer implements EventMessaging
4.	Check that create, update, delete REST operations send messages to RabbitMQ.
5.	Create, update, delete events in Event Service by sending messages to RabbitMQ.
Active MQ
Follow instruction in https://activemq.apache.org/download.html to download and install ActiveMQ.
Use ActiveMQ Web Console: http://localhost:8161/admin/
1.	Add necessary for Active MQ dependencies to event-messaging/pom.xml:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
2.	Implement activemq.EventConsumer and call corresponding methods of EventService:
@Component
@Profile("activemq")
public class EventConsumer {
    @JmsListener
    public void createEvent(Event event)
    @JmsListener
    public void updateEvent(Event event)
    @JmsListener
    public void deleteEvent(Identifier eventId)
}
3.	Implement activemq.EventProducer that is called from EventServiceImpl that should send messages to ActiveMQ:
@Component
@Profile("activemq")
public class EventProducer implements EventMessaging
4.	Check that create, update, delete REST operations send messages to ActiveMQ.
5.	Create, update, delete events in Event Service by sending messages to ActiveMQ.
