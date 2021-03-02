package fr.mspr.service;

import fr.mspr.model.entities.User;

public interface UserService {

    void sauvegardeUser(User u);
    User readFromKey(long key);
    User readFromPseudoAndPassword(String pseudo, String pass);

    void updateUser(User u);

    boolean exist(long key);
}
