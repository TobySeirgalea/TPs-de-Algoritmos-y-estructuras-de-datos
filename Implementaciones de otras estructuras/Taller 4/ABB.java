package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    private int cardinal;
    private Nodo minimo;
    private Nodo maximo;
    private class Nodo {
        // Agregar atributos privados del Nodo
        private T dato;
        private Nodo der;
        private Nodo izq;
        private Nodo padre;
        // Crear Constructor del nodo
        public Nodo(T dato) {
            this.dato = dato;
            this.der = null;
            this.izq = null;
            this.padre = null;
        }
    }
    public ABB() {
        raiz = null;
        cardinal = 0;
    }
    public int cardinal() {
        return cardinal;
    }
    public T minimo(){
        Nodo inicio = raiz;
        while (inicio.padre != null){
            inicio = inicio.padre;
        }
        while (inicio.izq != null){
            inicio = inicio.izq;
        }
        minimo = inicio;
        return minimo.dato;
    }

    public T maximo(){
        Nodo inicio = raiz;
        while (inicio.padre != null){
            inicio = inicio.padre;
        }
        while (inicio.der != null){
            inicio = inicio.der;
        }
        maximo = inicio;
        return maximo.dato;
    }

    public void insertar(T elem){
        Nodo nuevo = new Nodo(elem);
        if (raiz == null){
            raiz = nuevo; //Si está vacío agregalo como raiz.
            cardinal++;
            maximo = nuevo;
            minimo = nuevo;
        }
        else{
            Nodo padreONodo = busquedaNodoOpadre(elem); //Acá está el nodo si ya estaba en el conjunto y su padre si no.
            if (padreONodo.dato.compareTo(elem) != 0){//Si no esta el nodo y tenes al que debe ser su padre
                if  (padreONodo.dato.compareTo(elem) > 0){//Si es menor que el que debe ser su padre
                    padreONodo.izq = nuevo;
                    nuevo.padre = padreONodo;
                    cardinal++;
                }
                else if (padreONodo.dato.compareTo(elem) < 0){//Si es mayor que el valor de su padre 
                    padreONodo.der = nuevo;
                    nuevo.padre = padreONodo;
                    cardinal++;
                }
            }
        }
    }
    public Nodo busquedaNodoOpadre (T valor){
        Nodo actual = raiz;
        while (actual != null && actual.padre != null){ //Subis hasta la raíz y de ahí empezás a buscar
            actual = actual.padre;
        }
        Nodo padre = null;
        while (actual != null){ //Salis con nodo si lo encontraste sino actual == null y retornas su padre.
            if (valor.compareTo(actual.dato) == 0){
                return actual; //Si lo encontraste devolvelo
            }
            else{
                if (valor.compareTo(actual.dato) > 0) {
                    padre = actual;
                    actual = actual.der;
                }
                else{
                    padre = actual;
                    actual = actual.izq;
                }
               }
            }
            return padre;
        }
    public boolean pertenece(T elem){
        if (raiz != null && raiz.dato == elem){
            return true;
        }
        
        else if(busquedaNodoOpadre(elem) != null && busquedaNodoOpadre(elem).dato.compareTo(elem) == 0){
            return true;
        }
        
        else{
            return false;
        }
    }
    public void eliminar(T elem){
        Nodo nodoOPadre = busquedaNodoOpadre(elem);
        if (pertenece(elem)){
            eliminarNodo(nodoOPadre);
            cardinal--;
        }
    }
    public void transplant(Nodo u,Nodo v){
        if (u.padre == null){
            raiz = v;
        }
        else if (u == u.padre.izq){
            u.padre.izq = v;
        }
        else{
            u.padre.der = v;
        }
        if (v != null){
            v.padre = u.padre;
        }
    }

    public void eliminarNodo(Nodo z){
        if (z.izq == null){
            transplant(z,z.der);
        }
        else if (z.der == null){
            transplant(z,z.izq);
        }
        else {
            Nodo y = leftmost(z.der);
            if (y.padre != z){
                transplant(y,y.der);
                y.der = z.der;
                y.der.padre = y;
            }
            transplant(z,y);
            y.izq = z.izq;
            y.izq.padre = y;
        }
    }
    public Nodo leftmost(Nodo root){
        while (root.izq != null){
            root = root.izq;
        }
        return root;
    }
    public String toString(){
        T actual = minimo();
        Iterador<T> it = new ABB_Iterador();
        String printeo = "{";
        int i = 0;
        while (i < cardinal - 1){
            actual = it.siguiente();
            printeo += actual + ",";
            i++;
        }
        printeo += it.siguiente() + "}"; 
        return printeo;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo actual = nodoMinimo();
        
        public boolean haySiguiente() {            
            return actual.padre != null;
        }
        public Nodo nodoMinimo(){
            while (raiz.izq != null){
                raiz = raiz.izq;
            }
            minimo = raiz;
            return minimo;
        }
        public T siguiente() { //Para evitar null tras haberlo recorrido todo habría que pedir que actual != maximo
            if (actual == null){
                return null;
            }
            T dato = actual.dato;
            if (actual.der != null){
                actual = leftmost(actual.der);
            }
            else {
                while (actual.padre != null && actual == actual.padre.der) { 
                    actual = actual.padre;
                }
                actual = actual.padre;
            }
            return dato;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
