package com.caveofprogramming.spring.web.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.service.OffersService;

/**
 * @author 227761
 *
 */
@RestController
@RequestMapping("/offers")
public class OffersController {
	Logger logger = LoggerFactory.getLogger(OffersController.class);
	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Offer> showOffers() {
		List<Offer> offers = offersService.getCurrent();
		return offers;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody Map<String, String> params) {
		logger.debug("created");
		Offer offer = new Offer(params.get("name"), params.get("email"), params.get("text"));
		offersService.create(offer);
		logger.debug("After Offer Service");
		return "Created :" + offer.getId();
	}
}
