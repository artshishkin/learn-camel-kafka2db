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
