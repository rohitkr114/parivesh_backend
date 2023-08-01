package com.backend.util;

import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
@Component
public class RSAUtil {

    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX5QXZXmiwf73G7XMggdKYW7WFxtptJ38RnUMz4rv5xOJJGP0TyyzibLdhhiSSzIspVmAopL4h3FVO7KAp0TemRW6e/lSDMVoTeVPipde8r6bwjrhnYEapUiDMAfal6ah4ZlOTjwWx3OwXg3kmbdV/1JffVYVA7kI8ZswuyNfZ5wIDAQAB";
    public static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJflBdleaLB/vcbtcyCB0phbtYXG2m0nfxGdQzPiu/nE4kkY/RPLLOJst2GGJJLMiylWYCikviHcVU7soCnRN6ZFbp7+VIMxWhN5U+Kl17yvpvCOuGdgRqlSIMwB9qXpqHhmU5OPBbHc7BeDeSZt1X/Ul99VhUDuQjxmzC7I19nnAgMBAAECgYANAUUGxJBhFZF+6o38tAYpw6cJ56nZm6lDraZ3LEwHiWW4eCUUS0UyCQ3gZzb9bKQ461Yl/15EvD5/mJST+qpGyyRFg0XcHrXuNiDCuzqHH6O5a1qBszXqakIwjxE2QsbGQYfu6rjuUI+dAokbjpPdWwBCrgM8zZ+RD8h7xf0gZQJBAJ/GfSxDx9Jhvv3LIu8SdhDfeHUhCUSw7p1Io4HkKRChtCsZhywNDk/NL9krnu4mOOD9BhIqm5zpkLoby1zIsQsCQQDzX4P8FSeYBvx66XQzQ8nXJ1n4RkEoTvofWtTqDOgb0zAZ8YpE+Sd8k/S23eCR4HJp9i+XvutDv7uanM5H4HwVAkEAkXRzGiwuqwGfjY80I2+sGppHIGLN/EITSQ8LA8JB/EkqK56W52yPXekPqWbDzkzvtSvELh+qAUTFJBkPn015lwJBALih8uhwSBxu2GaK66vktqCyavAOpGnnGJxm9XYmmoRZgHl+JC7Vcp5xRMriovcodsRqj4qovaUlNTdD1L700E0CQAIoqo4+QnYPjMdgiXRKyWACikmQH7PAyrbYkvDBHEUuXfD/br/MPWGAdjhtkBpYBTtPkZO9UoRBPYQ+9eu7qxo=";

    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        try {
            String encryptedString = Base64.getEncoder().encodeToString(encrypt("postgres@12345", publicKey));
            System.out.println(encryptedString);
            String decryptedString = RSAUtil.decrypt(
                    "FE4gf5e5gbnL7DPTWSmh7liR+GaF70k4J3Sqf+5XmxYdwTbcX+qnvzSXMjk6koYEmDeSDc4PyKn1UCVxroE3th9lLkJX0hyYPs5iHtcnXROpJug1PqCyLBOFSczrxJCaLN0k8oheqPR5fKsytEyIhk+QK6iEB8AdiROTm0jnKfQ=", privateKey);
            System.out.println(decryptedString);

            String decryptedString1 = RSAUtil.decrypt(
                    "erIlK8tfXqzWl6AZfO4Jf2gyuNkV9eccV5dyANSBUicp7/KNhj/hVrPSWN98Wt74M98RAut/dN4uPg8bSNh6p4n9HgD92/rWFPqSDzy45lEnPuOB4IDWR0uG+luPY4wvvbOgHGOQEYvov1l8iJvaj6CU6SIoHg772ytuqtN3Ki8=", privateKey);
            System.out.println(decryptedString1);
            
            
            
            
            
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

    }
}
