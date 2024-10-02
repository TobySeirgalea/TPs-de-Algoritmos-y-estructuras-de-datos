package aed;
/* Invariante representación SistemaCNE: (Que utiliza las clases auxiliares distrito y partido)
 * Para todo P partido, i entero tal que  0 <= i < arrayDistritos.length() && p pertenece a sistemaCNE.heapParaDhontDistrito[i] si y solo si p pertenece a sistemaCNE.datosParaDhontParciales[p.idPartido]
 * &&L 
 * Para todo p tal que p pertenece a sistema.CNE.heapParaDhontDistrito[i] && 0 <= i < sistemaCNE.arrayDistritos.length() entonces p pertenece a arrayPartidos
 * &&L
 * sistemaCNE.fstMasVotado pertenece sistema.CNE.arrayPartidos && sistema.CNE.sndMasVotado pertenece sistemaCNE.arrayPartidos && sistemaCNE.fstMasVotado.votosPresidente > sistemaCNE.sndMasVotado.votosPresidente
 * &&L
 * sistemaCNE.fstMasVotado.votosPresidente + sistemaCNE.sndMasVotado.votosPresidente <= sistema.cantidadVotosPresidente
 * &&L
 * Para todo i, j enteros tal que 0 <= i < sistemaCNE.arrayPartidos.length() && 0 <= j < sistemaCNE.arrayDistritos.length() entonces sumatoria sistemaCNE.arrayDistrito[j].votosPorPartido[i] = sistemaCNE.arrayDistritos[j].cantidadVotosDiputados
 * &&L
 * Para todo j entero tal que 0 <= j < sistemaCNE.arrayDistritos.length() entonces sistemaCNE.arrayDistritos[i].ganadoresBancas.length() = sistemaCNE.arrayDistritos[i].bancasEnDisputa
 * &&L
 * Para todo i, j enteros tal que 0 <= i < sistemaCNE.arrayPartidos.length() && 0 <= j < sistemaCNE.arrayDistritos.length() entonces sistemaCNE.arrayDistritos[j].ganadoresBancas[i] = sistemaCNE.arrayPartidos[i].bancasObtenidas 
 * &&L
 * Para todo j entero tal que 0 <= j < sistemaCNE.arrayDistritos.length() entonces existe P partido perteneciente a sistemaCNE.arrayPartidos tal que sistemaCNE.arrayDistritos[j].votosPorPartido[p.idPartido] = p.votosDiputados 
 * &&L
 * Para todo k, j entero tal que 0 <= j < sistemaCNE.arrayDistritos.length() && 0 <= k < sistemaCNE.arrayDistritos[j].ganadoresBancas.length() && sistemaCNE.arrayDistritos[j].ganadoresBancas[k] = sistemaCNE.arrayDistritos[j].bancasEnDisputa <=> sistemaCNE.arrayDistritos[j].hayResultados = True
 * &&L
 * Para todo P partido, K distrito tal que P pertenece sistemaCNE.arrayPartidos && k pertenece sistemaCNE.arrayDistritos entonces sistemaCNE.arrayPartidos[P.idPartido].datoDhont = sistemaCNE.arrayDistritos[k].votosPorPartido[P.idPartido] / (p.bancasObtenidas + 1)
 */









public class SistemaCNE {
    // Completar atributos privados
    private partido fstMasvotado;
    private partido sndMasvotado;
    private int cantidadVotosPresidente;
    private distrito[] arrayDistritos;
    private partido[] arrayPartidos;
    private heap[] heapParaDhontDeDistrito;
    private partido[] datosParaHeapParciales;

    public class VotosPartido {
        private int presidente;
        private int diputados;

        VotosPartido(int presidente, int diputados) {
            this.presidente = presidente;
            this.diputados = diputados;
        }

        public int votosPresidente() {
            return presidente; //O(1)
        }

        public int votosDiputados() {
            return diputados; //O(1)
        }
    }
//Para este punto se requiere complejidad O(P*D)
    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        /* |nombresDistritos| = |diputadosPorDistrito| = |ultimasMesasDistrito| = D */
        /* |nombresPartidos| = P */

