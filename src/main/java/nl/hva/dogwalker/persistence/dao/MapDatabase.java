package nl.hva.dogwalker.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 24/08/2023 16:04
 */
@Component
public class MapDatabase {
    // Heel simpele opslag van gebruikersnaam en wachtwoord
    private Map<String, String> db;
    public MapDatabase() {
        db = new ConcurrentHashMap<>();
// vul de database zelf met test data
// denk eraan deze hashen
    }
    public String findHashByUsername(String username) {
        return db.get(username);
    }
    public boolean insertUsernameWithHash(String username, String hash){
        if(!db.containsKey(username)){ // controleer of de nieuw aan te maken key al bestaat
            db.put(username, hash);
            return true;
        }
        return false;
    }
}
