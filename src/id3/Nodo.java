/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paco
 */
public class Nodo implements Serializable {

    Nodo vieneDe;
    String nombre;
    List<Enlace> enlaces = new ArrayList<>();

    public Nodo(Nodo vieneDe, String nombre, List<Enlace> enlaces) {
        this.nombre = nombre;
        this.enlaces = enlaces;
        this.vieneDe = vieneDe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Enlace> getEnlaces() {
        return enlaces;
    }

    public void setEnlaces(List<Enlace> enlaces) {
        this.enlaces = enlaces;
    }

    @Override
    public String toString() {
        if (enlaces == null || enlaces.isEmpty()) {
            return "\n " + nombre;
        }
        return "\n Nodo{" + "nombre=" + nombre + ", enlaces=" + enlaces + '}';
    }

}