        arrayDistritos = new distrito[nombresDistritos.length]; // O(D), pues es crear un array de tamaño D
        arrayPartidos = new partido[nombresPartidos.length]; // O(P), pues es crear un array de tamaño P
        datosParaHeapParciales = new partido[nombresPartidos.length - 1]; // O(P) 
        heapParaDhontDeDistrito = new heap[nombresDistritos.length]; // O(D) 
        for (int i = 0; i < nombresDistritos.length; i++) { // O(D), ciclo que realiza D iteraciones
            distrito distritoAagregar = new distrito(nombresDistritos[i], i, diputadosPorDistrito[i], ultimasMesasDistritos[i], nombresPartidos.length); // O(P), pues en el se realizan asignaciones O(1) y la creación de dos arrays de longitud P 
            arrayDistritos[i] = distritoAagregar; // O(1) asignación
        } 
        /* La complejidad de este ciclo es O(D)*complejidad del cuerpo del ciclo
         * Complejidad cuerpo ciclo = O(P)
         * Por lo tanto este ciclo tiene complejidad O(P*D)
         */

        for (int j = 0; j < nombresPartidos.length; j++) { // O(P), ciclo que realiza P iteraciones
            partido partidoAagregar = new partido(nombresPartidos[j], j); // O(1) asignación y constructor cuyo costo es O(1) puesto que en el solo se realizan asignaciones O(1)
            arrayPartidos[j] = partidoAagregar; // O(1) asignacion de variable ya creada arriba
        }
        /* La complejidad de este ciclo es O(P)*complejidad cuerpo del ciclo
        Complejidad cuerpo ciclo = O(1)
        Por lo tanto este ciclo tiene complejidad = O(P)
        */
    } 
    /*Complejidad del método:
     * Como los ciclos estan uno debajo del otro, son independientes, entonces sus complejidades se suman
     * Resultando en complejidad = O(P*D) + O(P) = O(max{P*D, P}) = O(P*D)
     */

//Para este punto se requiere complejidad O(1)
    public String nombrePartido(int idPartido) {
        return arrayPartidos[idPartido].nombrePartido(); //Costo de acceder a una posición en un array + acceder a un campo de la clase partido = O(1)
    }
//Para este punto se requiere complejidad O(1)
    public String nombreDistrito(int idDistrito) {
        return arrayDistritos[idDistrito].nombreDistrito(); //Costo de acceder a una posición en un array + acceder a un campo de la clase distrito = O(1)
    }
//Para este punto se requiere complejidad O(1)
    public int diputadosEnDisputa(int idDistrito) {
        return arrayDistritos[idDistrito].bancasEnDisputa(); //Costo de acceder a una posición en un array + acceder a un campo de la clase distrito = O(1)
    }
//Para este punto se requiere complejidad O(log D)
    public String distritoDeMesa(int idMesa) {
        // Hacer binary search en arrayDistritos usando arrayDistritos.
        int high = arrayDistritos.length - 1;
        int low = 0;
        int mid = low + (high - low) / 2;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (idMesa < arrayDistritos[mid].ultimaMesaDistrito()) {
                high = mid;
            }
            else if (idMesa > arrayDistritos[mid].ultimaMesaDistrito()) {
                low = mid;
            } else { //Entra acá si idMesa == arrayDistritos[mid].ultimaMesaDistrito()
                return arrayDistritos[mid + 1].nombreDistrito();
            }
            if (low + 1 == high) {
                if (idMesa > arrayDistritos[low].ultimaMesaDistrito()) {
                    return arrayDistritos[high].nombreDistrito();
                }
                if (idMesa < arrayDistritos[low].ultimaMesaDistrito()) {
                    return arrayDistritos[low].nombreDistrito();
                }
            }
        }
        return "no está";
    }
    /*La complejidad del binary search es O(log N), siendo N la cantidad de elementos en el array
     * Como la dimension de la partición del array sobre el que se realiza la comparacion va reduciendose a la mitad por cada iteración
     * La negación de la guarda del while es = low > high
     * Nuestra función variante(fv) es high - low - 1
     *  (si fv <= 0 ) => ¬B
     * high - low - 1 <= 0  == high <= low + 1 == high < low == ¬B por lo tanto si la funcion variante llega a cero sale del ciclo
     * Vemos que high - low <= 1 implica high - low = 1, tomando t = cantidad de iteraciones para llegar a eso 
     */






    public int nroDistritoDeMesa(int idMesa) {
        // Hacer binary search en arrayDistritos usando arrayDistritos.
        int high = arrayDistritos.length - 1;
        int low = 0;
        int mid = low + (high - low) / 2;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (idMesa < arrayDistritos[mid].ultimaMesaDistrito()) {
                high = mid;
            }
            else if (idMesa > arrayDistritos[mid].ultimaMesaDistrito()) {
                low = mid;
            } else { // idMesa == arrayDistritos[mid].ultimaMesaDistrito
                return arrayDistritos[mid + 1].nrodistrito();
            }
            if (low + 1 == high) {
                if (idMesa > arrayDistritos[low].ultimaMesaDistrito()) {
                    return arrayDistritos[high].nrodistrito();
                }
                if (idMesa < arrayDistritos[low].ultimaMesaDistrito()) {
                    return arrayDistritos[low].nrodistrito();
                }
            }
        }
        return -1;
    }
