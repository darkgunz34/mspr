package fr.mspr.model.utils;

import fr.mspr.model.constante.UserConstante;
import fr.mspr.model.entities.Coupon;
import fr.mspr.model.entities.Partenaire;
import fr.mspr.model.entities.User;
import fr.mspr.model.exception.CouponException;
import fr.mspr.model.exception.MessageErreur;
import fr.mspr.model.exception.PartenaireException;
import fr.mspr.model.exception.UserException;

public final class UtilsEntity {

    private UtilsEntity(){

    }

    public static void champNonVideUser(final String chaine) throws UserException {
        if(chaine == null || chaine.trim().isEmpty()){
            throw new UserException(MessageErreur.CHAMP_VIDE);
        }
    }

    public static void nonValideMailUser(final String mail) throws UserException{
        champNonVideUser(mail);
        if (!mail.matches(UserConstante.REGEX_VALIDATION_MAL)){
            throw new UserException(MessageErreur.MAIL_INVALIDE);
        }
    }

    public static void champNonVideUser(final long id) throws UserException{
        if(id == 0){
            throw new UserException(MessageErreur.CHAMP_VIDE);
        }
    }

    public static void champNonVidePartenaire(final String chaine) throws PartenaireException {
        if(chaine == null || chaine.trim().isEmpty()){
            throw new PartenaireException(MessageErreur.CHAMP_VIDE);
        }
    }

    public static void champNonVidePartenaire(final long id) throws PartenaireException{
        if(id == 0){
            throw new PartenaireException(MessageErreur.CHAMP_VIDE);
        }
    }

    public static void champNonVideCoupon(final String chaine) throws CouponException {
        if(chaine == null || chaine.trim().isEmpty()){
            throw new CouponException(MessageErreur.CHAMP_VIDE);
        }
    }

    public static void champNonVideCoupon(final long id) throws CouponException{
        if(id == 0){
            throw new CouponException(MessageErreur.CHAMP_VIDE);
        }
    }

    public static void champNonVideCoupon(final Partenaire p) throws PartenaireException {
        if(p == null){
            throw new PartenaireException(MessageErreur.CHAMP_VIDE);
        }
    }
    
    public static boolean userOrCouponIsNull(final User u, final Coupon c) {
        return u == null || c == null;
    }
    
}
