package nl.hva.dogwalker.persistence.repository;

import nl.hva.dogwalker.business.domain.User;
import nl.hva.dogwalker.persistence.dao.JdbcUserDao;
import nl.hva.dogwalker.persistence.dao.JdbcVerificationTokenDao;
import nl.hva.dogwalker.util.security.password.PasswordCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 12/09/2023 14:06
 */
@Repository
public class UserRepository {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final JdbcUserDao jdbcUserDao;
    private final PasswordCheckerService passwordCheckerService;
    private final JdbcVerificationTokenDao jdbcVerificationTokenDao;

    public UserRepository(JdbcUserDao jdbcUserDao, PasswordCheckerService passwordCheckerService, JdbcVerificationTokenDao jdbcVerificationTokenDao) {
        this.jdbcUserDao = jdbcUserDao;
        this.passwordCheckerService = passwordCheckerService;
        this.jdbcVerificationTokenDao = jdbcVerificationTokenDao;
        logger.info("New UserRepository");
    }

    public User findUserByEmail(String email) {
        return jdbcUserDao.findUserByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("No user found with email: " + email));
    }

    public User saveUser(User user) {
        System.out.println(user);
        jdbcUserDao.saveUser(user);
        return user;
    }
    public User updateUser(User user) {
        return jdbcUserDao.updateUser(user);
    }

//    public boolean updateUserIdentificatieToken(User user) {
//        return jdbcUserDao.updateUserIdentificatieToken(user);
//    }

    public User finduserByJwtToken(String jwtoken) {
        return jdbcUserDao.findUserByjwtToken(jwtoken);
    }

    public User findUserById(int idUser) {
        return jdbcUserDao.findUserById(idUser);
    }
}
