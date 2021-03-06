package io.github.appuhafeez.tiktoktoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
@EnableEurekaClient
public class TictoctoeHistoryMaintainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TictoctoeHistoryMaintainerApplication.class, args);
	}

}
