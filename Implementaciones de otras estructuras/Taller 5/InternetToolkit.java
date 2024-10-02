package aed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class InternetToolkit {
    public InternetToolkit() {
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        // IMPLEMENTAR
        for (int i = 1; i < fragments.length; i++){
            insertionSort(fragments, i);
        }
        return fragments;
    }

    public void insertionSort(Fragment[] fragments,int i){
        int j = i - 1;
        Fragment elem = fragments[i];
        while (j >= 0 && fragments[j].compareTo(elem) == 1){
            fragments[j+1] = fragments[j];
            j--;
        }
        fragments[j+1] = elem;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        // IMPLEMENTAR
        Router[] filtrados =  new Router[k];
        Heap heap = new Heap(routers);
        int i = 0;
        while (i < filtrados.length){
            if (heap.askFirst().getTrafico() > umbral){
                filtrados[i] = heap.desencolar();
            }
        i++;
        }
        return filtrados;
    }
    /*
     Creas arreglo tamaño k = O(k)
     Creas heap con heapify O(n)
     Desencolas y lo metes en el array filtrado hasta llenarlo = O(k*log n)
     */





    public IPv4Address[] sortIPv4(String[] ipv4) {
        // IMPLEMENTAR
        IPv4Address[] arr = new IPv4Address[ipv4.length];
        for (int i = 0; i < ipv4.length; i++){
            IPv4Address ip = new IPv4Address(ipv4[i]);
            arr[i] = ip;
        }
        InsertionBucket sort = new InsertionBucket();
        sort.insertionSort(arr);
        return arr;
            
    }
}
