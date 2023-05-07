package dev.karatay.jsonserver;

import dev.karatay.jsonserver.domain.document.ApiRecord;
import dev.karatay.jsonserver.repository.ApiRecordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JsonserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonserverApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ApiRecordRepository apiRecordRepository) {
		return args -> {
			ApiRecord apiRecord = new ApiRecord();
			apiRecord.setDomain("products");
			apiRecord.setNamespace("karatay-dev");
			apiRecord.setJsonData("[{\"id\": 1, \"emre\": \"karatay\"}, {\"id\": \"35\", \"emre\": \"kara\"}]");
			apiRecord.setId(apiRecord.getNamespace()+"/"+apiRecord.getDomain());
			apiRecordRepository.save(apiRecord);
		};
	}

}
