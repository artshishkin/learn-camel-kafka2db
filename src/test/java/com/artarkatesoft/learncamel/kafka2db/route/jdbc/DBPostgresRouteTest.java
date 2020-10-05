package com.artarkatesoft.learncamel.kafka2db.route.jdbc;

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


class DBPostgresRouteTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new DBPostgresRoute();
    }

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

    @Test
    void testPersistMessageToDB() {
        //given
        String messageToPersist = "Hello";

        //when
        ArrayList rowsList = template.requestBody("direct:dbInput", messageToPersist, ArrayList.class);

        //then
        assertNotNull(rowsList);
        assertNotEquals(0, rowsList.size());
        Map<String, Object> rowMap = (Map<String, Object>) rowsList.get(rowsList.size() - 1);
        Object actualMessage = rowMap.get("message");
        assertEquals(messageToPersist, actualMessage);
    }
}