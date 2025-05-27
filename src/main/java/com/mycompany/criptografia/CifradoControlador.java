/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.criptografia;

import javax.swing.JOptionPane;
/**
 *
 * @author facuu
 */

/**
 * Clase controlador que conecta el modelo con la vista
 */
public class CifradoControlador {
    private final CifradoModelo modelo;
    private CifradoVista vista;
    
    public CifradoControlador() {
        this.modelo = new CifradoModelo();
    }
    
    public void setVista(CifradoVista vista) {
        this.vista = vista;
    }
    
    public void procesarCesar(String mensaje, boolean esCifrado) {
        String resultado;
        
        if (esCifrado) {
            resultado = modelo.cifradoCesar(mensaje, 3);
        } else {
            resultado = modelo.descifradoCesar(mensaje);
        }
        
        vista.mostrarResultado(resultado);
    }
    
    public void procesarCesarGeneralizado(String mensaje, int desplazamiento, boolean esCifrado) {
        String resultado;
        
        if (esCifrado) {
            resultado = modelo.cifradoCesarGeneralizado(mensaje, desplazamiento);
        } else {
            resultado = modelo.descifradoCesarGeneralizado(mensaje, desplazamiento);
        }
        
        vista.mostrarResultado(resultado);
    }
    
    /**
     * Procesa el cifrado/descifrado RSA
     */
    public void procesarRSA(String mensaje, boolean esCifrado) {
        if (!modelo.tieneClavesRSA()) {
            vista.mostrarMensaje("Debe generar las claves RSA primero.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String resultado;
        
        if (esCifrado) {
            resultado = modelo.cifradoRSA(mensaje);
        } else {
            resultado = modelo.descifradoRSA(mensaje);
        }
        
        vista.mostrarResultado(resultado);
    }
    
    /**
     * Genera las claves RSA
     */
    public void generarClavesRSA() {
        vista.mostrarMensaje("Generando claves RSA...\nEsto puede tomar unos segundos.", 
            "Información", JOptionPane.INFORMATION_MESSAGE);
        
        boolean resultado = modelo.generarClavesRSA();
        
        if (resultado) {
            vista.mostrarMensaje("Claves RSA generadas exitosamente.", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.actualizarEstadoClavesRSA(true);
            vista.mostrarClaves(modelo.getClavePublicaString(), modelo.getClavePrivadaString());
        } else {
            vista.mostrarMensaje("Error al generar las claves RSA.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Guarda las claves RSA en archivos
     */
    public void guardarClavesRSA() {
        if (!modelo.tieneClavesRSA()) {
            vista.mostrarMensaje("No hay claves RSA para guardar.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nombreBase = JOptionPane.showInputDialog(vista, 
            "Ingrese el nombre base para los archivos de claves:", 
            "Guardar claves RSA", JOptionPane.QUESTION_MESSAGE);
        
        if (nombreBase != null && !nombreBase.trim().isEmpty()) {
            boolean resultado = modelo.guardarClavesRSA(nombreBase);
            
            if (resultado) {
                vista.mostrarMensaje("Claves guardadas exitosamente:\n" +
                    "- " + nombreBase + "_publica.txt\n" +
                    "- " + nombreBase + "_privada.txt", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                vista.mostrarMensaje("Error al guardar las claves.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void guardarResultado() {
        String nombreArchivo = JOptionPane.showInputDialog(vista, 
            "Ingrese el nombre del archivo (sin extensión):", 
            "Guardar resultado", JOptionPane.QUESTION_MESSAGE);
        
        if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
            boolean resultado = modelo.guardarEnArchivo(nombreArchivo, vista.getResultado());
            
            if (resultado) {
                vista.mostrarMensaje("Archivo guardado exitosamente como " + 
                    nombreArchivo + ".txt", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                vista.mostrarMensaje("Error al guardar el archivo.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}