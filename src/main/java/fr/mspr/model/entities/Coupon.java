package fr.mspr.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "coupon")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coupon {

    @Id
    @Column(name = "coupon_id")
    @Getter
    @Setter
    long id;

    @Column(name = "coupon_nom")
    @Getter
    @Setter
    String nom;

    @Column(name = "coupon_motif")
    @Getter
    @Setter
    String motif;

    @Column(name = "coupon_code")
    @Getter
    @Setter
    String code;

    @Column(name = "coupon_date_validation")
    @Getter
    @Setter
    String dateValidation;

    @Column(name = "coupon_valide")
    @Getter
    @Setter
    boolean valide;

    @JoinColumn(name = "coupon_partenaire")
    @OneToOne
    @Getter
    @Setter
    Partenaire partenaire;

    public Coupon(final long id, final String nom, final String motif, final String code, final String dateValidation, final boolean valide, final Partenaire partenaire) {
        this.id = id;
        this.nom = nom;
        this.motif = motif;
        this.code = code;
        this.dateValidation = dateValidation;
        this.valide = valide;
        this.partenaire = partenaire;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + this.id +
                ", nom='" + this.nom + '\'' +
                ", motif='" + this.motif + '\'' +
                ", code='" + this.code + '\'' +
                ", dateValidation='" + this.dateValidation + '\'' +
                ", valide=" + this.valide +
                ", partenaire=" + this.partenaire +
                '}';
    }
}
