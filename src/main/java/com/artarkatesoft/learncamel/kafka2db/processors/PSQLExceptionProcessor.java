package com.artarkatesoft.learncamel.kafka2db.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PSQLExceptionProcessor implements Processor {

    private Logger log = LoggerFactory.getLogger(PSQLExceptionProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception exceptionCaught = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        log.error("Caught an Exception with message: {}", exceptionCaught.getMessage());
        log.error("          Exception class: {}", exceptionCaught.getClass());
        log.error("          Failure Endpoint: {}", exchange.getProperty(Exchange.FAILURE_ENDPOINT));
        exchange.getIn().setBody("Caught an Exception: " + exceptionCaught.getMessage());
    }
}
