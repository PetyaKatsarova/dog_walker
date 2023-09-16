package nl.hva.dogwalker.util.security;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 24/08/2023 14:36
 */
public class HashAndSaltUtil {
    private static final int HASH_LENGTH = 64;
    //l returns from hash_length till end of str
    public static String getSaltFromHashSalt(String hashSalt) {
        return hashSalt.substring(HASH_LENGTH);
    }
    // returns from 0 till hash_length
    public static String getHashFromHashSalt(String hashSalt) {
        return hashSalt.substring(0, HASH_LENGTH);
    }
}
