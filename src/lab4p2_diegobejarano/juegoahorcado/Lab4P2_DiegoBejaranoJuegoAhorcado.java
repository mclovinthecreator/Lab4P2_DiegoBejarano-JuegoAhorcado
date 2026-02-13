/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab4p2_diegobejarano.juegoahorcado;

import javax.swing.SwingUtilities;

/**
 *
 * @author diego
 */
public class Lab4P2_DiegoBejaranoJuegoAhorcado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
              Guideljuego gui = new Guideljuego();
              gui.setVisible(true);
            }
            
        });
    }
    
}
