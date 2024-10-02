package aed;

public class distrito {
        private String nombreDistrito;
        private int bancasEnDisputa;
        private int[] ganadoresBancas;
        private int[] votosPorPartido;
        private int ultimaMesaDistrito;
        private int nrodistrito;
        private boolean hayResultados = false;
        private int cantidadVotosDiputados = 0;
    
        //Constructor distrito
        public distrito(String nombreDistrito, int nrodistrito, int bancasEnDisputa, int ultimaMesaDeDistrito, int cantidadPartidos){
            this.nombreDistrito = nombreDistrito; //Acceder a un campo de la clase y asignarle un valor dado O(1)
            this.bancasEnDisputa = bancasEnDisputa; //Acceder a un campo de la clase y asignarle un valor dado O(1)
            ultimaMesaDistrito = ultimaMesaDeDistrito; //Acceder a un campo de la clase y asignarle un valor dado O(1)
            votosPorPartido = new int[cantidadPartidos]; //Inicializar un array de tamaño P = O(P)
            ganadoresBancas = new int[cantidadPartidos]; //Inicializar un array de tamaño P = O(P)
            this.nrodistrito = nrodistrito; //Acceder a un campo de la clase y asignarle un valor dado O(1)
        }
        //Complejidad del constructor = O(P)
        public String nombreDistrito(){
            return nombreDistrito; //Acceder a un campo de la clase O(1)
        }
        public int bancasEnDisputa(){
            return bancasEnDisputa; //Acceder a un campo de la clase O(1)
        }
        public int nrodistrito(){
            return nrodistrito; //Acceder a un campo de la clase O(1)
        }
        public int[] resultadosDiputados(){
            return ganadoresBancas; //Acceder a un campo de la clase O(1)
        }
        public int cantidadVotosDiputados(){
            return cantidadVotosDiputados; //Acceder a un campo de la clase O(1)
        }
        public boolean hayResultados(){
            return hayResultados; //Acceder a un campo de la clase O(1)
        }
        public int ultimaMesaDistrito(){
            return ultimaMesaDistrito; //Acceder a un campo de la clase O(1)
        }
        public void incrementarVotosDiputados(int incremento){
            cantidadVotosDiputados += incremento; //Acceder a un campo de la clase y asignarle un valor dado O(1)
        }
        public void incrementarVotosPorPartido(int indice, int incremento){
            votosPorPartido[indice] += incremento; //Acceder a un campo de la clase y asignarle un valor dado O(1)
        }
        public int obtenerVotosPorPartido(int indice){
            return votosPorPartido[indice]; //Acceder a un campo de la clase, acceder a un array con su indice y retornar ese valor es O(1)
        }
        public void resultadosYaCalculados(){
            hayResultados = true; //Acceder a un campo de la clase y asignarle un valor dado O(1)
        }
       public void asignarBancaAPartido(int idPartido){
            ganadoresBancas[idPartido]++; //Acceder a un campo de la clase y asignarle un valor dado O(1)
       }
    }