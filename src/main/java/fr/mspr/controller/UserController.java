package fr.mspr.controller;

import fr.mspr.model.exception.UserException;
import fr.mspr.model.factory.UserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.mspr.model.entities.Coupon;
import fr.mspr.model.entities.User;
import fr.mspr.service.UserService;
import fr.mspr.service.CouponService;

@RestController
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	private final UserService userService;
	private final CouponService couponService;

	public UserController(final UserService userService, final CouponService couponService) {
		LOGGER.debug("UserController()");
		this.userService = userService;
		this.couponService = couponService;
	}

	@GetMapping(value = "/getuser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> seconnecter(@RequestParam(value = "pseudo", defaultValue = "")final String pseudo, @RequestParam(value = "password", defaultValue = "") final String password) {
		LOGGER.debug("seconnecter()");
		try {
			User u = UserFactory.getUserWithPseudoAndPassword(pseudo, password);
			u = this.userService.readFromPseudoAndPassword(u.getPseudo(), u.getPassword());
			LOGGER.debug("user trouvé");
			return new ResponseEntity<>(u, HttpStatus.FOUND);
		} catch (final UserException e) {
			LOGGER.debug("user non trouvé");
			return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/addCoupon", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addCouponIntoListUser(@RequestParam(value = "id_user", defaultValue = "")final long id_user,@RequestParam(value = "id_coupon", defaultValue = "") final long id_coupon) {
		LOGGER.debug("addCouponIntoListUser");
		final User u = this.userService.readFromKey(id_user);
		if (u == null) {
			final String  str = "User non trouvé avec l'id : " + id_coupon;
			LOGGER.debug(str);
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		}
		final Coupon c = this.couponService.findCouponById(id_coupon);
		if (c == null) {
			final String  str = "Coupon non trouvé avec l'id : " + id_coupon;
			LOGGER.debug(str);
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		}
		u.ajouterCoupon(c);
		this.userService.updateUser(u);
		return null;
	}
}
