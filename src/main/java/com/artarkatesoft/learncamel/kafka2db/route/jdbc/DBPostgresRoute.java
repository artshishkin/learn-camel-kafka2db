package com.artarkatesoft.learncamel.kafka2db.route.jdbc;

import com.artarkatesoft.learncamel.kafka2db.processors.InputMessageProcessor;
import org.apache.camel.builder.RouteBuilder;

public class DBPostgresRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:dbInput")
                .log("Received message is ${body}")
                .process(new InputMessageProcessor())
                .log("Query string is ${body}")
                .to("jdbc:myDataSource")
                .to("sql:select * from messages_kafka")
                .log("After sql: ${body}");
    }
}
