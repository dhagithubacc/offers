package com.caveofprogramming.spring.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.caveofprogramming.spring.web.dao.Offer;
import com.google.gson.Gson;

/**
 * @author 227761
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OffersControllerTest {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testCreatedOfferSuccess() throws Exception {

		Offer offer = new Offer();
		offer.setName("Testtman");
		offer.setEmail("ddhara10@gmail.com");
		offer.setText("NewOffer");
		Gson gson = new Gson();
		String json = gson.toJson(offer);
		mockMvc.perform(post("/offers")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json))
		 		.andReturn();

	}

	@Test
	public void testShowOffersSuccess() throws Exception {

		mockMvc.perform(get("/offers").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

}
