package aed;

public class InsertionBucket {
    public void insertionSort(IPv4Address[] array){
        for (int i = 1; i < array.length; i++){
            insertion(array, i);
        }
    }

    public void insertion(IPv4Address[] array, int i){
        int j = i - 1;
        IPv4Address elem = array[i];
        while (j >= 0 && hayQueSwapear(array[j], elem) == 1) {
            array[j + 1] = array[j];
            j = j - 1;            
        }
        array[j + 1] = elem;
    }

    public int hayQueSwapear(IPv4Address a, IPv4Address b){
        if (a.equals(b)){
            return 0; //0=NoSwap
        }
        else if (a.getOctet(0) > b.getOctet(0)) {
            return 1;
        }
        else if (a.getOctet(0) < b.getOctet(0)) {
            return -1;
        }
        else if (a.getOctet(1) > b.getOctet(1)) {
            return 1;
        }        
        else if (a.getOctet(1) < b.getOctet(1)) {
            return -1;
        }    
        else if (a.getOctet(2) > b.getOctet(2)) {
            return 1;
        }    
        else if (a.getOctet(2) < b.getOctet(2)) {
            return -1;
        }    
        else if (a.getOctet(3) > b.getOctet(3)) {
            return 1;
        }
        else{
            return -1;
        }
    }

}
