package com.artarkatesoft.learncamel.kafka2db.route.kafka2jdbc;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Registry;
import org.apache.camel.support.DefaultRegistry;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Kafka2JdbcRouteTest extends CamelTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
        Registry registry = new DefaultRegistry();
        registry.bind("myDataSource", createDataSource());
        return new DefaultCamelContext(registry);
    }

    private DataSource createDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/learn_camel");
        dataSource.setUser("postgres");
        dataSource.setPassword("123");
        return dataSource;
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new Kafka2JdbcRoute();
    }

    @Test
    void testKafka2JdbcRoute() {
        //given
        //need zookeeper and kafka are running
        //after test starts you must send message 123 to pass the test
        String expectedMessage = "123";

        //when
        ArrayList rowsList = consumer.receiveBody("direct:output", ArrayList.class);

        //then
        assertNotNull(rowsList);
        assertNotEquals(0, rowsList.size());
        Map<String, Object> rowContent = (Map<String, Object>) rowsList.get(rowsList.size() - 1);
        Object actualMessage = rowContent.get("message");
        assertEquals(expectedMessage, actualMessage);
    }
}