package fr.mspr.model.factory;

import fr.mspr.model.entities.Coupon;
import fr.mspr.model.entities.Partenaire;
import fr.mspr.model.exception.CouponException;
import fr.mspr.model.exception.PartenaireException;
import fr.mspr.model.utils.UtilsEntity;

public final class CouponFactory {
    private CouponFactory(){
    }

    public static Coupon getCouponEmpty(){
        return new Coupon();
    }

    public static Coupon getCouponFromParam(final long id, final String nom, final String motif, final String code, final String date, final boolean valide, final Partenaire p) throws CouponException,PartenaireException{
        UtilsEntity.champNonVideCoupon(id);
        UtilsEntity.champNonVideCoupon(nom);
        UtilsEntity.champNonVideCoupon(motif);
        UtilsEntity.champNonVideCoupon(code);
        UtilsEntity.champNonVideCoupon(date);
        UtilsEntity.champNonVideCoupon(p);
        return new Coupon(id,nom,motif,code, date,valide,p);
    }
}
