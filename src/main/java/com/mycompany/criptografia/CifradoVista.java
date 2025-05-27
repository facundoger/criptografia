/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.criptografia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author facuu
 */

/**
 * Clase que representa la vista (interfaz gráfica)
 */
public class CifradoVista extends JFrame {
    private CifradoControlador controlador;
    
    // Componentes de la interfaz
    private JRadioButton rbCifrar;
    private JRadioButton rbDescifrar;
    private JRadioButton rbCesar;
    private JRadioButton rbCesarGeneralizado;
    private JRadioButton rbRSA;
    private JTextField txtDesplazamiento;
    private JTextField txtClave;
    private JTextArea txtMensaje;
    private JTextArea txtResultado;
    private JButton btnProcesar;
    private JButton btnLimpiar;
    private JButton btnGuardar;
    
    // Componentes específicos para RSA
    private JButton btnGenerarClaves;
    private JButton btnGuardarClaves;
    private JButton btnMostrarClaves;
    private JLabel lblEstadoClaves;
    
    public CifradoVista(CifradoControlador controlador) {
        this.controlador = controlador;
        
        // Configuración básica de la ventana
        setTitle("Sistema de Cifrado de Mensajes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(panel);
        
        // Panel superior: Opciones
        JPanel panelOpciones = new JPanel(new GridLayout(2, 1, 10, 10));
        panelOpciones.setBorder(BorderFactory.createTitledBorder("Opciones"));
        
        // Panel operación (cifrar/descifrar)
        JPanel panelOperacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbCifrar = new JRadioButton("Cifrar", true);
        rbDescifrar = new JRadioButton("Descifrar");
        ButtonGroup grupoOperacion = new ButtonGroup();
        grupoOperacion.add(rbCifrar);
        grupoOperacion.add(rbDescifrar);
        
        panelOperacion.add(new JLabel("Operación:"));
        panelOperacion.add(rbCifrar);
        panelOperacion.add(rbDescifrar);
        
        // Panel método (César/César generalizado/RSA)
        JPanel panelMetodo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbCesar = new JRadioButton("César", true);
        rbCesarGeneralizado = new JRadioButton("César Generalizado");
        rbRSA = new JRadioButton("RSA");
        ButtonGroup grupoMetodo = new ButtonGroup();
        grupoMetodo.add(rbCesar);
        grupoMetodo.add(rbCesarGeneralizado);
        grupoMetodo.add(rbRSA);
        
        panelMetodo.add(new JLabel("Método:"));
        panelMetodo.add(rbCesar);
        panelMetodo.add(rbCesarGeneralizado);
        panelMetodo.add(rbRSA);
        
        // Agregar los paneles de operación y método al panel de opciones
        panelOpciones.add(panelOperacion);
        panelOpciones.add(panelMetodo);      
        
        // Panel de parámetros
        JPanel panelParametros = new JPanel(new GridLayout(3, 1, 10, 10));
        panelParametros.setBorder(BorderFactory.createTitledBorder("Parámetros"));
        
        // Desplazamiento para César
        JPanel panelDesplazamiento = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDesplazamiento.add(new JLabel("Desplazamiento:"));
        txtDesplazamiento = new JTextField(5);
        txtDesplazamiento.setText("3");
        txtDesplazamiento.setEnabled(false);
        panelDesplazamiento.add(txtDesplazamiento);   
        
        // Panel para controles RSA
        JPanel panelRSA = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnGenerarClaves = new JButton("Generar Claves RSA");
        btnGuardarClaves = new JButton("Guardar Claves");
        btnMostrarClaves = new JButton("Ver Claves");
        lblEstadoClaves = new JLabel("Estado: Sin claves generadas");
        
        btnGenerarClaves.setEnabled(false);
        btnGuardarClaves.setEnabled(false);
        btnMostrarClaves.setEnabled(false);
        
        panelRSA.add(btnGenerarClaves);
        panelRSA.add(btnGuardarClaves);
        panelRSA.add(btnMostrarClaves);
        
        // Panel para estado de claves RSA
        JPanel panelEstadoRSA = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstadoRSA.add(lblEstadoClaves);
  
        // Añadir parámetros al panel
        panelParametros.add(panelDesplazamiento);
        panelParametros.add(panelRSA);
        panelParametros.add(panelEstadoRSA);
        
        // Panel de entrada/salida
        JPanel panelTexto = new JPanel(new GridLayout(2, 1, 10, 10));
        panelTexto.setBorder(BorderFactory.createTitledBorder("Mensaje"));
        
        // Área de texto para el mensaje
        JPanel panelMensaje = new JPanel(new BorderLayout());
        panelMensaje.add(new JLabel("Mensaje original:"), BorderLayout.NORTH);
        txtMensaje = new JTextArea(5, 40);
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        JScrollPane scrollMensaje = new JScrollPane(txtMensaje);
        panelMensaje.add(scrollMensaje, BorderLayout.CENTER);
        
        // Área de texto para el resultado
        JPanel panelResultado = new JPanel(new BorderLayout());
        panelResultado.add(new JLabel("Resultado:"), BorderLayout.NORTH);
        txtResultado = new JTextArea(5, 40);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        panelResultado.add(scrollResultado, BorderLayout.CENTER);
        
        // Añadir áreas de texto al panel
        panelTexto.add(panelMensaje);
        panelTexto.add(panelResultado);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnProcesar = new JButton("Procesar");
        btnLimpiar = new JButton("Limpiar");
        btnGuardar = new JButton("Guardar en archivo");
        btnGuardar.setEnabled(false);
        
        panelBotones.add(btnProcesar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnGuardar);
        
        // Añadir todos los paneles al panel principal
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1, 10, 10));
        panelSuperior.add(panelOpciones);
        panelSuperior.add(panelParametros);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(panelTexto, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        // Configurar listeners
        setupListeners();
    }
    
