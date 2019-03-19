# Storm

To develop Spout and Bolt application in Storm distributed real-time computation system with Storm Java API.

## Prerequisites

To get started with installing and configuring Kafka and Storm on local system and create a simple topic in Kafka and write Java program for Spout and Bolt.

## Deployment

### Compile
```
mvn clean compile assembly:single
```

### Storm submit topology
```
/usr/local/storm/bin/storm jar /home/ubuntu/storm/target/storm-1.0-SNAPSHOT-jar-with-dependencies.jar [TOPOLOGY_NAME]
```

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management
