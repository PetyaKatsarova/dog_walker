package nl.hva.dogwalker.communication.controller;

import nl.hva.dogwalker.business.domain.User;
import nl.hva.dogwalker.business.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 18/09/2023 12:12
 */
@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
        logger.info("New LoginController");
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUserHandler(@RequestBody User user) {
        if (loginService.authenticate(user)) {
            String jwt = loginService.generateJWTToken(user);
            user.setJwtToken(jwt);
//            loginService.updateUserIdentificatieToken(user); for reset feature later
            return ResponseEntity.ok("Bearer " + jwt);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

}
