package fr.mspr.model.factory;

import fr.mspr.model.entities.Partenaire;
import fr.mspr.model.exception.PartenaireException;
import fr.mspr.model.utils.UtilsEntity;

public final class PartenaireFactory {
    private PartenaireFactory(){
    }

    public static Partenaire getPartenaireEmpty(){
        return new Partenaire();
    }

    public static Partenaire getPartenaireFromParamWithId(final long id, final String nom, final String adresse) throws PartenaireException{
        UtilsEntity.champNonVidePartenaire(id);
        UtilsEntity.champNonVidePartenaire(nom);
        UtilsEntity.champNonVidePartenaire(adresse);
        return new Partenaire(id,nom,adresse);
    }
}
