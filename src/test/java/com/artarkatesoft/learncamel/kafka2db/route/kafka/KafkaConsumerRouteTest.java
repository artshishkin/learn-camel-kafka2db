package com.artarkatesoft.learncamel.kafka2db.route.kafka;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KafkaConsumerRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new KafkaConsumerRoute();
    }

    @Test
    void testConsumeKafkaTopic() {
        //given
        //need zookeeper and kafka are running
        //after test starts you must send message 123 to pass the test

        //when
        String kafkaMessage = consumer.receiveBody("direct:readFromKafka", String.class);

        //then
        assertEquals("123", kafkaMessage);
    }
}