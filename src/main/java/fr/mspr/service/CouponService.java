package fr.mspr.service;

import java.util.List;

import fr.mspr.model.entities.Coupon;

public interface CouponService {

    void sauvegardeCoupon(Coupon c);
    Coupon findCouponById(long id);
    List<Coupon> findAll();
}
