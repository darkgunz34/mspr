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
@ToString(of = {"id","nom","adresse"})
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

    public Partenaire(final long id, final String nom, final String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }
}
