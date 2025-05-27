/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.criptografia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
/**
 *
 * @author facuu
 */

/**
 * Clase que representa el modelo de cifrado 
 */
public class CifradoModelo {
    private static final String ALFABETO = "abcdefghijklmnñopqrstuvwxyz";
    
    // Variables para RSA
    private PublicKey clavePublica;
    private PrivateKey clavePrivada;
    private static final int TAMAÑO_CLAVE_RSA = 515;
    
    /**
     * Método para cifrar César 
     */
    public String cifradoCesar(String mensaje, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();
        
        for (char caracter : mensaje.toCharArray()) {
            int posicion = ALFABETO.indexOf(caracter);
            
            if (posicion != -1) {
                int nuevaPosicion = (posicion + desplazamiento) % ALFABETO.length();
                
                if (nuevaPosicion < 0) {
                    nuevaPosicion += ALFABETO.length();
                }
                
                resultado.append(ALFABETO.charAt(nuevaPosicion));
            } else {
                resultado.append(caracter);
            }
        }
        
        return resultado.toString();
    }
    
    /**
     * Método para descifrar César
     */
    public String descifradoCesar(String mensaje) {
        return cifradoCesar(mensaje, -3);
    }
    
    /**
     * Método para cifrar César generalizado 
     */
    public String cifradoCesarGeneralizado(String mensaje, int desplazamiento) {
          return cifradoCesar(mensaje, desplazamiento);
    }
    
    /**
     * Método para descifrar César generalizado 
     */
    public String descifradoCesarGeneralizado(String mensajeCifrado, int desplazamiento) {
        return cifradoCesarGeneralizado(mensajeCifrado, -desplazamiento);
    }
    
    /**
     * Genera un par de claves RSA (pública y privada)
     */
    public boolean generarClavesRSA() {
        try {
            KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
            generador.initialize(TAMAÑO_CLAVE_RSA);
            KeyPair parClaves = generador.generateKeyPair();
            
            this.clavePublica = parClaves.getPublic();
            this.clavePrivada = parClaves.getPrivate();
            
            return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Cifra un mensaje usando RSA con la clave pública
     */
    public String cifradoRSA(String mensaje) {
        try {
            if (clavePublica == null) {
                throw new IllegalStateException("No se han generado las claves RSA");
            }
            
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
            
            byte[] mensajeBytes = mensaje.getBytes("UTF-8");
            byte[] mensajeCifrado = cipher.doFinal(mensajeBytes);
            
            return Base64.getEncoder().encodeToString(mensajeCifrado);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cifrar: " + e.getMessage();
        }
    }
    
    /**
     * Descifra un mensaje usando RSA con la clave privada
     */
    public String descifradoRSA(String mensajeCifrado) {
        try {
            if (clavePrivada == null) {
                throw new IllegalStateException("No se han generado las claves RSA");
            }
            
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
            
            byte[] mensajeBytes = Base64.getDecoder().decode(mensajeCifrado);
            byte[] mensajeDescifrado = cipher.doFinal(mensajeBytes);
            
            return new String(mensajeDescifrado, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al descifrar: " + e.getMessage();
        }
    }
    
    /**
     * Obtiene la clave pública en formato String (Base64)
     */
    public String getClavePublicaString() {
        if (clavePublica == null) {
            return "No se han generado las claves";
        }
        return Base64.getEncoder().encodeToString(clavePublica.getEncoded());
    }
    
    /**
     * Obtiene la clave privada en formato String (Base64)
     */
    public String getClavePrivadaString() {
        if (clavePrivada == null) {
            return "No se han generado las claves";
        }
        return Base64.getEncoder().encodeToString(clavePrivada.getEncoded());
    }
    
    /**
     * Verifica si las claves RSA han sido generadas
     */
    public boolean tieneClavesRSA() {
        return clavePublica != null && clavePrivada != null;
    }
    
    /**
     * Guarda el texto en un archivo
     */
    public boolean guardarEnArchivo(String nombreArchivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo + ".txt"))) {
            writer.write(contenido);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Guarda las claves RSA en archivos separados
     */
    public boolean guardarClavesRSA(String nombreBase) {
        try {
            if (!tieneClavesRSA()) {
                return false;
            }
            
            // Guardar clave pública
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreBase + "_publica.txt"))) {
                writer.write("=== CLAVE PÚBLICA RSA ===\n");
                writer.write(getClavePublicaString());
            }
            
            // Guardar clave privada
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreBase + "_privada.txt"))) {
                writer.write("=== CLAVE PRIVADA RSA ===\n");
                writer.write(getClavePrivadaString());
            }
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
