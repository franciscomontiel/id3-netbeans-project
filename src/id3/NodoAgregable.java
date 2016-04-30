/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

/**
 *
 * @author Paco
 */
public class NodoAgregable {
    String nombre;
    int tipo;

    public NodoAgregable(String nombre, int tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "NodoAgregable{" + "nombre=" + nombre + ", tipo=" + tipo + '}';
    }
    
    
}
