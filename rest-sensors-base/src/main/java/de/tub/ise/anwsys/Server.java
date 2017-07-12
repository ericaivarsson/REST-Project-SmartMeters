package de.tub.ise.anwsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("de.tub.ise.anwsys")
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

}
// @Bean
// CommandLineRunner init(UserRepository userRepo, ProductRepository
// productRepo) {
// return (evt) -> Arrays.asList("demo,test,bestvalue".split(",")).forEach(name
// -> {
// userRepo.save(new User(name));
// });
// Arrays.asList("towel:42,bag:31".split(",")).forEach(dataEntry -> {
// String[] data = dataEntry.split(",");
// double price = Double.parseDouble(data[0]);
// productRepo.save(new Product(data[0], price));
// });
// };
//
// }