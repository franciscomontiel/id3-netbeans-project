/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.util.List;

/**
 *
 * @author Paco
 */
public class EntropiaColumna {
    public double entropia;
    public String columna;

    @Override
    public String toString() {
        return "EntropiaColumna{" + "entropia=" + entropia + ", columna=" + columna + '}';
    }
    
    public static EntropiaColumna getMejorNodo(List<EntropiaColumna> lista){
        EntropiaColumna min = lista.get(0);
        for (EntropiaColumna ec : lista) {
            if(ec.entropia < min.entropia){
                min = ec;
            }
        }
        return min;
    }
}
