package fr.mspr.service;

import fr.mspr.model.entities.User;
import fr.mspr.repositoy.UserRepository;

import fr.mspr.repositoy.UserRepositoryCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	private final UserRepositoryCustom userRepositoryCustom;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository,final BCryptPasswordEncoder passwordEncoder,final UserRepositoryCustom userRepositoryCustom){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepositoryCustom = userRepositoryCustom;
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

    /**
     * Encode le mode de passe avant le traitement en BDD.
     * @param user Le user.
     */
    private void encodeMotDePasseUser(final User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }

	@Override
	public User readFromPseudoAndPassword(final String pseudo, final String pass) {
        final User userFromDataBase = this.userRepository.findUserByPseudoAndPassword(pseudo,pass);
        if(userFromDataBase != null) {
                LOGGER.debug("Recherche acomplie");
                return userFromDataBase;
        }
        return null;
	}

    @Override
    public void updateUser(final User u) {
        this.userRepositoryCustom.updateUser(u.getList_coupon(),u.getPseudo());
    }
}
