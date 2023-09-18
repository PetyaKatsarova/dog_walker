package nl.hva.dogwalker.business.service;

import nl.hva.dogwalker.business.domain.Customer;
import nl.hva.dogwalker.business.domain.User;
import nl.hva.dogwalker.persistence.repository.CustomerRepository;
import nl.hva.dogwalker.persistence.repository.UserRepository;
import nl.hva.dogwalker.util.security.EmailSenderService;
import nl.hva.dogwalker.util.security.JWTToken;
import nl.hva.dogwalker.util.security.password.HashService;
import nl.hva.dogwalker.util.security.password.PasswordCheckerService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

import static nl.hva.dogwalker.util.security.password.HashAndSaltUtil.getHashFromHashSalt;
import static nl.hva.dogwalker.util.security.password.HashAndSaltUtil.getSaltFromHashSalt;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 24/08/2023 15:22
 */

@Service
public class LoginService {

    private final UserRepository            userRepository;
    private final EmailSenderService        emailSenderService;
    private final HashService               hashService;
    private final PasswordCheckerService    passwordCheckerService;
    private JWTToken                        jwtToken;

    public LoginService(UserRepository userRepository, HashService hashService,
                        PasswordCheckerService passwordCheckerService, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
        this.passwordCheckerService = passwordCheckerService;
        this.jwtToken = new JWTToken();
        this.emailSenderService = emailSenderService;
    }

    /**
     * Authenticeert een gebruiker op basis van de verstrekte gebruikersgegevens.
     *
     * @param user The user that needs to be authenticated, including email and password.
     * @return true als de gebruiker succesvol is geauthenticeerd, anders false.
     */
    public boolean authenticate(User user) {
        boolean userCanEnter = false;
        User loginUser = userRepository.findUserByEmail(user.getEmail());
        if (loginUser != null) {
            String hashedPW = loginUser.getPassword();
            String salt = loginUser.getSalt();
            String toBechecked = getHashFromHashSalt(hashService.hash(user.getPassword(), salt));
            if (hashedPW.equals(toBechecked) && loginUser.isVerified()) {
                userCanEnter = true;
            }
        }
        return userCanEnter;
    }

    public String generateJWTToken(User user) {
        return jwtToken.generateJWTToken(user.getEmail());
    }

    private void setHashedPassword(String password, User user) {
        String hashedPassword = hashService.hash(password);
        user.setSalt(getSaltFromHashSalt(hashedPassword));
        user.setPassword(getHashFromHashSalt(hashedPassword));
    }

//    public boolean updateUserIdentificatieToken(User user) {
//        return userRepository.updateUserIdentificatieToken(user);
//    }
}
