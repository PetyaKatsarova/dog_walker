package nl.hva.dogwalker.communication.controller;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 12/09/2023 17:44
 */

import jakarta.servlet.http.HttpServletRequest;
import nl.hva.dogwalker.business.domain.User;
import nl.hva.dogwalker.business.service.RegistrationService;
import nl.hva.dogwalker.business.service.UserService;
import nl.hva.dogwalker.util.excetions.EmailAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

@RestController
public class RegistrationController {

    private final Logger                logger = LoggerFactory.getLogger(RegistrationController.class);
    private final RegistrationService   registrationService;
    private final UserService           userService;
//    private final ApplicationEventPublisher publisher; todo: expand the app

    public RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
        logger.info("New RegistrationController");
    }

    @GetMapping("/test")
    public String testController(@RequestParam String lala) {
        return lala + " ** Hello from the other side.... do u want to singup for dog walking services? : )";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> userSignInHandler(@RequestBody SignupRequest signupRequest) {
        try {
            User user = registrationService.register(signupRequest.getEmail(), signupRequest.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (EmailAlreadyExistsException ex) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
    }

    private static class SignupRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

// todo: for next stage
    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}

