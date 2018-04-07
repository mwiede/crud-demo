# JSF CRUD Demo application

This application is written in Java and it is based on JSF (Java Server Faces) technology.

For the runtime it uses [Wildfly Swarm](http://wildfly-swarm.io/) as container/packaging.

The data model of this application consists of two entities: Customer and Note.

It can be built using [Maven](https://maven.apache.org/).

## Starting the app

* `mvn wildfly-swarm:run`
* From your IDE, run class `org.wildfly.swarm.Swarm`

Open browser and access [http://localhost:8080/](http://localhost:8080/)

In addition the customer data is exposed in a JAX-RS resource which is accessible via [http://localhost:8080/rest/customers](http://localhost:8080/rest/customers).

## Database and seed data

The applications is using an h2 in-memory database by default.

The database schema is automatically derived from the JPA entities using Hibernate hbm2ddl feature during startup. On a production system 
this tool should be replaced by a proper database scheme delivery tool like [liquibase](https://www.liquibase.org/) or [flyway](https://flywaydb.org/).

In addition the source of the application already brings some seed data, a couple of customers.

## Focus

The main focus of this application is to demonstrate a simple project setup and usage of Java EE standards, which Wildfly Swarm supports.

In contrast to application generation frameworks like [Grails](https://grails.org/) or [JHipster](https://www.jhipster.tech/), which use Spring Boot and other 
Spring libraries as base, the Java EE approach has the advantage of standardized API's and choice of different implementation vendors.
For example, the same war, which runs with Swarm here, can theoretically be deployed to WildFly application server, Tomcat or any container.

## Out of scope:

For a production ready application a few things could be desirable, but were not implemented due to limited time to set this up.

* Performance: for example if the data-set would be huge, it would make sense to use lazy data table, which loads the data in a page wise manner 
instead of loading all data at once. Same holds good for the JAX-RS resource.
* UI/design : nowadays web applications are consumed via different devices with different sizes of screens. Therefore a good approach would be responsive design, 
which adapts the layout to the screen size.

## Deployment & Delivery

Because Wildfly Swarm is packaging the application into a FAT-Jar, there is no need to deploy the war to container, it is self contained and can be started via
 `java -jar target/demo-swarm.jar`

The FAT-Jar can even be used to create a Docker image out of it so that this image can be directly pulled up in a Docker cluster.
