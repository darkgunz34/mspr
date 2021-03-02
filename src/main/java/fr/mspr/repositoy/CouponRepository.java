package fr.mspr.repositoy;

import fr.mspr.model.entities.Coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
	List<Coupon> findAllCouponsByUserId(long id);
	Coupon findCouponById(long id);
}
