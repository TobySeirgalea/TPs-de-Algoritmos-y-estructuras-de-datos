package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    Nodo primero;
    int longitud;

    private class Nodo {
        // Completar
        T dato;
        Nodo sig;
        Nodo prev;

        Nodo(T data){
            this.dato = data;
        }
    }

    public ListaEnlazada() {
        Nodo primero = new Nodo(null);
        int longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        Nodo actual = primero;
        if (actual == null){
            primero = nuevo;
        } else {
            while (actual.prev != null) {
                actual = actual.prev;
            }
            nuevo.sig = actual;
            actual.prev = nuevo;
        }
        longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        Nodo actual = primero;
        if (primero == null) {
            primero = nuevo;
        } else {
            while (actual.sig != null) {
                actual = actual.sig;
            }
            actual.sig = nuevo;    
        }
        longitud++;
    }

    public T obtener(int i) {
        Nodo actual = primero;
        int j = 0;
        while (actual.prev != null){
            actual = actual.prev;
        }
        while (j != i) {
            actual = actual.sig;
            j++;
        }
        return actual.dato;
    }

    public void eliminar(int i) {
        Nodo actual = primero;
        int j = 0;
        while (actual.prev != null){
            actual = actual.prev;
        }
        while (j != i) {
            actual.sig.prev = actual;
            actual = actual.sig;
            
            j++;
        }
        if (actual.prev == null && actual.sig == null){ //Es el unico
            actual = null;
        }
        else if (actual.prev == null){ //Es el primero
            actual.sig.prev = null;
            actual.sig = null;
        }
        else if (actual.sig == null){ //Es el ultimo
            actual.prev.sig = null;
            actual.prev = null;
        }
        else if (actual.prev != null && actual.sig != null){ //Resto de casos
            actual.prev.sig = actual.sig;
            actual.sig.prev = actual.prev;
        }


        longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        int j = 0;
        while (actual.prev != null){
            actual = actual.prev;
        }
        while (j != indice) {
            actual = actual.sig;
            j++;
        }
        actual.dato = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        Nodo actual = primero;
        while (actual.prev != null){
            actual = actual.prev;
        }
        while (actual != null){
            copia.agregarAtras(actual.dato);
            actual = actual.sig;
            longitud++;
        }
        return copia;
    }
                
    public ListaEnlazada(ListaEnlazada<T> lista) {
    if (lista.primero == null) {
        this.primero = null;
        this.longitud = 0;
        return; // La lista original está vacía, por lo que la copia también debe estar vacía
    }

    Nodo actual = lista.primero;
    Nodo previoCopia = null;
    Nodo nuevoCopia;

    while (actual != null) {
        nuevoCopia = new Nodo(actual.dato);

        if (previoCopia == null) {
            this.primero = nuevoCopia;
        } else {
            previoCopia.sig = nuevoCopia;
        }

        previoCopia = nuevoCopia;
        actual = actual.sig;
        this.longitud++;
    }
}
    @Override
    public String toString() {
        String printeo = "[";
        Nodo actual = primero;
        while (actual.prev != null){
            actual = actual.prev;
        }
        while (actual.sig != null){
                printeo += actual.dato + ", ";
                actual = actual.sig;
            }
        printeo += actual.dato + "]";
        return printeo;
        }



    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        private Nodo actual;
        private int pos;
       // private Nodo siguiente;
       // private Nodo previo;
        ListaIterador() {
            actual = primero;
            pos = 0;
            /*if (primero != null && primero.sig != null){
                siguiente = primero.sig;
            }
            if (primero != null && primero.prev != null){
                previo = primero.prev;
            }*/
        }

        public boolean haySiguiente() {
	        // Nodo actual = primero; No quiero volver al primero para ver si hay siguiente, quiero ir manteniendo referencia
            return pos < longitud();
        }
        
        public boolean hayAnterior() {
	        // Nodo actual = primero;
            return pos > 0;
        }

        public T siguiente() {
          /*   T res = actual.dato;
            previo = actual;
            while (actual.prev != null){
                previo.prev = actual.prev;
                actual.prev = actual.prev.prev;
            }
            actual = siguiente; // Avanzamos al siguiente nodo
            if (siguiente != null) {
                siguiente = siguiente.sig;
            }
            return res; */
            
            T actual = obtener(pos);
            pos++;
            return actual;
        }
        

        public T anterior() {
	        // Nodo actual = primero; No tiene sentido porque así cada vez que quiero ir al sig empiezo en el primero y debería implementar un while de mas.
            pos--;
            T actual = obtener(pos);
            return actual;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}

