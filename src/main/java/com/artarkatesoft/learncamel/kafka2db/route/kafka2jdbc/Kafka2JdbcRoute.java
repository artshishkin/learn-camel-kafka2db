package com.artarkatesoft.learncamel.kafka2db.route.kafka2jdbc;

import com.artarkatesoft.learncamel.kafka2db.processors.InputMessageProcessor;
import org.apache.camel.builder.RouteBuilder;

public class Kafka2JdbcRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:my-first-topic?brokers=localhost:9092&autoOffsetReset=latest")
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}")
                .process(new InputMessageProcessor())
                .log("Query string is ${body}")
                .to("jdbc:myDataSource")
                .to("sql:select * from messages_kafka")
                .log("After sql: ${body}")
                .to("direct:output")
        ;
    }
}
