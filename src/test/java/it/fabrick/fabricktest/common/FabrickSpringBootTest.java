package it.fabrick.fabricktest.common;

import it.fabrick.fabricktest.FabricktestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.*;

@SpringBootTest(classes = FabricktestApplication.class)
@TestPropertySource(locations = "classpath:application.yml")

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

@Inherited
public @interface FabrickSpringBootTest {
}