    private void setupListeners() {
        // Habilitar/deshabilitar campos según el método seleccionado
        rbCesar.addActionListener(e -> {
            txtDesplazamiento.setEnabled(false);
            txtDesplazamiento.setText("3");
            btnGenerarClaves.setEnabled(false);
            btnGuardarClaves.setEnabled(false);
            btnMostrarClaves.setEnabled(false);
        });
        
        rbCesarGeneralizado.addActionListener(e -> {
            txtDesplazamiento.setEnabled(true);
            btnGenerarClaves.setEnabled(false);
            btnGuardarClaves.setEnabled(false);
            btnMostrarClaves.setEnabled(false);
        });
        
        rbRSA.addActionListener(e -> {
            txtDesplazamiento.setEnabled(false);
            btnGenerarClaves.setEnabled(true);
        });
        
        // Botones RSA
        btnGenerarClaves.addActionListener(e -> {
            controlador.generarClavesRSA();
        });
        
        btnGuardarClaves.addActionListener(e -> {
            controlador.guardarClavesRSA();
        });
        
        btnMostrarClaves.addActionListener(e -> {
            controlador.generarClavesRSA(); // Esto mostrará las claves automáticamente
        });
        
        // Botón procesar
        btnProcesar.addActionListener(e -> {
            if (validarDatos()) {
                boolean esCifrado = rbCifrar.isSelected();
                boolean esCesar = rbCesar.isSelected();
                boolean esCesarGeneralizado = rbCesarGeneralizado.isSelected();
                boolean esRSA = rbRSA.isSelected();
                
                if (esCesar) {
                    controlador.procesarCesar(txtMensaje.getText(), esCifrado);
                } else if (esCesarGeneralizado) {
                    int desplazamiento = Integer.parseInt(txtDesplazamiento.getText().trim());
                    controlador.procesarCesarGeneralizado(txtMensaje.getText(), desplazamiento, esCifrado);
                } else if (esRSA) {
                    controlador.procesarRSA(txtMensaje.getText(), esCifrado);
                }
                
                btnGuardar.setEnabled(true);
            }
        });
        
        // Botón limpiar
        btnLimpiar.addActionListener(e -> {
            txtMensaje.setText("");
            txtResultado.setText("");
            txtDesplazamiento.setText("3");
            if (txtClave != null) {
                txtClave.setText("");
            }
            btnGuardar.setEnabled(false);
        });
        
        // Botón guardar
        btnGuardar.addActionListener(e -> {
            if (!txtResultado.getText().isEmpty()) {
                controlador.guardarResultado();
            }
        });
    }
    
    private boolean validarDatos() {
        // Validar mensaje
        if (txtMensaje.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese un mensaje para procesar.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (rbCesarGeneralizado.isSelected()) {
            try {
                Integer.valueOf(txtDesplazamiento.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                "El desplazamiento debe ser un número entero.",
                "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Actualiza el estado de los controles RSA
     */
    public void actualizarEstadoClavesRSA(boolean tieneCla) {
        btnGuardarClaves.setEnabled(tieneCla);
        btnMostrarClaves.setEnabled(tieneCla);
        
        if (tieneCla) {
            lblEstadoClaves.setText("Estado: Claves RSA generadas ✓");
        } else {
            lblEstadoClaves.setText("Estado: Sin claves generadas");
        }
    }
    
    /**
     * Muestra las claves RSA en ventanas emergentes
     */
    public void mostrarClaves(String clavePublica, String clavePrivada) {
        // Mostrar clave pública
        JTextArea areaClavePublica = new JTextArea(10, 50);
        areaClavePublica.setText(clavePublica);
        areaClavePublica.setEditable(false);
        areaClavePublica.setLineWrap(true);
        areaClavePublica.setWrapStyleWord(true);
        
        JScrollPane scrollPublica = new JScrollPane(areaClavePublica);
        JOptionPane.showMessageDialog(this, scrollPublica, 
            "Clave Pública RSA", JOptionPane.INFORMATION_MESSAGE);
        
        // Mostrar clave privada
        JTextArea areaClavePrivada = new JTextArea(10, 50);
        areaClavePrivada.setText(clavePrivada);
        areaClavePrivada.setEditable(false);
        areaClavePrivada.setLineWrap(true);
        areaClavePrivada.setWrapStyleWord(true);
        
        JScrollPane scrollPrivada = new JScrollPane(areaClavePrivada);
        JOptionPane.showMessageDialog(this, scrollPrivada, 
            "Clave Privada RSA", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarResultado(String resultado) {
        txtResultado.setText(resultado);
    }
    
    public String getResultado() {
        return txtResultado.getText();
    }
    
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
}