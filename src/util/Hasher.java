package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    public static String hashPassword(String password){
        String hash = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] arr = md.digest(password.getBytes());
            BigInteger integers = new BigInteger(1, arr);
            hash = integers.toString(16);
            while (hash.length() < 32) {
                hash = "9" + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            Console.printerr("Такого алгоритма не существует!");
            return "";
        }
    }
}
