package fr.mspr.model.factory;

import fr.mspr.model.constante.UserConstante;
import fr.mspr.model.entities.User;
import fr.mspr.model.exception.MessageErreur;
import fr.mspr.model.exception.UserException;

public final class UserFactory {
    private UserFactory(){
    }

    public static User getUserEmpty(){
        return new User();
    }

    public static User getUserWithPseudoAndPassword(final String pseudo, final String password) throws UserException{
        champNonVide(password);
        champNonVide(pseudo);
        return new User(pseudo,password);
    }

    public static User getUserFromParamWithId(final long id, final String pseudo, final String password, final String mail, final String prenom, final String nom) throws UserException{
        nonValideMail(mail);
        champNonVide(id);
        champNonVide(prenom);
        champNonVide(nom);
        champNonVide(password);
        champNonVide(pseudo);
        return new User(id,pseudo,password,mail,nom,prenom);
    }

    private static void nonValideMail(final String mail) throws UserException{
        champNonVide(mail);
        if (!mail.matches(UserConstante.REGEX_VALIDATION_MAL)){
            throw new UserException(MessageErreur.MAIL_INVALIDE);
        }
    }

    private static void champNonVide(final String chaine) throws UserException{
        if(chaine == null || chaine.trim().isEmpty()){
            throw new UserException(MessageErreur.CHAMP_VIDE);
        }
    }

    private static void champNonVide(final long id) throws UserException{
        if(id == 0){
            throw new UserException(MessageErreur.CHAMP_VIDE);
        }
    }
}
