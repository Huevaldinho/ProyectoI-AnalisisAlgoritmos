
package proyectoi.analisisalgoritmos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static java.lang.Math.abs;
import java.util.stream.IntStream;

/**
 *
 * @author Felipe Obando y Sophya
 * Idea de: https://skorks.com/2011/02/algorithms-a-dropbox-challenge-and-dynamic-programming/
 */
public class Dinamico{
    //Suma de todos los numeros positivos.
    private static int sumaPositivos;
    //Suma de todos los numeros negativos.
    private static int sumaNegativos;
    //Valores de las columnas segun la suma de positivos y negativos.
    private static HashMap<Integer, Integer> valorColumna;
    //Subconjunto que  sumado da 0
    private static List<Integer> subconjunto;
    //Variables para contar asignaciones y comparaciones
    static long asig;
    static long comp;
    static long startTime;
    static long endTime;
    
    public Dinamico() {
        asig = 0;
        comp = 0;
        startTime = 0;
        endTime = 0;
        sumaPositivos=0;
        sumaNegativos=0;
        valorColumna= new HashMap<>();
        subconjunto = new ArrayList<>();
    }
    /**
     * Metodo para sumar todos los negativos y todos los positivos.
     * @param array: Array de numeros enteros.
     */
    static void sumarNumeros(int array[]){
        for (int i=0;i<array.length;i++){
            if (array[i]>=0)
               sumaPositivos+=array[i];
            else
               sumaNegativos+=array[i];
        }
    }
//    /**
//     * Metodo para imprimir la matriz generada.
//     * @param array: Array de numeros
//     * @return int[][]: Matriz de tamano: filas = array.lenght y columnas = suma de todos los positivos + abs(suma de todos los negativos) +1 (cero).
//     */
//    static void imprimirMatriz(boolean [][]matriz,int array[]){
//        for (int i=0;i<matriz.length;i++){
//            for (int j=0;j<matriz[i].length;j++){
//                System.out.println("Array["+i+"]= "+array[i]+" y  Columna "+j+" - Valor columna= "+valorColumna.get(j)+" Celda: "+matriz[i][j]);
////                System.out.println("Fila "+i+" - Columna: "+j+" es: "+matriz[i][j]);
//                
//            }
//            System.out.println("");
//        }
//    }
    /**
     * Metodo para asignar el valor de cada columna segun las sumas de positivos y negativos
     * @param columnas: Cantidad de columnas de la matriz.
     */
    static void asignarValoresColumnas(int columnas){
        int p=1;//Variable para llevar contador de 1 hasta sumaPositivos para que la matriz asigne valores de menor a mayor
        for (int i = 0; i < columnas; i++){
            if (sumaNegativos<=0){
                valorColumna.put(i, sumaNegativos);
                sumaNegativos++;
            }else if(0<sumaPositivos){
                valorColumna.put(i,p);
                p++;
            }
        }
    }
    /**
     * Dado un conjunto de enteros, encontrar subconjunto que sumado de cero.
     * @param array: Arreglo donde se buscara el subconjunto que sumado de cero.
     */
    public void findSets(int array[]){
        Arrays.sort(array);//Lo ordena y toma el mas menor
        System.out.println("------------");
        System.out.println("Cantidad de elementos: "+array.length);
        System.out.println("Conjunto base: " + Arrays.toString(array));
        if (array.length==0){
            System.out.println(subconjunto);
            return;
        }
        sumarNumeros(array);

        //Si no hay negativos, tome el menor de los positivos (el arreglo esta ordenado de menor a mayor)
        startTime = System.nanoTime();//para tener el tiempo de cuando es solo positivos o solo negativos
        if (sumaNegativos==0){
            subconjunto.add(array[0]);//Agrega el menor de los positivos
            asig+=1;
            comp+=1;
        }else if (sumaPositivos==0){//No hay positivos, tome el mayor de los menores (el mas cercano a 0)
            subconjunto.add(array[array.length-1]);//Agrega el mayor de los negativos
            asig+=1;
            comp+=1;
        }else{//Hacer todo el proceso porque tenemos positivos y negativos
            //Crear matriz
            int numeroColumnas = sumaPositivos+abs(sumaNegativos)+1;//Cantidad de columnas necesarias
            boolean [][] matriz = new boolean[array.length][numeroColumnas];//Creacion de matriz
            //Asignar valores de columnas en el HashMap
            asignarValoresColumnas(numeroColumnas);
            //Empieza el algoritmo
            programacionDinamica(array,matriz,numeroColumnas);
        }
        endTime = System.nanoTime() - startTime; 
        
        
        if (IntStream.of(subconjunto.stream().mapToInt(i -> i).toArray()).sum()!=0)
            System.out.println("No hay subconjunto que sumado de cero, el mas aproximado es: ");
        System.out.println("Subconjunto encontrado: "+subconjunto);
        System.out.println("Asignaciones: "+asig);
        System.out.println("Compraciones: "+comp);
        System.out.println("Cantidad lineas ejecutadas: "+(asig+comp));
        System.out.printf("Tiempo total %.6f", (double)endTime/1000000000);
        System.out.println(" segundos.");
        System.out.println("Cantidad de lineas del algoritmo: "+192+"\n");
        
    }/**
     *  Metodo para conseguir el subconjunto utilizando programacion dinamica
     * almacena resultados en una matriz que utiliza para no repetir lo mismo varias veces.
     * @param array: Array del conjunto base
     * @param matriz: Matriz donde se guardan resultados
     * @param columnasTotales: Numero total de columnas de matriz.
     */
    static void programacionDinamica(int array[],boolean [][] matriz,int columnasTotales){
        asig+=3;//parametros
        int columnaBuscada=0;
        asig++;
        //La primera fila se trata por aparte porque no se puede subir una fila como en las demas.
        asig += 1;//asignacion del k en el for
        for (int k=0;k<matriz[0].length;k++){
            asig += 1;//incremento del for
            if (valorColumna.get(k)==array[0]){
                matriz[0][k]= true;
                asig+=1;//true
                break;
            }
            comp+=2;//if y for true
        }
        comp+=1;//false for
        asig += 1;//asignacion de fila en el for
        for (int fila=1;fila<matriz.length;fila++){//Recorremos todas las filas
            asig += 1;//del incremento de la fila
            comp+=1;//for fila true
            asig += 1;//asignación de columna del for
            for (int columna=0;columna<columnasTotales;columna++){//Recorremos las columnas de cada fila
                asig += 1; //del incremento de la columna
                comp+=1;//for columna true
                //La primera fila se trata diferente porque no se puede subir y da error de index
                //Valor de columna == valor del arreglo en el index de la fila
                if (valorColumna.get(columna)==array[fila]){
                    //System.out.println("Diagonal: "+valorColumna.get(columna)+" array fial: "+array[fila]);
                    matriz[fila][columna] = true;
                    asig+=1;//true
                    continue;
                }
                comp+=1;//if valorColumna
                
                asig += 1;//declaracion for key
                //key es el index de las columnas
                
                for (int key : valorColumna.keySet()){//Llaves del hashmap == index de las columnas
                    asig += 1; //del incremento
                    comp+=1;//for true
                    //valorColumna.get(key) valor de esa columna
                    //si el valor de la columna es igual a: valor de columna - valor del array en index fila
                    if (valorColumna.get(key)==valorColumna.get(columna)-array[fila]){
                        columnaBuscada=key;
                        //System.out.println("columna buscada: "+columnaBuscada);
                        asig+=1;//true
                        break;
                    }else{
                        columnaBuscada=-1;
                    }
                    comp+=1;//if
                    
                }
                comp+=1;//for key false
                //If para verificar que no intente saltar a una columna que no  exista(este caso es cuando el valor de la 
//columna es menor que el valor de la fila, ejemplo: valor columna 0, valor fila 2, entonces 0-2=-2, pero no existe columna con valor -2, entonces solo
//se copia el valor de arriba de la fila)
                if (columnaBuscada==-1){
                    matriz[fila][columna] = matriz[fila-1][columna];
                    asig+=1;
                    continue;
                }
                comp+=1;//if
                
                //Elemento array mayor o menor escoje la columna de arriba o la resta entre el valor de columna y el valor del array en ese indice
                //para "saltar"
                //Celda actual = celda arriba o matriz en fila de arriba en columna = valor columna - valor array en index fila
                matriz[fila][columna] = matriz[fila-1][columna]||matriz[fila-1][columnaBuscada];
                asig+=1;
            }
            comp+=1;//fase for columna
        }
        comp+=1;//false for fila
        //Lee matriz
        resultado(array,matriz);
    }
    /**
     * 
     * @param a: Array con numeros enteros.
     * @return int[][]: Retorna matriz con negativos y positivos separados. int[0] = Negativos, int[1] = Positivos.
     */
    static int[][] separadorPositivosNegativos(int a[]){
        Arrays.sort(a);//Ordena de menor a mayor para recorrer menos.
        List<Integer> nega =  new ArrayList<>();//Lista de negativos
        List<Integer> posi =  new ArrayList<>();//Lista de negativos
        for (int i=0;i<a.length;i++){//Recorre el arreglo a.
            if (a[i]<=0)//Si es negativo o 0. Restar o sumar 0 no afecta nada.
                nega.add(a[i]);//Lo agrega
            else//Si es positivo
                posi.add(a[i]);
        }
        //Retorna matriz con negativos y positivos separados.
        return new int[][]{nega.stream().mapToInt(Integer::intValue).toArray(),
            posi.stream().mapToInt(Integer::intValue).toArray()};
    }
    /**
     * Metodo para obtener el subconjunto que sumado sea mas cercano a cero pero no cero.
     * @param array: Conjunto base
     */
    static void aproximacion(int array []){
        int menorPositivos;
        int mayorNegativos;
        int restaMayorNegaMenorPosi;
        int restaMayorPosiMenorNega;
        int [][] separados = separadorPositivosNegativos(array);
        int [] nega=separados[0];
        int []posi=separados[1];
        Arrays.sort(nega);//Numeros negativos
        Arrays.sort(posi);//Numeros positivos
        menorPositivos = posi[0];
        mayorNegativos = nega[nega.length-1];
        restaMayorNegaMenorPosi = menorPositivos+mayorNegativos;//Se suma porque mayorNegativos es negativo o cero.
        restaMayorPosiMenorNega = posi[posi.length-1]+nega[0];
        //menor positivos < mayor negativos y menor positivos < resta de menor positivos - mayor negativos
        if (abs(menorPositivos)<abs(mayorNegativos) && abs(menorPositivos)<abs(restaMayorNegaMenorPosi) && abs(menorPositivos)<abs(restaMayorPosiMenorNega))
            subconjunto.add(menorPositivos);
        else if (abs(mayorNegativos)<abs(menorPositivos) && abs(mayorNegativos)<abs(restaMayorNegaMenorPosi)&&abs(mayorNegativos)<abs(restaMayorPosiMenorNega))
            subconjunto.add(mayorNegativos);
        else if (abs(restaMayorNegaMenorPosi)<abs(mayorNegativos)&&abs(restaMayorNegaMenorPosi)<abs(menorPositivos)&&abs(restaMayorNegaMenorPosi)<abs(restaMayorPosiMenorNega)){
            subconjunto.add(mayorNegativos);
            subconjunto.add(menorPositivos);
        }else{
            subconjunto.add(posi[posi.length-1]);//Mayor positivos
            subconjunto.add(nega[0]);//Menor negativos
        }
    }
    /**
     * Metodo para leer si existe o no un subconjunto que sumado de 0.
     * @param array: Arreglo del conjunto base.
     * @param matriz: Matriz completa.
     */
    static void resultado(int array[],boolean [][] matriz){
        int columna=0;
        //Saca el resultado de la columna del 0.
        for (int key : valorColumna.keySet()){
            if (valorColumna.get(key)==0){//Saca la columna del 0
                columna=key;
            }
        }
        //Si no hay solucion se sale
        //En este caso tenemos que tomar lo mas cercano a 0.
        //3 Opciones: El menor de los positivos, el mayor de los negativos o la resta de los dos anterioes mencionados
        if (matriz[matriz.length-1][columna]==false){//si no entra aqui y la columna del cero es false es porque la matriz esta mala
            aproximacion(array);
            return;
        }
        //Empieza desde la ultima fila
        //El ciclo sube de abajo hacia arriba la matriz
        for (int fila=matriz.length-1;fila>=0;fila--){
            if(fila==0  && columna>=0){//
                if (matriz[fila][columna]){
                    subconjunto.add(array[fila]);
                }
                break;
            }else if(columna<0)//Salto  imposible porque no hay indix negativos
                break;
            if(!matriz[fila-1][columna]){//Celda de arriba es false. Si la celda de arriba es true no se hace nada
                subconjunto.add(array[fila]);//Agrego al elemento donde estoy parado en la fila
                columna = valorColumna.get(columna) - array[fila];//Este es el valor de la columna dodne tengo que ir
                for (int key : valorColumna.keySet()){
                    if (valorColumna.get(key)==columna){
                        columna=key;
                        break;
                    }
                }
                //Suma todos los elementos del subconjunto
                //Esto es para asegurarnos que no entren mas datos que no deben
                if (IntStream.of(subconjunto.stream().mapToInt(i -> i).toArray()).sum()==0)
                    return;
            }   
        }   
    }
}