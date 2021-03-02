package fr.mspr.service;

import fr.mspr.model.entities.Coupon;
import fr.mspr.repositoy.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService{

    private final CouponRepository couponRepository;

    public CouponServiceImpl(final CouponRepository couponRepository){
        this.couponRepository = couponRepository;
    }

    @Override
    public void sauvegardeCoupon(final Coupon c) {
        this.couponRepository.save(c);
    }
    
    @Override
    public Coupon findCouponById(final long id) {
    	return this.couponRepository.findCouponById(id);
    }
}
