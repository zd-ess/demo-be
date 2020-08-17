package com.campervans.campervan;

import com.campervans.campervan.controller.RentalController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CampervanApplicationTests {

	@Autowired
	private RentalController rentalController;

	@Test
	void contextLoads() {
		assertThat(rentalController).isNotNull();
	}

}
