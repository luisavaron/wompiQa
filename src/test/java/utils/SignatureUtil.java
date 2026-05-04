package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SignatureUtil {

    public static String generate(String reference, int amount, String currency, String integrityKey) {
        try {
            String dataToSign = reference + amount + currency + integrityKey;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(dataToSign.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(hash);

        } catch (Exception e) {
            throw new RuntimeException("Error generando la firma", e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}