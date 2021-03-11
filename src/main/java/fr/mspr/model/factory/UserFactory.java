package fr.mspr.model.factory;

import fr.mspr.model.entities.User;
import fr.mspr.model.exception.UserException;
import fr.mspr.model.utils.UtilsEntity;

public class UserFactory {
    private UserFactory(){
    }

    public static User getUserEmpty(){
        return new User();
    }

    public static User getUserWithPseudoAndPassword(final String pseudo, final String password) throws UserException{
        UtilsEntity.champNonVideUser(password);
        UtilsEntity.champNonVideUser(pseudo);
        return new User(pseudo,password);
    }

    public static User getUserFromParamWithId(final long id, final String pseudo, final String password, final String mail, final String prenom, final String nom) throws UserException{
        UtilsEntity.nonValideMailUser(mail);
        UtilsEntity.champNonVideUser(id);
        UtilsEntity.champNonVideUser(prenom);
        UtilsEntity.champNonVideUser(nom);
        UtilsEntity.champNonVideUser(password);
        UtilsEntity.champNonVideUser(pseudo);
        return new User(id,pseudo,password,mail,nom,prenom);
    }
}
