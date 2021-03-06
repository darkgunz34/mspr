package fr.mspr.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.mspr.model.entities.User;
import fr.mspr.model.exception.UserException;
import fr.mspr.service.CouponService;
import fr.mspr.service.UserService;

@RestController
public class CouponController {
	
	private static final Logger LOGGER = LogManager.getLogger(CouponController.class);
	
	private static final String KEY_COOKIE = "moncookieperso";
	
	private CouponService couponService;
	private final UserService userService;
	
	public CouponController(final UserService userService, final CouponService couponService) {
		LOGGER.debug("CouponController()");
		this.userService = userService;
		this.couponService = couponService;
	}
	
	@GetMapping(value = "/getcoupons", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> seconnecter(@RequestParam(value = "idUser", defaultValue = "")final long idUser, final HttpSession session) {
		final String s =(String) session.getAttribute("key");
		System.out.println(s);
		if(s!= null && s.equals(KEY_COOKIE + idUser)) {
			LOGGER.debug("getcoupons()");
			try {	
				final User u = this.userService.readFromKey(idUser);
				if(u==null){
					throw new UserException("User inexistant en bdd.");
				}
				LOGGER.debug("user trouvé");
				return new ResponseEntity<>(u.getListCoupon(), HttpStatus.FOUND);
			} catch (final UserException e) {
				final String  str = "user non trouvé : " + e.getMessage();
				LOGGER.debug(str);
				return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
			}
			
		}
		return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		
	}

}
