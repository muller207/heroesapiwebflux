package br.com.muller.dio.heroesapi;

import br.com.muller.dio.heroesapi.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static br.com.muller.dio.heroesapi.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	public void getSingleHeroById() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL+"/{id}","5")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getSingleHeroByIdNotFound() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL+"/{id}","1")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void deleteSingleHeroById(){
		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL+"/{id}","2")
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);
	}
}
