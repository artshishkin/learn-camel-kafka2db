package com.artarkatesoft.learncamel.kafka2db.launch;

import com.artarkatesoft.learncamel.kafka2db.route.kafka2jdbc.Kafka2JdbcRoute;
import org.apache.camel.main.Main;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class AppLauncher {

    private Main main;

    public static void main(String[] args) throws Exception {
        AppLauncher appLauncher = new AppLauncher();
        appLauncher.boot();
    }


    public void boot() throws Exception {
        // create a Main instance
        main = new Main();

        DataSource dataSource = setupDataSource();

        main.bind("myDataSource", dataSource);

        // enable hangup support so you can press ctrl + c to terminate the JVM
//        main.enableHangupSupport();
        // add routes
        main.configure().addRoutesBuilder(new Kafka2JdbcRoute());

        // run until you terminate the JVM
        System.out.println("Starting Camel Kafka to DB Route. Use ctrl + c to terminate the JVM.\n");
        main.run();
    }

    private DataSource setupDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/learn_camel");
        dataSource.setUser("postgres");
        dataSource.setPassword("123");
        return dataSource;
    }
}
