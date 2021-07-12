package pl.graczykmateusz.StringCalculatorKata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StringCalculatorKataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StringCalculatorKataApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		StringCalculator stringCalculator = new StringCalculator();
		stringCalculator.add("1,1");
	}
}
