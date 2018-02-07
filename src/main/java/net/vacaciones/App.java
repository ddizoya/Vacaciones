package net.vacaciones;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

@ComponentScan("net.vacaciones")
@EntityScan
@SpringBootApplication
@EnableAutoConfiguration
public class App implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void run(String... arg0) throws Exception {

		String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean);
		}

	}

}