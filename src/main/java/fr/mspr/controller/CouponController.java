package fr.mspr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.mspr.service.CouponService;

@RestController
public final class CouponController {
	
	private static final Logger LOGGER = LogManager.getLogger(CouponController.class);
	
	private CouponService couponService;
	
	public CouponController(final CouponService couponService) {
		LOGGER.debug("CouponController()");
		this.couponService = couponService;
	}
	
	@GetMapping(value = "/getcoupons", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> seconnecter() {
			LOGGER.debug("getcoupons()");
			LOGGER.debug("coupons trouvé ");
			return new ResponseEntity<>(couponService.findAll(), HttpStatus.FOUND);
	}

}
