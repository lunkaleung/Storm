# Storm

To develop Spout and Bolt application in Storm distributed real-time computation system with Storm Java API.

To get started with installing and configuring Kafka and Storm on local system and create a simple topic in Kafka and write Java program for Spout and Bolt.

## Installation

Use Software Project Management and Comprehension Tool Maven [mvn](https://maven.apache.org/) to build the project.

## Usage

### Compile
```bash
mvn clean compile assembly:single
```

### Storm submit topology
```bash
/usr/local/storm/bin/storm jar /home/ubuntu/storm/target/storm-1.0-SNAPSHOT-jar-with-dependencies.jar [TOPOLOGY_NAME]
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
