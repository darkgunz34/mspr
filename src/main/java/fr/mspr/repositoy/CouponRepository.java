package fr.mspr.repositoy;

import fr.mspr.model.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
	Coupon findCouponById(long id);
}
