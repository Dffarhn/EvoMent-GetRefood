package Database;

import java.util.Base64;

public class PasswordUtils {

    private static final String ENCRYPTION_KEY = "K";

    public static String encryptPassword(String plainPassword) {
        byte[] passwordBytes = plainPassword.getBytes();
        byte[] encryptedBytes = xorEncrypt(passwordBytes, ENCRYPTION_KEY.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptPassword(String encryptedPassword) {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = xorEncrypt(encryptedBytes, ENCRYPTION_KEY.getBytes());
        return new String(decryptedBytes);
    }

    private static byte[] xorEncrypt(byte[] data, byte[] key) {
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) (data[i] ^ key[i % key.length]);
        }
        return result;
    }

    public static void main(String[] args) {
        String plainPassword = "Raihannajwa123";
        String encryptedPassword = encryptPassword(plainPassword);

        System.out.println("Encrypted password: " + encryptedPassword);

        String decryptedPassword = decryptPassword(encryptedPassword);
        System.out.println("Decrypted password: " + decryptedPassword);
    }
}

