/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paco
 */
public class Arbol implements Serializable {

    public Nodo nodoRaiz;
    public int anchoRectangulo = 8;
    public int altoRectangulo = 20;
    public int separadorNiveles = 50;

    public void setNodos(List<NodoAgregable> na) {
        nodoRaiz= null;
        Nodo nodoActual = new Nodo(null, na.get(0).nombre, new ArrayList<>());
        for (int i = 1; i < na.size(); i++) {
            if (i == na.size() - 1) {

            } else {
                switch (na.get(i).tipo) {
                    case 1:
                        Nodo nuevoNodo = new Nodo(nodoActual, na.get(i).nombre, new ArrayList<>());
                        nodoActual.enlaces.get(nodoActual.enlaces.size() - 1).nodo = nuevoNodo;
                        nodoActual = nuevoNodo;
                        break;
                    case 2:
                        Enlace enlace = new Enlace(na.get(i).nombre, null);
                        nodoActual.enlaces.add(enlace);
                        break;
                    case 3:
                        nodoActual.enlaces.get(nodoActual.enlaces.size() - 1).nodo = new Nodo(nodoActual, na.get(i).nombre, null);
                        break;
                    case 4:
                        nodoActual = nodoActual.vieneDe;
                        break;
                }
            }
        }
        nodoRaiz = nodoActual;
    }

    @Override
    public String toString() {
        return "Arbol{" + "nodoRaiz=" + nodoRaiz + '}';
    }

    public void paint(Graphics g, int x, int y, int anchoPantalla) {
        g.drawRect(x - (nodoRaiz.nombre.length() * anchoRectangulo) / 2,
                y - altoRectangulo,
                nodoRaiz.nombre.length() * anchoRectangulo,
                altoRectangulo);
        g.drawString(nodoRaiz.nombre, x - (nodoRaiz.nombre.length() * anchoRectangulo) / 2 + 2, y - 5);
        int espacioEntreNodos = anchoPantalla / (nodoRaiz.enlaces.size() + 1);
        for (int i = 0; i < nodoRaiz.enlaces.size(); i++) {
            int nuevaX = espacioEntreNodos * (i + 1);
            int nuevaY = y + separadorNiveles;
            g.drawLine(x, y, nuevaX, nuevaY);
            g.drawString(nodoRaiz.enlaces.get(i).nombre,
                    (x + nuevaX) / 2,
                    (y + nuevaY) / 2);
            int espacioParaDibujar = espacioEntreNodos * (i + 2) - espacioEntreNodos * (i + 1);
            this.paintNodo(nodoRaiz.enlaces.get(i).nodo, g, nuevaX, nuevaY, espacioParaDibujar, nuevaX);
        }

    }

    public void paintNodo(Nodo nodo, Graphics g, int x, int y, int espacioParaDibujar, int posicion) {
        y = y +20;
        g.drawRect(x - (nodo.nombre.length() * anchoRectangulo) / 2,
                y - altoRectangulo,
                nodo.nombre.length() * anchoRectangulo,
                altoRectangulo);
        g.drawString(nodo.nombre, x - (nodo.nombre.length() * anchoRectangulo) / 2 + 2, y - 5);
        if (nodo.enlaces == null || nodo.enlaces.isEmpty()) {
            return;
        }
        int espacioEntreNodos = espacioParaDibujar / (nodo.enlaces.size() + 1);
        int posX = posicion - espacioParaDibujar / 2;
        for (int i = 0; i < nodo.enlaces.size(); i++) {
            int nuevaX = posX + (espacioEntreNodos * (i + 1));
            int nuevaY = y + separadorNiveles;
            g.drawLine(x, y, nuevaX, nuevaY);
            g.drawString(nodo.enlaces.get(i).nombre,
                    (x + nuevaX) / 2,
                    (y + nuevaY) / 2);
            int nuevoEspacioParaDibujar = espacioEntreNodos * (i + 2) - espacioEntreNodos * (i + 1);
            this.paintNodo(nodo.enlaces.get(i).nodo, g, nuevaX, nuevaY, nuevoEspacioParaDibujar, nuevaX);
        }
    }

}
