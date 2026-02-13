/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4p2_diegobejarano.juegoahorcado;

/**
 *
 * @author diego
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Guideljuego extends JFrame {

    private JuegoBase juego;
    private PalabrasSecretas admin;
    private JTextArea figura;
    private JLabel jbpalabra;
    private JLabel jbintentos;
    private JLabel jbletras;
    private JTextField campoletra;

    public Guideljuego() {
        admin = new PalabrasSecretas();
        juego = new JuegoAzar(admin);

        setTitle("Ahorcado");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 20));

        JPanel paneltop = new JPanel();
        paneltop.setBackground(new Color(52, 73, 94));
        JLabel titulo = new JLabel("JUEGO DEL AHORCADO");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        paneltop.add(titulo);
        add(paneltop, BorderLayout.NORTH);

        JPanel panelcentro = new JPanel(new BorderLayout(10, 10));
        panelcentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        figura = new JTextArea(10, 15);
        figura.setFont(new Font("Monospaced", Font.BOLD, 14));
        figura.setEditable(false);
        panelcentro.add(new JScrollPane(figura), BorderLayout.WEST);

        JPanel panelinfo = new JPanel();
        panelinfo.setLayout(new BoxLayout(panelinfo, BoxLayout.Y_AXIS));
        jbpalabra = new JLabel("Palabra: _ _ _");
        jbpalabra.setFont(new Font("Arial", Font.BOLD, 24));
        panelinfo.add(jbpalabra);
        panelinfo.add(Box.createVerticalStrut(20));

        jbintentos = new JLabel("Intentos: 6");
        jbintentos.setFont(new Font("Arial", Font.PLAIN, 24));
        panelinfo.add(jbintentos);
        panelinfo.add(Box.createVerticalStrut(10));

        jbletras = new JLabel("Letras usadas: ");
        panelinfo.add(jbletras);
        panelinfo.add(Box.createVerticalStrut(20));

        JPanel panelinput = new JPanel();
        panelinput.add(new JLabel("Letra: "));
        campoletra = new JTextField(3);
        panelinput.add(campoletra);
        JButton btnadivinar = new JButton("Adivinar");
        btnadivinar.addActionListener(e -> procesarLetra());
        panelinput.add(btnadivinar);
        panelinfo.add(panelinput);

        panelcentro.add(panelinfo, BorderLayout.CENTER);
        add(panelcentro, BorderLayout.CENTER);
        JPanel panelbotones = new JPanel();
        JButton btnfijo = new JButton("Palabra fija");
        btnfijo.addActionListener(e -> iniciarFijo());
        panelbotones.add(btnfijo);
        
        JButton btnazar = new JButton("Palabra Azar");
         btnazar.addActionListener(e -> iniciarAzar());
         panelbotones.add(btnazar);
         
         JButton btnnuevo = new JButton("Nuevo");
        btnnuevo.addActionListener(e -> {
            juego.reiniciar();
            actualizar();
        });
        panelbotones.add(btnnuevo);
        add(panelbotones, BorderLayout.SOUTH);
        
        campoletra.addActionListener(e -> procesarLetra());
        actualizar();
        setLocationRelativeTo(null);
    }
    private void procesarLetra(){
        if(juego.juegoterminado()){
            JOptionPane.showMessageDialog(this, "Juego Terminado. Presione Nuevo");
            return;
        }
        try{
            boolean letracorrecta = juego.ProcesarIntento(campoletra.getText());
            campoletra.setText("");
            actualizar();
            
            if(juego.juegoterminado()){
                if(juego.hasganado()){
                    String mensajewin = "GANASTE! Palabra: "+ juego.getPalabrasecreta().toUpperCase();
                    JOptionPane.showMessageDialog(this, mensajewin);
                }else{
                    String mensajelost = "PERDISTE! Palabra: "+ juego.getPalabrasecreta().toUpperCase();
                    JOptionPane.showMessageDialog(this, mensajelost);
                }
            }
        }catch(ExceptionLetraRepetida e){
            JOptionPane.showMessageDialog(this, e.getMessage(),"ERROR",JOptionPane.WARNING_MESSAGE);
        }catch(ExceptionEntradaInvalida e){
            JOptionPane.showMessageDialog(this, e.getMessage(),"ERROR",JOptionPane.WARNING_MESSAGE);
        }
    }
    private void actualizar(){
        jbpalabra.setText("Palabra: "+ juego.getPalabraactual());
        jbintentos.setText("Intentos: "+ juego.getIntentos());
        figura.setText(juego.getFiguraAhorcado());
        StringBuilder sb = new StringBuilder("Letras: ");
        ArrayList<Character> letrasusadas = juego.getLetrasusadas();
        
        for (int i = 0; i < letrasusadas.size(); i++) {
            Character letra = letrasusadas.get(i);
            sb.append(letra);
            sb.append(" ");

        }
        jbletras.setText(sb.toString());
    }
    private void iniciarFijo(){
        String palabra = JOptionPane.showInputDialog(this,"Ingrese palabra:");
        if(palabra != null){
            if(!palabra.trim().isEmpty()){
                juego = new Juegofijo(palabra);
                actualizar();
            }
        }
    }
    private void iniciarAzar(){
        juego = new JuegoAzar(admin);
        actualizar();
    }
}
