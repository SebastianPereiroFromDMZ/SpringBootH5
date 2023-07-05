package ru.netology.springbooth5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootH5ApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        prodApp.start();
        devApp.start();
    }

    @Test
    void contextProdApp() {
        ResponseEntity<String> entityFromProdApp = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
        System.out.println(entityFromProdApp.getBody());

        String result = entityFromProdApp.getBody();

        String expected = "Current profile is production";

        assertEquals(expected, result);
    }

    @Test
    void contextDevApp() {
        ResponseEntity<String> entityFromDevApp = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
        System.out.println(entityFromDevApp.getBody());

        String result = entityFromDevApp.getBody();

        String expected = "Current profile is dev";

        assertEquals(expected, result);
    }


}