//Para este punto se requiere complejidad O(1)
    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        int distritoMesa = nroDistritoDeMesa(idMesa); // Es un binary search = O(log D)
        for (int i = 0; i < actaMesa.length; i++) { // O(P)
            arrayPartidos[i].SumarVotosPresidente(actaMesa[i].votosPresidente()); //O(1), pues es acceder a un array y usar metodo SumarVotosPresidente de la clase partidos cuyo costo es O(1)
            cantidadVotosPresidente += actaMesa[i].votosPresidente(); //O(1), sumar a un campo de esta clase. Costo de copia del dato = O(1) + costo asignacion = O(1)
            arrayDistritos[distritoMesa].incrementarVotosDiputados(actaMesa[i].votosDiputados()); //O(1), pues es acceder a un array y usar metodo incrementarVotosDiputados de la clase distritos cuyo costo es O(1)
            arrayDistritos[distritoMesa].incrementarVotosPorPartido(i, actaMesa[i].votosDiputados()); //O(1), pues es acceder a un array y usar metodo incrementarVotosPorPartido de la clase distritos cuyo costo es O(1)
            arrayPartidos[i].SumarDatoDhont(actaMesa[i].votosDiputados()); //O(1), pues es acceder a un array y usar metodo SumarDatoDhont de la clase distritos cuyo costo es O(1)
            if (fstMasvotado == null || arrayPartidos[i].votosPresidente() > fstMasvotado.votosPresidente()) { //Son comparaciones de datos que se obtienen en O(1), por lo tanto es O(1)
                fstMasvotado = arrayPartidos[i]; //O(1) asignación
            } else if (sndMasvotado == null || arrayPartidos[i].votosPresidente() < fstMasvotado.votosPresidente() && arrayPartidos[i].votosPresidente() > sndMasvotado.votosPresidente()) { //Son comparaciones de datos que se obtienen en O(1), por lo tanto es O(1)
                sndMasvotado = arrayPartidos[i]; //O(1) asignación
            }
        }
//Para este punto se requiere complejidad O(P + log D)
        int agregados = 0; //O(1)
        for (int i = 0; i < arrayPartidos.length - 1; i++) { //Ciclo de P iteraciones, entonces es O(P)
            if (porcentaje(arrayDistritos[distritoMesa].obtenerVotosPorPartido(i), arrayDistritos[distritoMesa].cantidadVotosDiputados()) >= 3) {
                //Porcentajes es O(1) por se una operación entre enteros. Acceder a los datos es O(1) por ser accesos a array y campos con getters que son O(1), la comparación es una operación booleana de O(1)
                partido nuevoSinManosearPorOtrosDistritos = new partido(arrayPartidos[i].nombrePartido(), arrayPartidos[i].datoDhont(), arrayPartidos[i].idPartido(), arrayPartidos[i].votosDiputado()); 
                //Crear un partido tiene un costo O(1), acceder a un campo de un elemento en un array dado su indice es O(1) y aplicarle metodos getter de O(1) es O(1)
                datosParaHeapParciales[agregados] = nuevoSinManosearPorOtrosDistritos; //O(1) asignación
                agregados++; //O(1)
            }
        /* La complejidad de este ciclo es O(P) * complejidad cuerpo del ciclo
         * Complejidad cuerpo del ciclo = O(1)
         * Resultando en complejidad = O(P)
        */
        }
        partido[] datosParaHeap = new partido[agregados]; //Crear un array de longitud = agregados es O(agregador), pero agregados <= P, por lo tanto O(P)
        for (int j = 0; j < agregados; j++) { //Este ciclo es O(P), por que agregados está acotado por P
            datosParaHeap[j] = datosParaHeapParciales[j]; //Acceder a posicion en array y asignar = O(1)
            datosParaHeap[j].setDhont(arrayDistritos[distritoMesa].obtenerVotosPorPartido(j)); //O(1), pues es acceder a posicion en un array y realizar métodos O(1) (Su complejidad está en sus clases propias)
            datosParaHeap[j].setVotosDiputado(arrayDistritos[distritoMesa].obtenerVotosPorPartido(datosParaHeapParciales[j].idPartido())); //O(1), pues es acceder a posicion en un array y realizar métodos O(1)
        }
        heapParaDhontDeDistrito[distritoMesa] = new heap(datosParaHeap, distritoMesa); //Asignacion y crear un heap dado un array utilizando Algoritmo de Floyd = O(datosParaHeap.length), pero está acotado por P, resultando en O(P) 
    }
    /* Sumando las complejidades de cada parte se llega a que la complejidad del método es O(P + log D) */

    private float porcentaje(int a, int total) {
        return (a * 100) / total; //Operaciones con enteros O(1)
    }
