/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

/**
 *
 * @author Paco
 */
public class ID3 {

    public static void main(String[] args) {
        //String resultado = id3.core.getid3("tamano,color,pelambre,", "peligroso", "perros");
        //resultado = resultado.substring(26);
        /*String resultado = null;
         System.out.println(resultado);
         Arbol arbol = new Arbol();
         List<NodoAgregable> nodos = new ArrayList<>();
         while (!resultado.isEmpty()) {
         int a = resultado.indexOf("|");
         String cadena1 = resultado.substring(0, a);
         resultado = resultado.substring(a + 1);
         int b = resultado.indexOf("|");
         String cadena2 = resultado.substring(0, b);
         resultado = resultado.substring(b + 1);
         //NodoAgregable na = new NodoAgregable(cadena1, cadena2);
         System.out.println(cadena1);
         System.out.println(cadena2);
         nodos.add(new NodoAgregable(cadena1, Integer.valueOf(cadena2)));
         }
         //System.out.println(nodos);
         arbol.setNodos(nodos);
         System.out.println(arbol);*/

        String consult = "consult('conexion.pl')";
        Query queryConsult = new Query(consult);
        queryConsult.hasSolution();
        String conexion = "abrir_conexion";
        Query queryConexion = new Query(conexion);
        System.out.println(queryConexion + "" + (queryConexion.hasSolution() ? "Se ha conectado a la base de datos " : " Error en la conexión "));

        Term arg[] = {new Atom("pelambre,color,tamano"), new Atom("perros"), new Atom("peligroso"), new jpl.Integer(0)};
        Query q = new Query("insertarEntropias", arg);
        q.oneSolution();

        Variable X = new Variable("Columna");
        Variable Y = new Variable("Entropia");
        Term argumentos[] = {X, Y};
        Query q5 = new Query("entropia", argumentos);
        Hashtable solution;
        List<EntropiaColumna> entropiasColumna = new ArrayList<>();
        while (q5.hasMoreSolutions()) {
            solution = q5.nextSolution();
            EntropiaColumna ec = new EntropiaColumna();
            ec.columna = solution.get("Columna").toString();
            ec.entropia = Double.valueOf(solution.get("Entropia").toString());
            entropiasColumna.add(ec);
            System.out.println(ec);
        }
        EntropiaColumna mejorColumna = EntropiaColumna.getMejorNodo(entropiasColumna);
        entropiasColumna.remove(mejorColumna);
        System.out.println("Mejor Columna = "+mejorColumna);
        System.out.println("Lista sin la mejor columna =" +entropiasColumna);
        
        
        Variable columna = new Variable("Columna");
        Variable enlace = new Variable("Enlace");
        Term argumentosEnlaces[] = {columna, enlace};
        Query qEnlaces = new Query("enlaces", argumentosEnlaces);
        List<Columna> columnas = new ArrayList<>();
        while (qEnlaces.hasMoreSolutions()) {
            Columna c = new Columna();
            solution = qEnlaces.nextSolution();
            c.nombre = solution.get("Columna").toString();
            c.enlaces.add(solution.get("Enlace").toString());
            columnas.add(c);
            System.out.println(c);
        }
        //columnas = Columna.unirColumnasEnlaces(columnas);
        //System.out.println(columnas);
    }
}
