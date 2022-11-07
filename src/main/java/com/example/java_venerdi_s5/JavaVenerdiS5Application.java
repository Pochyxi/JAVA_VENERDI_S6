package com.example.java_venerdi_s5;


import ch.qos.logback.classic.*;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JavaVenerdiS5Application implements CommandLineRunner {

     Logger logger = ( Logger ) LoggerFactory.getLogger( JavaVenerdiS5Application.class );

    public static void main( String[] args ) {
        SpringApplication.run( JavaVenerdiS5Application.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {
        logger.info( "APP READY!" );
    }
}
