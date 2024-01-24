package it.fabrick.fabricktest;

import it.fabrick.fabricktest.common.FabrickSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@FabrickSpringBootTest
class FabricktestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void applicationStartTest() {

		FabricktestApplication.main(new String[] {});

		Assertions.assertDoesNotThrow(FabricktestApplication::printReadyInfos);
	}

}
