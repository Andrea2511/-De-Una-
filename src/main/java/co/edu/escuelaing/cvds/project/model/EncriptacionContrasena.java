package co.edu.escuelaing.cvds.project.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptacionContrasena {

    private String password;
    public EncriptacionContrasena() {
    }

    // Método para generar un hash a partir de una contraseña
    public void setHashPassword(String password) {
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Manejo básico de la excepción
        }
    }

    public String getPassword() {
        return password;
    }
}

