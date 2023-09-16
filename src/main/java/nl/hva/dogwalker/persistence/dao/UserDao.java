package nl.hva.dogwalker.persistence.dao;
import nl.hva.dogwalker.business.domain.User;

import java.util.Optional;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 12/09/2023 16:01
 */
public interface UserDao {
    Optional<User> findUserByEmail(String email);
    User saveUser(User user);
}
