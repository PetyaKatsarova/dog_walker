package nl.hva.dogwalker.util.security.password;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 23/08/2023 16:26
 */
public class ByteArrayToHexHelper {
    // Zet een array van bytes om naar een representatie in hexadecimalen
    public static String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (byte b : byteArray) {
            hexStringBuffer.append(byteToHex(b));
        }
        return hexStringBuffer.toString();
    }
    private static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
// 4 bits encoderen voor de 16 waarden 0 - F. Dus 2 karakters vormen een byte
// 1010 0101 ->
// >> is een bit shift operator.
// Deze schuift de bits in dit geval 4 plaatsen naar rechts
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }
}