//Para este punto se requiere complejidad O(1)
    public int votosPresidenciales(int idPartido) {
        return arrayPartidos[idPartido].votosPresidente(); //O(1), acceder a un array dado su indice y aplicar un método cuya complejidad es O(1)
    }
//Para este punto se requiere complejidad O(1)
    public int votosDiputados(int idPartido, int idDistrito) {
        return arrayDistritos[idDistrito].obtenerVotosPorPartido(idPartido); //O(1), acceder a un array dado su indice y aplicar un método cuya complejidad es O(1)

    }
//Para este punto se requiere complejidad O(Dd * log P)
    public int[] resultadosDiputados(int idDistrito) {
        /*  Descripción del algoritmo
        Recorrer array de todas las bancas en disputa, donde cada indice se refiere
        * al nro de banca
        * Obtener máximo del heap O(1).
        * Realizar ciclo con tantas iteraciones como cantidad de bancas a asignar en
        * ese distrito.
        * Desencolar, seria obtener el maximo y luego reestablecer el invariante del
        * heap.
        * Le sumo una banca
        * Actualizo su datoDhont dividiendo sus votos por sus bancas obtenidas + 1
        * Encolarlo nuevamente O(log p)
        * Realizar esto hasta asignar todas las bancas
        */
        if (arrayDistritos[idDistrito].hayResultados() == false) { //Acceder a un array dado su indice, aplicar un metodo de complejidad O(1) y realizar una comparación booleana = O(1)
            for (int i = 0; i < arrayDistritos[idDistrito].bancasEnDisputa(); i++) { //Ciclo que realiza #iteraciones = Bancas en disputa de diputados en el distrito = Dd. Complejidad O(Dd)
                partido max = heapParaDhontDeDistrito[idDistrito].desencolar(idDistrito); //Desencolar en un heap de P nodos es O(log P)
                if (max != null) { //Comparación booleana O(1)
                    arrayDistritos[idDistrito].asignarBancaAPartido(max.idPartido()); //Acceder a un array y realizar un método de complejidad O(1) es O(1)
                    max.sumarUnaBanca(); //O(1), las complejidades de estos métodos se encuentran en las clases a las que pertenecen
                    max.setDhont(max.votosDiputado() / (max.bancasObtenidas() + 1)); //Asignacion y operacion entre enteros, además de aplicar métodos O(1) es O(1)
                    heapParaDhontDeDistrito[idDistrito].encolar(max, idDistrito); //Encolar en un heap de P nodos es O(log P)
                }
            }
            arrayDistritos[idDistrito].resultadosYaCalculados(); //Método O(1), pues es acceso a una posición en un array y a ese valor aplicarle un método O(1) de su clase
        }
        return arrayDistritos[idDistrito].resultadosDiputados();
    }
    /* La complejidad de este ciclo es Dd* complejidad cuerpo del ciclo
     * Complejidad cuerpo del ciclo es log(P), siendo P la cantidad de partidos, que es >= que la cantidad de elementos en el heap, ya que solo pusimos en heap los que superen el porcentaje de 3%
     * Resultando en complejidad del método = O(Dd*log(P))
     */



//Para este punto se requiere complejidad O(1)
    public boolean hayBallotage() {
        boolean res; //Crear variable O(1)
        float porcentajePrimero = porcentaje(fstMasvotado.votosPresidente(), cantidadVotosPresidente); //O(1), pues los datos utilizados en porcentaje se acceden en O(1), 
        float porcentajeSegundo = porcentaje(sndMasvotado.votosPresidente(), cantidadVotosPresidente); //uno de ellos con un getter de O(1) de otra clase y otro es atributo de esta misma clase
        float diferencia = porcentajePrimero - porcentajeSegundo; //Crear e inicializar variable cuyo valor se obtiene con operaciones entre enteros es O(1)
        if (porcentajePrimero >= 45) { //Comparacion booleana con variable local obtenida en O(1) es O(1)
            res = false; //O(1) asignacion
        } else if (diferencia > 10 && porcentajePrimero >= 40) { //Comparacion booleana O(1)
            res = false; //O(1) asignacion
        } else { 
            res = true; //O(1) asignacion
        }
        return res;
    }
    //Finalmente sumando las complejidades llegamos a que es O(1)
}
