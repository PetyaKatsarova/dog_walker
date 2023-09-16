//package nl.hva.dogwalker.communication.controller;
//
//import nl.hva.dogwalker.business.service.LoginService;
//import nl.hva.dogwalker.business.service.RegistrationService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//
///**
// * Description:
// * Author: Petya Katsarova
// * Email: pskpetya@gmail.com
// * Created on: 24/08/2023 15:21
// */
//
//import nl.hva.dogwalker.business.domain.User;
//import nl.hva.dogwalker.business.service.LoginService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class LoginController {
//    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//    private final LoginService loginService;
//
//    public LoginController(LoginService loginService) {
//        this.loginService = loginService;
//        logger.info("New LoginController");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> authenticateUserHandler(@RequestBody User user) {
//        logger.info("Received login request - User: {}", user);
//        if (loginService.authenticate(user)) {
//            String jwt = loginService.generateJWTToken(user);
//            user.setJwtToken(jwt);
//            loginService.updateUserIdentificatieToken(user);
//            return ResponseEntity.ok("Bearer " +jwt);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//    }
//
//}







////@Controller
//public class LoginController {
//    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//    private final LoginService loginService;
//    private final RegistrationService registrationService;
//
//    public LoginController(LoginService loginService, RegistrationService registrationService) {
//        this.loginService = loginService;
//        this.registrationService = registrationService;
//    }
//    //  private final ApplicationEventPublisher publisher;
//
//}
