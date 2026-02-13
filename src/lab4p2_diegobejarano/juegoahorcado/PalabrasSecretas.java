/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4p2_diegobejarano.juegoahorcado;

/**
 *
 * @author diego
 */
import java.util.*;

public class PalabrasSecretas {
    private ArrayList<String> palabras;
    private Random R;

    public PalabrasSecretas() {
        this.palabras = new ArrayList<>();
        this.R = new Random();
        inicializarPalabras();
    }
    public void inicializarPalabras(){
        try{
            agregarPalabra("JAVA");
            agregarPalabra("CLASE");
            agregarPalabra("OBJETO");
            agregarPalabra("METODO");
            agregarPalabra("VARIABLE");
            agregarPalabra("POLIMORFISMO");
            agregarPalabra("HERENCIA");
            agregarPalabra("ABSTRACCION");
            agregarPalabra("SISTEMAS");
            
        }catch(ExceptionPalabraDuplicada e){
            System.err.println(e.getMessage());
        }
    }
    public void agregarPalabra(String palabra) throws ExceptionPalabraDuplicada{//throws le da la responsabilidad de tirar error a una x clase
        if(palabra == null ){
            return;
        }
        
        if(palabra.trim().isEmpty()){//trim elimina espacios en blanco
            return;
        }
        
        String palabraNormalizada = palabra.toUpperCase().trim();
        
        if(palabras.contains(palabraNormalizada)){
            throw new ExceptionPalabraDuplicada("Palabra duplicada");
        }
        palabras.add(palabraNormalizada);
    }
    public String obtenerpalabraalazar(){
        if(palabras.isEmpty()){
            throw new IllegalStateException("Sin Palabras");
        }
        int indiceAleatorio = R.nextInt(palabras.size());
        return palabras.get(indiceAleatorio);
    }
}
