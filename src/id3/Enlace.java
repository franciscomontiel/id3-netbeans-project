/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.io.Serializable;

/**
 *
 * @author Paco
 */
public class Enlace implements Serializable{
    
    String nombre;
    Nodo nodo;

    public Enlace(String nombre, Nodo nodo) {
        this.nombre = nombre;
        this.nodo = nodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    @Override
    public String toString() {
        return "\n Enlace{" + "nombre=" + nombre + ", nodo=" + nodo + '}';
    }
    
    
}
