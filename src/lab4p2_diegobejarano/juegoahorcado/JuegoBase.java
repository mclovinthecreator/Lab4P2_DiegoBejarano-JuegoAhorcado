/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4p2_diegobejarano.juegoahorcado;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public abstract class JuegoBase implements Juego {

    protected String palabrasecreta;
    protected String palabraactual;
    protected int intentos;
    protected int limiteintentos = 6;
    protected ArrayList<Character> letrasusadas; // arraylist protegido que guarda las letras ya usadas por el jugador

    public JuegoBase() {
        this.intentos = limiteintentos;
        this.letrasusadas = new ArrayList<>(); // metodo vacio para las letras usadas
    }

    protected abstract void actualizarPalabra(char letra);

    protected abstract boolean verificarletra(char letra);

    protected abstract boolean hasganado();

    public String getPalabrasecreta() {
        return palabrasecreta;
    }

    public void setPalabrasecreta(String palabrasecreta) {
        this.palabrasecreta = palabrasecreta;
    }

    public String getPalabraactual() {
        return palabraactual;
    }

    public void setPalabraactual(String palabraactual) {
        this.palabraactual = palabraactual;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getLimiteintentos() {
        return limiteintentos;
    }

    public void setLimiteintentos(int limiteintentos) {
        this.limiteintentos = limiteintentos;
    }

    public ArrayList<Character> getLetrasusadas() {
        return letrasusadas;
    }

    public void setLetrasusadas(ArrayList<Character> letrasusadas) {
        this.letrasusadas = letrasusadas;
    }

    //metodo para la figura del ahorcado
    public String getFiguraAhorcado() {
        int errores = limiteintentos - intentos;
        /*
        Utilizare un string builder para facilitarme el trabajo de 
        dibujar la figura y ahorrarme el tiempo de hacer otra clase
         */
        StringBuilder sb = new StringBuilder();
        //parte superior
        sb.append("+---+\n");
        sb.append("|   |\n");
        // aqui ya vemos si hay errores en la letra que introdujo
        if (errores >= 1) {
            sb.append("|   O\n");
        } else {
            sb.append("|    \n");
        }
        // dibujamos el cuerpo si hay mas errores
        if (errores >= 4) {
            sb.append("|   /|\\\n");
        } else if (errores >= 3) {
            sb.append("|   /|\n");
        } else if (errores >= 2) {
            sb.append("|    |\n");
        } else {
            sb.append("|    |\n");
        }
        // si hay mas de 6 errores dibujamos lo siguiente
        if (errores >= 6) {
            sb.append("|   / \\n");
        } else if (errores >= 5) {
            sb.append("|   /\n");
        } else {
            sb.append("|    |\n");
        }
        // la base del dibujo
        sb.append("|\n");
        sb.append("=============\n");
        return sb.toString();
    }
    
    protected void ValidarLetraRepetida(char letra) throws ExceptionLetraRepetida{
        if(letrasusadas.contains(letra)){
            throw new ExceptionLetraRepetida("Letra '"+letra+"' ya usada");
        }
    }
    
    protected void validarEntrada(String entrada) throws ExceptionEntradaInvalida{
        //si la entrada esta vacio lanza una excepcion
        if(entrada==null){
            throw new ExceptionEntradaInvalida("Debes Ingresar Una Letra");
        }
        
        if(entrada.isEmpty()){
            throw new ExceptionEntradaInvalida("Debes Ingresar Una Letra");
        }
        // si lo que introduce es mayor a un caracter lanzamos excepcion
        if(entrada.length() != 1){
            throw new ExceptionEntradaInvalida("Debes Ingresar Solo Una Letra valida");
        }
        
        if(!Character.isLetter(entrada.charAt(0))){
            throw new ExceptionEntradaInvalida("Debes Ingresar Solo Una Letra valida");
        }
    }
    public boolean ProcesarIntento(String entrada) throws ExceptionLetraRepetida, ExceptionEntradaInvalida{
        validarEntrada(entrada);
        char letra = Character.toLowerCase(entrada.charAt(0));
        ValidarLetraRepetida(letra);
        letrasusadas.add(letra);
        if(verificarletra(letra)){
           actualizarPalabra(letra);
           return true;
        }
        else{
            intentos--;
            return false;
        }
    }
    public boolean juegoterminado(){
        if(hasganado()){
            return true;
        }
        if(intentos <= 0){
            return true;
        }
        return false;
    }
    public void reiniciar(){
        this.intentos = limiteintentos;
        this.letrasusadas.clear();
        inicializarpalabrasecreta();
    }
}
