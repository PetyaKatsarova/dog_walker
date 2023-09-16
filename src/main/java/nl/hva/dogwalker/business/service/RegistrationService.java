package nl.hva.dogwalker.business.service;

import nl.hva.dogwalker.business.domain.User;
import nl.hva.dogwalker.persistence.dao.JdbcUserDao;
import nl.hva.dogwalker.persistence.repository.UserRepository;
import nl.hva.dogwalker.util.password.PasswordCheckerService;
import nl.hva.dogwalker.util.security.HashAndSaltUtil;
import nl.hva.dogwalker.util.security.HashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import nl.hva.dogwalker.communication.dto.RegistrationDTO;
import nl.hva.dogwalker.exception.PasswordNotValidException;
import nl.hva.dogwalker.exception.UserExistsException;

import java.util.Optional;

import static nl.hva.dogwalker.util.security.HashAndSaltUtil.*;

@Service
public class RegistrationService {
    private final Logger                    logger = LoggerFactory.getLogger(RegistrationService.class);
    private final JdbcUserDao               jdbcUserDao;
    private final HashService               hashService;
    private final PasswordCheckerService    passwordCheckerService;

    public RegistrationService(JdbcUserDao jdbcUserDao, HashService hashService, PasswordCheckerService passwordCheckerService) {
        this.jdbcUserDao = jdbcUserDao;
        this.hashService = hashService;
        this.passwordCheckerService = passwordCheckerService;
        logger.info("New RegistrationService");
    }

    public User register(String email, String password) throws UserExistsException, PasswordNotValidException {
        Optional<User> existingUserOpt = jdbcUserDao.findUserByEmail(email);
        logger.info("registration service register: " + existingUserOpt);

        if (existingUserOpt.isPresent())
            throw new UserExistsException("User with email " + email + " already exists");
        if (!passwordCheckerService.isPasswordValid(password))
            throw new PasswordNotValidException("Password is not valid");

        // Create a new user with hashed password
        User newUser = userWithHashedPassword(password, email);
        System.out.println("new user in registrationService: " + newUser);
        return jdbcUserDao.saveUser(newUser);
    }

    protected User userWithHashedPassword(String password, String email) {
        String hashedPassword = hashService.hash(password);
        String salt = getSaltFromHashSalt(hashedPassword);
        String passwordHash = getHashFromHashSalt(hashedPassword);
        return new User(email, passwordHash, salt);
    }

    private String getSaltFromHashSalt(String hashedPassword) {
        return HashAndSaltUtil.getSaltFromHashSalt(hashedPassword);
    }

    private String getHashFromHashSalt(String hashedPassword) {
        return HashAndSaltUtil.getHashFromHashSalt(hashedPassword);
    }

}
















//@Service
//public class RegistrationService {
//    private final Logger                    logger = LoggerFactory.getLogger(RegistrationService.class);
//    private final UserRepository            userRepository;
//    private final HashService               hashService;
//    private final PasswordCheckerService    passwordCheckerService;
//
//
//    public RegistrationService(UserRepository userRepository, HashService hashService, PasswordCheckerService passwordCheckerService) {
//        this.userRepository = userRepository;
//        this.hashService = hashService;
//        this.passwordCheckerService = passwordCheckerService;
//        logger.info("New RegistrationService");
//    }
//
//    /**
//     * Registreert een nieuwe gebruiker met de opgegeven registratiegegevens.
//     *
//     * @param registrationDTO De registratiegegevens van de nieuwe gebruiker.
//     * @return De geregistreerde gebruiker als de registratie succesvol is voltooid.
//     * @throws UserExistsException       als er al een gebruiker is met het opgegeven e-mailadres.
//     * @throws PasswordNotValidException als het opgegeven wachtwoord niet geldig is.
//     */
//    public User register(RegistrationDTO registrationDTO) throws UserExistsException,
//            PasswordNotValidException {
//        User existingUser = findUserByEmail(registrationDTO.getEmail());
//        if (existingUser != null) {
//            throw new UserExistsException("User with email " + registrationDTO.getEmail() + " already exists");
//        }
//        String password = registrationDTO.getPassword();
//        if (!passwordCheckerService.isPasswordValid(password)) {
//            throw new PasswordNotValidException("Password is not valid");
//        }
//        return createUser(registrationDTO.getEmail(), password);
//    }
//
//    private User createUser(String email, String password) {
//        User inputUser = userWithHashedPassword(password, email);
//        User returnUser = userRepository.saveUser(inputUser);
//        return returnUser;
//    }
//
//    public User findUserByEmail(String email) {
//        return userRepository.findUserByEmail(email);
//    }
//
//    protected User userWithHashedPassword(String password, String email) {
//        String hashedPassword = hashService.hash(password);
//        String salt = getSaltFromHashSalt(hashedPassword);
//        String passwordHash = getHashFromHashSalt(hashedPassword);
//        return new User(email, passwordHash, salt);
//    }
//
//}
