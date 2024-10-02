package aed;

public class Heap {
    private Router[] arrayBase;
    int longitud;
    //Constructor
    public Heap(Router[] base){
        arrayBase = arrayToHeap(base);
        longitud =  arrayBase.length;
    }

    public Router[] getHeap(){
        return arrayBase;
    }

    public Router askFirst(){
        return arrayBase[0];
    }



    public Router desencolar(){
        if (longitud >0){
            Router max = arrayBase[0];
            arrayBase[0] = arrayBase[longitud-1]; 
            arrayBase[longitud - 1] = null;
            longitud--; 
            heapify(arrayBase, 0);
            return max;
        }
        else {
            return null;
        }
    }

    public Router[] arrayToHeap(Router[] routers) {
        for (int i = (routers.length / 2) - 1; i > -1; i--){
            heapify(routers, i);
        }
        return routers;
    }

    public void heapify(Router[] array, int i){
        int left = 2*i + 1;
        int rigth = 2*i + 2;
        int max = 0;
        if (left < array.length && array[left] != null && array[left].getTrafico() > array[i].getTrafico()){
            max = left;
        }
        else {
            max = i;
        }
        if (rigth < array.length && array[rigth] != null && array[rigth].getTrafico() >= array[left].getTrafico() && array[rigth].getTrafico() >= array[i].getTrafico()){
            max = rigth;
        }
        if (max != i && max < array.length){
            Router temp = array[max];
            array[max] = array[i];
            array[i] = temp;
            heapify(array, max);
        }
    }
}

