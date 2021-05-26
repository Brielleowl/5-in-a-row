package com.example.server;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.server.game.GameContainer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)


class ServerApplicationTests {
	// @Autowired
	// private TestRestTemplate restTemplate;

	// @Test
	// void getGameContainerTest() {
	// 	GameContainer response = restTemplate.getForObject("/sendPos", GameContainer.class);
	// 	assertNull(response);
	// }

	

}
