package com.dm.lcn.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Hongbing.D
 */
@SpringBootApplication
@EnableTransactionManagerServer
@EnableEurekaClient
public class TmBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(TmBootstrap.class, args);
    }
}
