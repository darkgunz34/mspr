package fr.mspr.controller;

import fr.mspr.model.exception.UserException;
import fr.mspr.model.utils.UtilsEntity;
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

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	private final UserService userService;
	private final CouponService couponService;
	private static final String KEY_COOKIE = "moncookieperso";

	public UserController(final UserService userService, final CouponService couponService) {
		LOGGER.debug("UserController()");
		this.userService = userService;
		this.couponService = couponService;
	}

	@GetMapping(value = "/getuser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> seconnecter(@RequestParam(value = "pseudo", defaultValue = "")final String pseudo, @RequestParam(value = "password", defaultValue = "") final String password,final HttpSession session) {
		LOGGER.debug("seconnecter()");
		try {
			UtilsEntity.champNonVideUser(pseudo);
			UtilsEntity.champNonVideUser(password);
			final User u = this.userService.readFromPseudoAndPassword(pseudo,password);
			if(u==null){
				throw new UserException("User inexistant en bdd.");
			}
			LOGGER.debug("user trouvé");
			session.setAttribute("key","moncookieperso" + u.getId());
			return new ResponseEntity<>(u, HttpStatus.FOUND);
		} catch (final UserException e) {
			final String  str = "user non trouvé : " + e.getMessage();
			LOGGER.debug(str);
			return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/addCoupon")
	public ResponseEntity<Object> addCouponIntoListUser(@RequestParam(value = "idUser", defaultValue = "")final long idUser,@RequestParam(value = "idCoupon", defaultValue = "") final long idCoupon,final HttpSession session) {
		final String s =(String) session.getAttribute("key");
		if(s!= null && s.equals(KEY_COOKIE + idUser)) {
			LOGGER.debug("addCouponIntoListUser");
			LOGGER.debug(s);

			final User u = this.userService.readFromKey(idUser);
			final Coupon c = this.couponService.findCouponById(idCoupon);
			
			if( UtilsEntity.userOrCouponIsNull(u, c, idCoupon)) {
				 return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			} else {
				u.ajouterCoupon(c);
				this.userService.updateUser(u);
				return new ResponseEntity<>("", HttpStatus.OK);
			}
		}
		else{
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping(value = "/delCoupon")
	public ResponseEntity<Object> delCouponIntoListUser(@RequestParam(value = "idUser", defaultValue = "")final long idUser,@RequestParam(value = "idCoupon", defaultValue = "") final long idCoupon,final HttpSession session) {
		final String s =(String) session.getAttribute("key");
		if(s!= null && s.equals(KEY_COOKIE + idUser)) {
			
			LOGGER.debug("delCouponIntoListUser");
			LOGGER.debug(s);
			
			final User u = this.userService.readFromKey(idUser);
			final Coupon c = this.couponService.findCouponById(idCoupon);
			
			if( UtilsEntity.userOrCouponIsNull(u, c, idCoupon)) {
				 return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			} else {
				// verifier que le coupon existe bien dans la liste de coupon du user
				/*u.getListCoupon().forEach(e -> {
					if( e.getId() == idCoupon) {
						u.removeCoupon(c);
						}});*/
				u.removeCoupon(c);
				this.userService.updateUser(u);
				return new ResponseEntity<>("", HttpStatus.OK);	
			}	
		}
		else{
			return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(value = "/logout")
	public void seDeconnecter(final HttpSession session) {
		LOGGER.debug("seDeconnecter()");
		final String s =(String) session.getAttribute("key");
		if(s!= null) {
			session.removeAttribute("key");
		}
	}
	
}
