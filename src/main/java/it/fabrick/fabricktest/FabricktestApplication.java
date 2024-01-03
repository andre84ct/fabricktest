package it.fabrick.fabricktest;

import it.fabrick.fabricktest.utils.RuntimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FabricktestApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(FabricktestApplication.class);
		app.setBannerMode(Banner.Mode.LOG);
		app.run(args);

		printReadyInfos();
	}

	private static void printReadyInfos() {
		RuntimeUtils.logRuntimeProperties();
		log.info(">>> STATUS: [READY]");
	}

}
