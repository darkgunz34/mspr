package fr.mspr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.mspr.service.PartenaireService;

@RestController
public class PartenaireController {
	
private static final Logger LOGGER = LogManager.getLogger(PartenaireController.class);
	
	@Autowired
	private PartenaireService partenaireService;
	
	@GetMapping(value = "/partenaires")
	public ResponseEntity<Object> getCoupons() {
		LOGGER.debug("partenaires request()");
		return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
	}

}
