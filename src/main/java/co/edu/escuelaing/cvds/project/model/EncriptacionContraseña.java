package co.edu.escuelaing.cvds.project.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptacionContraseña {

    private String password;
    public EncriptacionContraseña() {
    }

    // Método para generar un hash a partir de una contraseña
    public String setHashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            // Convertir el byte array a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            this.password =  hexString.toString();
            return this.password;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Manejo básico de la excepción
            return  null;
        }
    }

    public String getPassword() {
        return password;
    }
}

