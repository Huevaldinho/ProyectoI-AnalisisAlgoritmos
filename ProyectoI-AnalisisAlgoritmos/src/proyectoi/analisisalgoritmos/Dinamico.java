/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 */
public class Dinamico{ // PRUEBAS DE COMBINACIONES DE CODIGO PARA LLEGAR AL DINAMICO
    //Suma de todos los numeros positivos.
    private static int sumaPositivos;
    //Suma de todos los numeros negativos.
    private static int sumaNegativos;
    //Valores de las columnas segun la suma de positivos y negativos.
    private static HashMap<Integer, Integer> valorColumna;
    //Subconjunto que  sumado da 0
    private static List<Integer> subconjunto;

    public Dinamico() {
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
    /**
     * Metodo para imprimir la matriz generada.
     * @param array: Array de numeros
     * @return int[][]: Matriz de tamano: filas = array.lenght y columnas = suma de todos los positivos + abs(suma de todos los negativos) +1 (cero).
     */
    static void imprimirMatriz(boolean [][]matriz,int array[]){
        for (int i=0;i<matriz.length;i++){
            for (int j=0;j<matriz[i].length;j++){
                System.out.println("Valores: "+array[i]+" y "+valorColumna.get(j));
                System.out.println("Fila "+i+" - Columna: "+j+" es: "+matriz[i][j]);
                
            }
            System.out.println("");
        }
    }
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
    public void subsetSum(int array[]){
        if (array.length==0){
            System.out.println(subconjunto);
            return;
        }
        //Incicia variables
        sumaPositivos=0;
        sumaNegativos=0;
        valorColumna = new HashMap<>(); 
        subconjunto = new ArrayList<>();
        Arrays.sort(array);//Lo ordena y toma el mas menor
        sumarNumeros(array);
        //Si no hay negativos, tome el menor de los positivos (el arreglo esta ordenado de menor a mayor)
        if (sumaNegativos==0){
            subconjunto.add(array[0]);//Agrega el menor de los positivos
        }else if (sumaPositivos==0){//No hay positivos, tome el mayor de los menores (el mas cercano a 0)
            subconjunto.add(array[array.length-1]);//Agrega el mayor de los negativos
        }else{//Hacer todo el proceso porque tenemos positivos y negativos
            //Crear matriz
            int numeroColumnas = sumaPositivos+abs(sumaNegativos)+1;//Cantidad de columnas necesarias
            boolean [][] matriz = new boolean[array.length][numeroColumnas];//Creacion de matriz
            //Asignar valores de columnas en el HashMap
            asignarValoresColumnas(numeroColumnas);
            //Empieza el algoritmo
            conseguirSubconjunto(array,matriz,numeroColumnas);
        
        }
        System.out.println(subconjunto);
    }/**
     *  Metodo para conseguir el subconjunto utilizando programacion dinamica
     * almacena resultados en una matriz que utiliza para no repetir lo mismo varias veces.
     * @param array: Array del conjunto base
     * @param matriz: Matriz donde se guardan resultados
     * @param columnasTotales: Numero total de columnas de matriz.
     */
    static void conseguirSubconjunto(int array[],boolean [][] matriz,int columnasTotales){
        int columnaBuscada=0;
        //La primera fila se trata por aparte porque no se puede subir una fila como en las demas.
        for (int k=0;k<matriz[0].length;k++){
            if (valorColumna.get(k)==array[0]){
                matriz[0][k]=true;
                break;
            }
        }
        for (int fila=1;fila<matriz.length;fila++){//Recorremos todas las filas
            for (int columna=0;columna<columnasTotales;columna++){//Recorremos las columnas de cada fila
                //La primera fila se trata diferente porque no se puede subir y da error de index
                //Valor de columna == valor del arreglo en el index de la fila
                if (valorColumna.get(columna)==array[fila]){
                    matriz[fila][columna] = true;
                    continue;
                }
                //key es el index de las columnas
                for (int key : valorColumna.keySet()){//Llaves del hashmap
                    //valorColumna.get(key) valor de esa columna
                    //si el valor es igual al resultado de : valor de columna - valor del array en index fila
                    if (valorColumna.get(key)==valorColumna.get(columna)-array[fila]){
                        columnaBuscada=key;
                        break;
                    }
                }
                //Elemento array mayor
                if(array[fila]>valorColumna.get(columna)){
                    matriz[fila][columna] = matriz[fila-1][columna]||matriz[fila-1][columnaBuscada];
                }else{//Elemento array menor
                    //Celda actual = celda arriba o matriz en fila de arriba en columna = valor columna - valor array en index fila
                    matriz[fila][columna] = matriz[fila-1][columna] || matriz[fila-1][columnaBuscada];            
                }
            }
        }
        //Lee matriz
        resultado(array,matriz);
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
        //Si no hay solucion salgase
        if (!matriz[matriz.length-1][columna])
            return;
        //Empieza desde la ultima fila
        //El ciclo sube de abajo hacia arriba la matriz
        for (int fila=matriz.length-1;fila>=0;fila--){
            if(fila==0){
                if (matriz[fila][columna]){
                    subconjunto.add(array[fila]);
                }
                break;
            }
            if(!matriz[fila-1][columna]){//Celda de arriba es false. Si la celda de arriba es true no se hace nada
                subconjunto.add(array[fila]);//Agrego al elemento donde estoy parado en la fila
                columna = valorColumna.get(columna) - array[fila];//Este es el valor de la columna dodne tengo que ir
                for (int key : valorColumna.keySet()){
                    if (valorColumna.get(key)==columna){//Saca la columna del 0
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
    public static void main(String[] args) {
        int [] a = {7,-7,3,5,2,1};
        int [] b={-5,-3,-2,5,8};
        int [] c = {9,-7,3,5,8,1};//Falta este caso
        int d[]={-15,-6,1,2,3,5,7};
        int e[]={-650,-60,-73,-71,-3,50,125,25,7};
        int f[]={-596,-156,-2156,-52,-123};
        int g[]={52,12,53,68,62,51,63,6778};

        Dinamico dinamico = new Dinamico();
        System.out.println("Caso: Existe solucion");
        dinamico.subsetSum(a);
        System.out.println("Caso: Existe solucion");
        dinamico.subsetSum(b);
        System.out.println("Caso: Hay negativos y positivos pero suman 0");
        dinamico.subsetSum(c);
        System.out.println("Caso: Existe solucion");
        dinamico.subsetSum(d);
        System.out.println("Caso: Existe solucion");
        dinamico.subsetSum(e);
        System.out.println("Caso: Todos negativos");
        dinamico.subsetSum(f);
        System.out.println("Caso: Todos positivos");
        dinamico.subsetSum(g);

        //Falta agregar el caso donde no existe solucion pero hay que dar el subconjunto mas cercano a 0
    }
}