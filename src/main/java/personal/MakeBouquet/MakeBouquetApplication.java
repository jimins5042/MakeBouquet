package personal.MakeBouquet;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MakeBouquetApplication {

	public static void main(String[] args) {
		try {
			Dotenv dotenv = Dotenv.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(MakeBouquetApplication.class, args);
	}

}
