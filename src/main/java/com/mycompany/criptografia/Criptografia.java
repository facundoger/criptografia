package com.mycompany.criptografia;

import javax.swing.*;

/**
 * Clase principal que inicia la aplicación
 */
public class Criptografia {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            CifradoControlador controlador = new CifradoControlador();
            CifradoVista vista = new CifradoVista(controlador);
            controlador.setVista(vista);
            vista.setVisible(true);
        });        
    }
}
