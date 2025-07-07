package io.github.smfmo.mscreditassessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MscreditassessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditassessorApplication.class, args);
	}

}
