package aed;

import java.util.Scanner;
import java.io.PrintStream;

class Archivos {
    float[] leerVector(Scanner entrada, int largo) {
        float[] vector = new float[largo];
        for (int i = 0; i < largo; i++) {
            vector[i] = entrada.nextFloat();
        }
        return vector;
    }

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float[][] matriz = new float[filas][columnas];
        for (int i = 0; i < filas; i++) {
            matriz[i] = leerVector(entrada, columnas);
        }
        return matriz;
    }

    void imprimirPiramide(PrintStream salida, int alto) {
        String string = "";
        for (int niveles = 1; niveles <= alto ; niveles ++){
            for (int caracteres = 1; caracteres <= (2*alto) -1 ; caracteres++){
                if ((caracteres > alto + niveles -1 ) || (caracteres < alto - niveles +1 )) {
                    string += " ";
                }
                else {
                    string += "*";
                }
            }
            salida.println(string);
            string = "";
        }
    } 
}
