package fr.mspr.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

	@Id
    @Column(name = "id")
    @Getter
    @Setter
    long id;
	
    @Column(name = "pseudo_user")
    @Getter
    @Setter
    String pseudo;
    
    @Column(name = "password")
    @Getter
    @Setter
    String password;
    
    @Column(name = "mail")
    @Getter
    @Setter
    String mail;
    
    @Column(name = "nom")
    @Getter
    @Setter
    String nom;
    
    @Column(name = "prenom")
    @Getter
    @Setter
    String prenom;


    @ManyToMany
	@JoinTable(
	  name = "user_coupon", 
	  joinColumns = @JoinColumn(name ="user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    @Getter
    @Setter
    List<Coupon> listCoupon;
    
    public User(final long id, final String pseudo, final String password, final String mail, final String nom, final String prenom) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(final String pseudo, final String password){
        this.pseudo = pseudo;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", pseudo='" + this.pseudo + '\'' +
                ", mail='" + this.mail + '\'' +
                ", nom='" + this.nom + '\'' +
                ", prenom='" + this.prenom + '\'' +
                ", list_coupon=" + this.listCoupon +
                '}';
    }

    public void ajouterCoupon(final Coupon c){
        this.listCoupon.add(c);
    }
    
    public void removeCoupon(final Coupon c){
        this.listCoupon.remove(c);
    }
}
