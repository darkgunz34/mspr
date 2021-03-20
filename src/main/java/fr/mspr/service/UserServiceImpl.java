package fr.mspr.service;

import fr.mspr.model.entities.User;
import fr.mspr.repositoy.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final String HASH = "mspr";

    public UserServiceImpl(final UserRepository userRepository, final BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void sauvegardeUser(final User u) {
        this.encodeMotDePasseUser(u);
        this.userRepository.save(u);
    }

    @Override
    public User readFromKey(final long key) {
        if(this.exist(key)){
            final Optional<User> user = this.userRepository.findById(key);
            if(user.isPresent()){
                return user.get();
            }
        }
        return null;
    }

    @Override
    public boolean exist(final long key){
        return this.userRepository.findById(key).isPresent();
    }

    private String hashPassword(final String password){
        return String.format("%s %s",password, HASH);
    }
    /**
     * Encode le mode de passe avant le traitement en BDD.
     * @param user Le user.
     */
    private void encodeMotDePasseUser(final User user){
        user.setPassword(this.passwordEncoder.encode(this.hashPassword(user.getPassword())));
    }

    /**
     * Compare le mot de passe avec celui de la BDD.
     * @param userFromDataBase Le user issu de la bdd.
     * @return true si les passwords sont identiques.
     */
    private boolean decodeMotDePasseUser(final String password, final User userFromDataBase){
        return this.passwordEncoder.matches(this.hashPassword(password),userFromDataBase.getPassword());
    }

	@Override
	public User readFromPseudoAndPassword(final String pseudo, final String pass) {
        final User userFromDataBase = this.userRepository.findUserByPseudo(pseudo);
        if(userFromDataBase != null && this.decodeMotDePasseUser(pass,userFromDataBase)) {
            LOGGER.debug("Recherche acomplie");
                return userFromDataBase;
        }
        return null;
	}

    @Override
    public void updateUser(final User u) {
        this.userRepository.save(u);
    }
}
