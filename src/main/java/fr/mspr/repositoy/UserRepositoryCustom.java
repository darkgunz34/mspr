package fr.mspr.repositoy;


import fr.mspr.model.entities.Coupon;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void updateUser(final List<Coupon> lst, final String pseudo) {
		final Query query = this.entityManager.createQuery("UPDATE User u set u.list_coupon = :lst where u.pseudo = :pseudo");
		query.setParameter("lst",lst);
		query.setParameter("pseudo",pseudo);
		query.executeUpdate();
	}
}