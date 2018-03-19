package lk.inova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class },scanBasePackages= {"lk.inova.config","lk.inova.rest","lk.inova.dao","lk.inova.txn","lk.inova.sbeans"}) 

public class Main {

	
	public static void main(String[] args) {

		System.setProperty("spring.config.name", "db-config-dev,db-config-pro,application,metadataConfig");
		System.setProperty("spring.profiles.active", "dev");
		SpringApplication.run(Main.class, args);

	}

}
