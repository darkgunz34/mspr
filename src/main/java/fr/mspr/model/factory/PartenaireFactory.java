package fr.mspr.model.factory;

import fr.mspr.model.entities.Partenaire;
import fr.mspr.model.exception.MessageErreur;
import fr.mspr.model.exception.PartenaireException;

public final class PartenaireFactory {
    private PartenaireFactory(){
    }

    public static Partenaire getPartenaireEmpty(){
        return new Partenaire();
    }

    public static Partenaire getPartenaireFromParamWithId(final long id, final String nom, final String adresse) throws PartenaireException{
        champNonVide(id);
        champNonVide(nom);
        champNonVide(adresse);
        return new Partenaire(id,nom,adresse);
    }

    private static void champNonVide(final String chaine) throws PartenaireException {
        if(chaine == null || chaine.trim().isEmpty()){
            throw new PartenaireException(MessageErreur.CHAMP_VIDE);
        }
    }

    private static void champNonVide(final long id) throws PartenaireException{
        if(id == 0){
            throw new PartenaireException(MessageErreur.CHAMP_VIDE);
        }
    }
}
