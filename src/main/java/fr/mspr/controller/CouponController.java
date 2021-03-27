package fr.mspr.controller;

import fr.mspr.model.entities.Coupon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.mspr.service.CouponService;

@RestController
public final class CouponController {
	
	private static final Logger LOGGER = LogManager.getLogger(CouponController.class);
	
	private final CouponService couponService;
	
	public CouponController(final CouponService couponService) {
		LOGGER.debug("CouponController()");
		this.couponService = couponService;
	}
	
	@GetMapping(value = "/getcoupons", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getcoupons() {
		LOGGER.debug("getcoupons()");
		LOGGER.debug("coupons trouvé ");
			return new ResponseEntity<>(couponService.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping(value= "/getcoupon", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getcoupon(@RequestParam("idCoupon") final Long idCoupon) {
		LOGGER.debug("getcouponsById()");
		Coupon c = couponService.findCouponById(idCoupon);
		if(c != null){
			return new ResponseEntity<>(c, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
	}

}
