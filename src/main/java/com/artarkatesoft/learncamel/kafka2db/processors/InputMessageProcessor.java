package com.artarkatesoft.learncamel.kafka2db.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InputMessageProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String inputMessage = exchange.getIn().getBody(String.class);
        String queryString = "INSERT INTO messages_kafka (message) VALUES ('" + inputMessage + "')";
//        String queryString = "INSERT INTO messages_kafka_error (message) VALUES ('" + inputMessage + "')";
        exchange.getIn().setBody(queryString);
    }
}
