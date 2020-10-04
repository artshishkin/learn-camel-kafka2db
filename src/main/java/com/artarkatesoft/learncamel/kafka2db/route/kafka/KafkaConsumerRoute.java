package com.artarkatesoft.learncamel.kafka2db.route.kafka;

import org.apache.camel.builder.RouteBuilder;

public class KafkaConsumerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from Dilip
//        from("kafka:localhost:9092?topic=my-first-topic&groupId=group1&consumerCount=1&autoOffsetReset=earliest")
        from("kafka:my-first-topic?brokers=localhost:9092&autoOffsetReset=latest")
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}")
                .to("direct:readFromKafka");
    }
}
