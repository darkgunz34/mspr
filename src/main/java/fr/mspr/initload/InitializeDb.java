package fr.mspr.initload;

import fr.mspr.model.entities.Coupon;
import fr.mspr.model.entities.Partenaire;
import fr.mspr.model.entities.User;
import fr.mspr.model.exception.CouponException;
import fr.mspr.model.exception.CustomGetMessage;
import fr.mspr.model.exception.PartenaireException;
import fr.mspr.model.exception.UserException;
import fr.mspr.model.factory.CouponFactory;
import fr.mspr.model.factory.PartenaireFactory;
import fr.mspr.model.factory.UserFactory;
import fr.mspr.service.CouponService;
import fr.mspr.service.PartenaireService;
import fr.mspr.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitializeDb {

    private static final Logger LOGGER = LogManager.getLogger(InitializeDb.class);

    private final CouponService couponService;
    private final UserService userService;
    private final PartenaireService partenaireService;

    private static final String PASSWORD_COMPTE = "Azerty01";

    public InitializeDb(final CouponService couponService, final UserService userService, final PartenaireService partenaireService) {
        this.couponService = couponService;
        this.userService = userService;
        this.partenaireService = partenaireService;
    }

    @PostConstruct
    public void init() {
        this.initUser();
        final List<Partenaire> lstPartenaire = this.initPartenaire();
        this.initCoupon(lstPartenaire);
    }

    private void initCoupon(final List<Partenaire> lstPartenaire) {
        final List<Coupon> lstCoupon = new ArrayList<>();
        try {
            lstCoupon.add(CouponFactory.getCouponFromParam(1, "coupon 1", "reduction 10 %", "AB7584", "15 février 2022", true, lstPartenaire.get(0)));
            lstCoupon.add(CouponFactory.getCouponFromParam(2, "coupon 2", "reduction 20 %", "AB6784", "15 décembre 2021", true, lstPartenaire.get(0)));
            lstCoupon.add(CouponFactory.getCouponFromParam(3, "coupon 3", "reduction sur les ordinateurs", "TE7784", "15 janvier 2022", true, lstPartenaire.get(2)));
            lstCoupon.add(CouponFactory.getCouponFromParam(4, "coupon 4", "bon d'achat de 10 €", "AD7784", "10 février 2021", false, lstPartenaire.get(1)));
            lstCoupon.add(CouponFactory.getCouponFromParam(5, "coupon 5", "reduction - 30 % sur les écrans", "AZ5784", "28 février 2018", false, lstPartenaire.get(2)));
        } catch (final CouponException couponException) {
            LOGGER.error(CustomGetMessage.recuperationDerniereLigneException("Le coupon contient une données vide : ",couponException.getStackTrace(),3));
        } catch (final PartenaireException partenaireException) {
            LOGGER.error(CustomGetMessage.recuperationDerniereLigneException("Le partenaire est null : ",partenaireException.getStackTrace(),3));
        }
        lstCoupon.forEach(this.couponService::sauvegardeCoupon);
    }

    private void initUser() {
        final List<User> lstUser = new ArrayList<>();
        try {
            lstUser.add(UserFactory.getUserFromParamWithId(1, "stephan", PASSWORD_COMPTE, "stephan.parichon@epsi.fr", "Stéphan", "Parichon"));
            lstUser.add(UserFactory.getUserFromParamWithId(2, "george", PASSWORD_COMPTE, "georges.garnier@epsi.fr", "George", "Garnier"));
            lstUser.add(UserFactory.getUserFromParamWithId(3, "chris", PASSWORD_COMPTE, "chris.domingues@epsi.fr", "Chris", "Domingues"));
            lstUser.add(UserFactory.getUserFromParamWithId(4, "jeffrey", PASSWORD_COMPTE, "jeffrey.fevre@epsi.fr", "jeffrey", "fevre"));
        } catch (final UserException userException) {
            LOGGER.error(CustomGetMessage.recuperationDerniereLigneException("Le user contient une données vide : ",userException.getStackTrace(),3));
        }
        lstUser.forEach(this.userService::sauvegardeUser);
    }

    private List<Partenaire> initPartenaire() {
        final List<Partenaire> lstPartenaires = new ArrayList<>();
        try {
            lstPartenaires.add(PartenaireFactory.getPartenaireFromParamWithId(1, "adidas", "Rue de l'adidas"));
            lstPartenaires.add(PartenaireFactory.getPartenaireFromParamWithId(2, "nike", "Rue de l'nike"));
            lstPartenaires.add(PartenaireFactory.getPartenaireFromParamWithId(3, "reebok", "Rue de reebok"));
            lstPartenaires.forEach(this.partenaireService::sauvegardePartenaire);
        } catch (final PartenaireException partenaireException) {
            LOGGER.error(CustomGetMessage.recuperationDerniereLigneException("Le partenaire contient une données vide : ",partenaireException.getStackTrace(),3));
        }
        return lstPartenaires;
    }
}
