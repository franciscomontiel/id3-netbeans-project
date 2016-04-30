/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paco
 */
public class Columna {

    public String nombre;
    public List<String> enlaces = new ArrayList<>();

    @Override
    public String toString() {
        return "Columna{" + "nombre=" + nombre + ", enlaces=" + enlaces + '}';
    }
/*
    public static List<Columna> unirColumnasEnlaces(List<Columna> lista){
        List<String> nuevaLista=new ArrayList<>();
        for (int i = 0;i < lista.size();i++) {
            if(nuevaLista.contains(lista.get(i).nombre)){
            
            }else{
            nuevaLista.add(lista.get(i).nombre);
            }
        }
    }
    
    public static List<Columna> unirColumnaEnlaces(List<Columna> lista){
        for (Columna c: lista) {
            
        }
    }*/
}
