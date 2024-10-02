package aed;
/*Invariante de representación del max-heap
 * esABB(arrayBase) &&L estaPerfectamenteBalanceado(arrayBase) &&L esIzquierdista(arrayBase)
 * Para todo i entero, 0 <= i < (arrayBase.length() -1)/2 entonces arrayBase[i].datoDhont >= arrayBase[2*i+1] && arrayBase[2*i+2].datoDhont <= arrayBase[i]
 *  &&L
 * Para todo i entero, 0 <= i < (arrayBase.length() -1)/2 entonces esHeap(arrayBase[2*i+1]) && esHeap(arrayBase[2*i+2])
 *
 * Inv Rep esABB(a) <=> Para todo valor de su subarbol izquierdo estos son menores que el nodo raiz y todos los del subarbol derecho son mayores que nodo raiz                    
 * estaPerfectamenteBalanceado(a) && esIzquierdista(a) <=> el array con el que se representa el heap no tiene elementos huecos, es decir posiciones contiguas vacias
 * 
 */

public class heap {
        private partido[] arrayBase;
        private int longitud;
        public heap(int longitud){
            arrayBase = new partido[longitud]; 
        }

        public heap(partido[] arrayBase, int idDistrito){
            this.arrayBase = construirHeapDesdeArray(arrayBase, idDistrito, arrayBase.length);
        }
        public partido obtener(int i){
            return arrayBase[i]; //O(1) obtener elemento de un array dado su indice
        }
        //Algoritmo heapify de Floyd, para construir un heap dado un array, su costo fue explicado en la teorica y eso O(n), siendo n la cantidad de nodos del heap
        public partido[] construirHeapDesdeArray(partido[] base, int idDistrito, int longitud){
            for (int i = base.length / 2 - 1; i > -1; i--){
                algoHeapify(base, i, idDistrito, longitud);
            }
            this.longitud = longitud;
            return base;
        }
        //Algoritmo visto en clase teórica donde dijo que su complejidad es O(log N), siendo N la cantidad de elementos del heap.
        public void algoHeapify(partido[] A, int i, int distritoMesa, int longitudDeA) { //Top down
            int left = 2*i + 1; //O(1)
            int right = 2*i + 2; //O(1)
            int max =  0; //O(1)
            if (left < longitudDeA && A[i] != null && A[left] != null && A[left].datoDhont() > A[i].datoDhont()){ //Comparacion booleana y se usan getters de O(1)
                max = left; //O(1)
            }
            else{
                max = i; //O(1)
            }
            if (right < longitudDeA && A[i] != null && A[left] != null && A[right] != null && A[right].datoDhont() >=A[left].datoDhont() && A[right].datoDhont() > A[i].datoDhont()){ //Son comparaciones y en los casos en los que se usa un getter para obtener el dato, estos métodos son de complejidad O(1)
                max = right; //O(1)
            }
            if (max != i){ //O(1) comparación booleana
                partido temp = A[max]; //O(1), puesto que todos los atributos de partido son constantes y no hay arrays
                A[max] = A[i]; //O(1)
                A[i] = temp; //O(1)
                algoHeapify(A, max, distritoMesa, longitudDeA); 
                /*Solo se vuelve a ejecutar su max != i, por lo tanto max == left o max == rigth cuyo valor es el doble del valor con el que se ingresó al método,
                 *por lo tanto su función variante se va duplicando por cada nueva llamada a la función y al realizar el despeje correspondiente se llega a que su complejidad es  O(log N), siendo N la cantidad de nodos del heap. 
                */
            }
    
        }
        //Este algoritmo va recorriendo una rama del arbol desde la hoja de inicio hasta la raíz, esto puede llevar en peor caso O(h), siendo h la altura del árbol. h = log(n) siendo n la cantidad de elementos del arbol.
        public void heapifyBottomUp(partido[] arr, int inicio,int idDistrito){
            int padre = (inicio - 1)/2; //O(1)
            if (padre >= 0){ 
                if (arr[padre] == null || arr[inicio].datoDhont() > arr[padre].datoDhont()){
                    partido temp = arr[inicio]; //O(1), puesto que todos los atributos de partido son constantes y no hay arrays
                    arr[inicio] = arr[padre]; //O(1), asignación
                    arr[padre] = temp; //O(1), asignación
                    heapifyBottomUp(arr, padre, idDistrito);
                }
                else if (inicio - 1 >= 0 && arr[padre].datoDhont() < arr[inicio - 1].datoDhont()){
                    partido temp = arr[inicio - 1];
                    arr[inicio - 1] = arr[padre]; //O(1), asignación
                    arr[padre] = temp; //O(1), asignación
                    heapifyBottomUp(arr, padre, idDistrito);
                }
            }
        }
        public void encolar(partido elemento, int idDistrito){
            int ultimo = this.longitud - 1; //O(1), asignación
            arrayBase[ultimo] = elemento; //O(1), asignación
            heapifyBottomUp(arrayBase, ultimo, idDistrito); //O(log N) siendo N la cantidad de nodos del heap
        }
        public partido desencolar(int idDistrito){
            if (this.longitud >0){
                partido max = arrayBase[0]; //O(1), asignación y acceso a un array
                arrayBase[0] = arrayBase[this.longitud-1]; //O(1), asignación
                arrayBase[this.longitud - 1] = null; //O(1), asignación
                algoHeapify(arrayBase, 0, idDistrito, this.longitud); //O(log N) siendo N la cantidad de nodos del heap
                return max;
            }
            else {
                return null;
            }
        }
    }       
    


