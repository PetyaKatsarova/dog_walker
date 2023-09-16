package nl.hva.dogwalker.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: Learning password security processes
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 24/08/2023 09:48
 */
@Service
public class HashService {
    private static final int    DEFAULT_ROUNDS = 1;
    private PepperService       pepperService;
    private SaltMaker           saltMaker;
    private int                 rounds;

    @Autowired
    public HashService(PepperService pepperService) {
        this(pepperService, DEFAULT_ROUNDS);
    }

    public HashService(PepperService pepperService, int rounds) {
        this(pepperService, rounds, SaltMaker.STANDARD_SALT_LENGTH);
    }

    public HashService(PepperService pepperService, int rounds, int saltLength) {
        this.pepperService = pepperService;
        this.rounds = rounds;
        this.saltMaker = new SaltMaker(saltLength);
    }

    public String hash(String password) {
        String salt = saltMaker.generateSalt();
        return hash(password, salt);
    }

    public String hash(String password, String salt) {
        String pepper = pepperService.getPepper();
        String hash = HashHelper.hash(password, salt, pepper);
        String slowedDownHash = processRounds(hash, this.getRounds());
        // added layer of security:
        return slowedDownHash + salt; // the first string is 64 chars
    }

    private String processRounds(String hash, long numberOfRounds) {
        for (int i = 0; i < numberOfRounds; i++)
            // increase the computational cost of hashing: to slow down and avoid brute force attacks
            hash = HashHelper.hash(hash);
        return hash;
    }

    public int getRounds() {
        return rounds;
    }
}
