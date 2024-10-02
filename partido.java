package aed;

public class partido {
        private String nombrePartido;
        private int bancasObtenidas;
        private int idPartido;
        private int datoDhont;
        private int votosPresidente;
        private int votosDiputado;
        public partido(String nombre, int j) {
            nombrePartido = nombre; //Acceder a un campo de la clase y asignarle un valor dado O(1)
            votosPresidente = 0; //Acceder a un campo de la clase y asignarle un valor dado O(1)
            idPartido = j; //Acceder a un campo de la clase y asignarle un valor dado O(1)
        } //Complejidad del constructor = O(1)
        public String nombrePartido(){
            return nombrePartido; //Acceder a un campo de la clase O(1)
        }
        public int idPartido(){
            return idPartido; //Acceder a un campo de la clase O(1)
        }
        public int datoDhont(){
            return datoDhont; //Acceder a un campo de la clase O(1)
        }
        public int votosDiputado(){
            return votosDiputado; //Acceder a un campo de la clase O(1)
        }
        public int bancasObtenidas(){
            return bancasObtenidas; //Acceder a un campo de la clase O(1)
        }
        public int votosPresidente(){
            return votosPresidente; //Acceder a un campo de la clase O(1)
        }
        public void SumarDatoDhont(int nuevoDhont){
            datoDhont += nuevoDhont; //Acceder a un campo de la clase y sumarle un valor dado O(1)
        }
        public void SumarVotosPresidente(int nuevo){
            votosPresidente += nuevo; //Acceder a un campo de la clase y sumarle un valor dado O(1)
        }
        public void sumarUnaBanca(){
            bancasObtenidas++; //Acceder a un campo de la clase y sumarle un valor dado O(1)
        }
        public void setDhont(int nuevoDhont){
            datoDhont = nuevoDhont; //Acceder a un campo de la clase y asignarle un valor dado O(1)
        }
        public void setVotosDiputado(int nuevo){
            votosDiputado = nuevo; //Acceder a un campo de la clase y asignarle un valor dado O(1)
        }
        public partido(String nombre, int datoDhont, int idPartido, int votosDiputado){
            nombrePartido = nombre; //Acceder a un campo de la clase y asignarle un valor dado O(1)
            this.votosDiputado = votosDiputado; //Acceder a un campo de la clase y asignarle un valor dado O(1)
            this.datoDhont = datoDhont; //Acceder a un campo de la clase y asignarle un valor dado O(1)
            this.idPartido = idPartido; //Acceder a un campo de la clase y asignarle un valor dado O(1)
        } //Complejidad del constructor = O(1)

    }


