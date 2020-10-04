# 15 Tutorial on Camel for Beginners - Dilip S - Create a Route from Kafka to DB

### Section 15 - Create a Route from Kafka to DB

##### 65 - How to start Kafka Broker and Zookeeper 

1.  [Getting Started](https://kafka.apache.org/quickstart)
2.  start `Zookeeper` (moved Kafka to D:/Art/Kafka)
    -  `.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`
3.  modify `server.properties`
    -  `#advertised.listeners=PLAINTEXT://your.host.name:9092` -> `advertised.listeners=PLAINTEXT://localhost:9092`    
4.  start `Kafka`
    -  `.\bin\windows\kafka-server-start.bat .\config\server.properties`
5.  ensure `zookeeper` and `kafka` are running
    -  run `jps`
    -  see `Kafka`
    -  see `QuorumPeerMain`

##### 66 - How to Create Topic, Producer and Consume Messages, Kafka Log Folder

1.  create topic
    -  `.\bin\windows\kafka-topics.bat --create --topic my-first-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 1`
    -  `or` (and preferred)
    -  `.\bin\windows\kafka-topics.bat --create --topic my-first-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1`
2.  describe topics
    -  `.\bin\windows\kafka-topics.bat --describe -zookeeper localhost:2181`
    -  `or` (and preferred)
    -  `.\bin\windows\kafka-topics.bat --describe --bootstrap-server localhost:9092`
3.  instantiate a console producer
    -  `.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic my-first-topic`
4.  instantiate a console consumer
    -  `.\bin\windows\kafka-console-consumer.bat  --bootstrap-server localhost:9092 --topic my-first-topic --from-beginning`
5.  log files
    -  path is set in `server.properties`
    -  default is `/tmp/kafka-logs`
    -  located in root (`d:/tmp/kafka-logs`)