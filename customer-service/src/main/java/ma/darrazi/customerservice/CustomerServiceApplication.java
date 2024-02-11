package ma.darrazi.customerservice;

import ma.darrazi.customerservice.config.GlobalConfig;
import ma.darrazi.customerservice.entities.Customer;
import ma.darrazi.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			List<Customer> customerList	= List.of(
					Customer.builder()
							.firstName("Michael")
							.lastName("Scott")
							.email("scott@hotmail.com")
							.build(),
					Customer.builder()
							.firstName("Jim")
							.lastName("Halpert")
							.email("jim@hotmail.com")
							.build()
			);
			customerRepository.saveAll(customerList);
		};
	}

}
