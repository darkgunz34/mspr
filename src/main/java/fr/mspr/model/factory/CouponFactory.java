package fr.mspr.model.factory;

import fr.mspr.model.entities.Coupon;
import fr.mspr.model.entities.Partenaire;
import fr.mspr.model.entities.User;
import fr.mspr.model.exception.CouponException;
import fr.mspr.model.exception.MessageErreur;
import fr.mspr.model.exception.PartenaireException;
import fr.mspr.model.exception.UserException;

public final class CouponFactory {
    private CouponFactory(){
    }

    public static Coupon getCouponEmpty(){
        return new Coupon();
    }

    public static Coupon getCouponFromParam(final long id, final String nom, final String motif, final String code, final String date, final boolean valide, final User u, final Partenaire p) throws CouponException,UserException,PartenaireException{
        champNonVide(id);
        champNonVide(nom);
        champNonVide(motif);
        champNonVide(code);
        champNonVide(date);
        champNonVide(u);
        champNonVide(p);
        return new Coupon(id,nom,motif,code, date,valide,u,p);
    }

    private static void champNonVide(final String chaine) throws CouponException {
        if(chaine == null || chaine.trim().isEmpty()){
            throw new CouponException(MessageErreur.CHAMP_VIDE);
        }
    }

    private static void champNonVide(final long id) throws CouponException{
        if(id == 0){
            throw new CouponException(MessageErreur.CHAMP_VIDE);
        }
    }

    private static void champNonVide(final User u) throws UserException{
        if(u == null){
            throw new UserException(MessageErreur.CHAMP_VIDE);
        }
    }

    private static void champNonVide(final Partenaire p) throws PartenaireException {
        if(p == null){
            throw new PartenaireException(MessageErreur.CHAMP_VIDE);
        }
    }

}
