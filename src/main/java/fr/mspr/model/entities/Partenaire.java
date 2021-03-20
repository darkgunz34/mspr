package fr.mspr.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "partenaire")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Partenaire {

	@Id
    @Column(name = "id_partenaire")
    @Getter
    @Setter
    long id;
	
    @Column(name = "nom_partenaire")
    @Getter
    @Setter
    String nom;
	

    @Column(name = "adresse_partenaire")
    @Getter
    @Setter
    String adresse;

    public Partenaire(long id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Partenaire{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
