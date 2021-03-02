package fr.mspr.service;

import fr.mspr.model.entities.Coupon;

public interface CouponService {

    void sauvegardeCoupon(Coupon c);
    Coupon findCouponById(long id);
}
