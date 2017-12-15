# ContGuard Telemetry Service

## 1. Design
1. This application is a micro-service that retrieves a stores telemetry requests and allows matching them to vessels. 

2. The project is built on top of [Spring Boot](https://projects.spring.io/spring-boot/) framework and utilizes the following framework's functionality:
* IoC container for Dependency Injection
* Embedded Tomcat for HTTP server
* JPA hibernate for Object-Relational mapping
* H2 Embedded in-memory database for storing telemetries

3. The project contains the following modules:
* **RESTful HTTP server** to store Telemetries and match to vessels
* **API Contracts** to map HTTP request/response bodies to Java structures
* **SDK** encapsulating server's REST URLs and providing Java-based interface on top of the contracts.
* **Example client** utilizing the SDK for sending to server a list of telemetries parsed from CSV file and matching them to vessels parsed from another CSV file.
    
## 2. Configuration
1. The server configuration parameters are located in application.properties file in resources directory
This file is a part of created jar. 
In order to override all or some parameters in runtime, place the application.properties with file in the same directory with the jar file.

2. The default Server HTTP port is 8080
 
## 3. REST API
1. Health check:
````
GET http://<base-url>:8080/healthcheck
````

2. Add telemetry:
````
POST http://<base-url>:8080/telemetry/telemetries
````

3. Match vessel:
````
PUT http://<base-url>:8080/telemetry/telemetries
````

4. Delete all telemetries:
````
DELETE http://<base-url>:8080/telemetry/telemetries
````

## 4. Build
Build the project using Maven. Run the following command from the project root directory: 
````
mvn clean install
````

## 5. Run
Both client and server are built into a self containing "fat" jars. 

1. Run Server application using command:
````
java -jar contguard-telemetry-service-server-0.0.1-SNAPSHOT.jar
````

2. Run Client application using command:
````
java -jar contguard-telemetry-service-client-0.0.1-SNAPSHOT.jar
````

On Linux, run the applications with **_sudo_** permissions:
````
sudo java -jar contguard-telemetry-service-server-0.0.1-SNAPSHOT.jar
sudo java -jar contguard-telemetry-client-server-0.0.1-SNAPSHOT.jar
````

## 6. Test the application
1. Startup the server application
2. Run the client application.
    1. The client application will send telemetries from a **_resource_** CSV file
    2. The client application will match telemetries to vessels from a **_resource_** CSV file.
    3. The client application will store the matching telemetries to **_matchedTelemetries.csv_** file in the running directory.
3. Both server and client application stores logs under \var\log\contguard\telemetry\ folder.