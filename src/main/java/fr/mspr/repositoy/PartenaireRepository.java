package fr.mspr.repositoy;

import fr.mspr.model.entities.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartenaireRepository extends JpaRepository<Partenaire,Long> {
}
