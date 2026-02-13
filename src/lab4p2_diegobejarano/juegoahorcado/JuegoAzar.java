/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4p2_diegobejarano.juegoahorcado;

/**
 *
 * @author diego
 */
public class JuegoAzar extends JuegoBase{
    private PalabrasSecretas adminpalabra;

    public JuegoAzar(PalabrasSecretas adminpalabra) {
        super();
        this.adminpalabra = adminpalabra;
        inicializarpalabrasecreta();
    }
    
    @Override 
    public void inicializarpalabrasecreta(){
        this.palabrasecreta = adminpalabra.obtenerpalabraalazar().toLowerCase();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < palabrasecreta.length(); i++) {
            sb.append("_ ");

        }
        palabraactual = sb.toString().trim();
    }
    @Override 
    protected void actualizarPalabra(char letra){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palabrasecreta.length(); i++) {
            if(palabrasecreta.charAt(i)==letra){
                sb.append(letra);
                sb.append(" ");
            }else{
                int pos = i*2;
                
                if(pos<palabraactual.length()){
                    if(palabraactual.charAt(pos) != '_'){
                        sb.append(palabraactual.charAt(pos));
                        sb.append(" ");
                    }
                    else{
                        sb.append("_ ");

                    }
                }else{
                    sb.append("_ ");
                }
            }
        }
        palabraactual = sb.toString().trim();
    }
    @Override 
    protected boolean verificarletra(char letra){
        return palabrasecreta.contains(String.valueOf(letra));
    }
      @Override
    protected boolean hasganado(){
        return !palabraactual.contains("_");
    }
    @Override
    public void jugar(){
        System.out.println("Jugando con palabra al azar");
    }
}
