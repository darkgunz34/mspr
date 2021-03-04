package fr.mspr.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.mspr.service.CouponService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {
	
	private static final Logger LOGGER = LogManager.getLogger(CouponController.class);
	
	@Autowired
	private CouponService couponService;
	
	@GetMapping(value = "/coupons")
	public ResponseEntity<Object> getCoupons() {
		LOGGER.debug("coupons request()");
		return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
	}

}
