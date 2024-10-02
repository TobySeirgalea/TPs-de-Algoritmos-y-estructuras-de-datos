package aed;

class Funciones {
    int cuadrado(int x) {
        // COMPLETAR
        int res = x*x;
        return res;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        double res = Math.sqrt(x*x + y*y);
        return res;
    }

    boolean esPar(int n) {
        // COMPLETAR
        boolean res = false;
        if (n % 2 == 0) {
            res = true;
        }
        return res;
    }
    /* Sin if
    boolean esPar(int n) {
        // COMPLETAR
        return (n % 2 == 0);
    }    
    */



    boolean esBisiesto(int n) {
        // COMPLETAR
        boolean res = false;
        if (((n % 4 == 0) && (n % 100 != 0)) || (n % 400 == 0)) {
            res = true;
        }
        return res;
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        // COMPLETAR
        if (n == 0) {
            return 1;
        }
        return n*factorialRecursivo(n - 1);
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        boolean res = true;
        if (n == 0 || n == 1) {
            return !res;
        }
        for (int i = 2; i < n; i++) {
            res &= !(n % i == 0);
        }
        return res;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        int suma_actual = 0;
        if (numeros.length != 0) {
           for (int i = 0; i < numeros.length; i++) {
                suma_actual += numeros[i];
            }
        }
        return suma_actual;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        int i = 0;
        while (numeros[i] != buscado) {
            i += 1;
        }
        return i;
    }

    boolean tienePrimo(int[] numeros) {
        // COMPLETAR
        boolean res = false;
        for (int i = 0; i < numeros.length; i++) {
            if (esPrimo((numeros[i]))) {
                res = true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        boolean res = true;
        for (int i = 0; i < numeros.length; i++) {
            if (esPar(numeros[i])) {
                res &= true;
            }
            else {
                res &= false;
            }
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        boolean res = true;
        if (s1.length() <= s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(i)) {
                    res &= true;
                }
                else {
                    res &= false;
                }
            }
        }
        else {
            res = false;
        }
        return res;
    }

    boolean esSufijo(String s1, String s2) {
        // COMPLETAR
        boolean res = true;
        if (s1.length() > s2.length()) {
            res = false;
        }
        else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt((s2.length()) - s1.length() + i)) {
                    res &= true;
                }
                else {
                    res &= false;
                }
            }
        }
        return res;
    }
}
