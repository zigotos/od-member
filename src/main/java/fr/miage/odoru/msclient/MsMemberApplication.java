package fr.miage.odoru.msclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMemberApplication.class, args);
    }

}
