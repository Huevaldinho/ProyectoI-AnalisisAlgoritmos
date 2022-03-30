/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoi.analisisalgoritmos;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Dinamico {
        
    //Suma de todos los numeros positivos.
    private static int sumaPositivos=0;
    //Suma de todos los numeros negativos.
    private static int sumaNegativos=0;
    //Valores de las columnas segun la suma de positivos y negativos.
    private static HashMap<Integer, Integer> valorColumna = new HashMap<>(); 
    //Subconjunto que  sumado da 0
    private static List<Integer> subconjunto = new ArrayList<>();
    
    /**
     * 
     * @param array: Array de numeros enteros.
     */
    static void sumarNumeros(int array[]){
        for (int i=0;i<array.length;i++){
            if (array[i]>=0)
               sumaPositivos+=array[i];
            else
               sumaNegativos+=array[i];
        }
        System.out.println("Positivos: "+sumaPositivos+" Negativos: "+sumaNegativos);
    }/**
     * 
     * @param array: Arreglo de enteros a imprimir
     */
    static void imprimirArreglo(int array[]){
        System.out.println("Arreglo ingresado: ");
        for (int i=0;i<array.length;i++)
            System.out.println(array[i]);
    }
    /**
     * 
     * @param array: Array de numeros
     * @return int[][]: Matriz de tamano: filas = array.lenght y columnas = suma de todos los positivos + abs(suma de todos los negativos) +1 (cero).
     */
    static void imprimirMatriz(boolean [][]matriz,int array[]){
        //Imprimir tamano matriz.
        for (int i=0;i<matriz.length;i++){
            for (int j=0;j<matriz[i].length;j++){
                System.out.println("Valores: "+array[i]+" y "+valorColumna.get(j));
                System.out.println("Fila "+i+" - Columna: "+j+" es: "+matriz[i][j]);
                //System.out.print(matriz[i][j]+"     ");
                
            }
            System.out.println("");
        }
    }
    /**
     * 
     * @param columnas: Cantidad de columnas de la matriz.
     */
    static void asignarValoresColumnas(int columnas){
        //System.out.println("Cantidad de columnas: "+columnas);
        int p=1;//Variable para llevar contador de 1 hasta sumaPositivos
        for (int i = 0; i < columnas; i++){
            if (sumaNegativos<=0){
                valorColumna.put(i, sumaNegativos);
                sumaNegativos++;
            }else if (sumaNegativos==0){
                valorColumna.put(i,0);
            }else if(p<=sumaPositivos){
                valorColumna.put(i,p);
                p++;
            }
            
        }
        System.out.println(valorColumna);
    }
    /**
     * 
     * @param array: Arreglo donde se buscara el subconjunto que sumado de cero.
     */
    static void subsetSum(int array[]){
        //Arrays.sort(array);
        //imprimirArreglo(array);
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
            //imprimirMatriz(matriz);//Solo para ver si estan las columnas y filas que deberian estar
            //Asignar valores de columnas en el HashMap
            asignarValoresColumnas(numeroColumnas);
            //Hasta este punto tengo, matriz creada, columnas con valor entonces estoy
            //listo para hacer todo el proceso
            conseguirSubconjunto(array,matriz,numeroColumnas);
        
        }
        //System.out.println("Subconjunto: "+subconjunto);
    }
    static boolean puedoSumar(int valorColumna,int []arrayEnRango){
        
        
        return false;
    }
    static boolean conseguirSubconjunto(int array[],boolean [][] matriz,int columnasTotales){
        int columnaBuscada=0;
        //imprimirMatriz(matriz);
        int primeraFilaRevisar=0;
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
                    matriz[fila][columna]=true;
                    continue;
                }
                //key es el index de las columnas
                for (int key : valorColumna.keySet()){//Llaves del hashmap
                    //System.out.println(key);
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
                }
                //Elemento array menor
                if (array[fila]<valorColumna.get(columna)){
                    //Celda actual        = celda arriba            o  matriz en fila de arriba en columna= valor columna - valor array en index fila
                    matriz[fila][columna] = matriz[fila-1][columna] || matriz[fila-1][columnaBuscada];            
                }
                
            }
        }
        
        imprimirMatriz(matriz,array);
        //Retornar el valor que buscamos, o sea, el valor que tiene la columna con valor 0.
        //Dependiendo de eso sabemos si existe o no subconjunto, si existe lo mandamos a sacar
        resultado(array,matriz);
        return false;
    }
    static void resultado(int array[],boolean [][] matriz){
        int columna=0;
        for (int key : valorColumna.keySet()){
                if (valorColumna.get(key)==0){//Saca la columna del 0
                    columna=key;
                }
        }
        //Si no hay solucion salgase
        if (!matriz[matriz.length-1][columna])
            return;
        //Empieza desde la ultima fila
        //Se hace mientras la fila sea mayor que 0
        //El ciclo sube de abajo hacia arriba la matriz
        //System.out.println("key del cero: "+columna+" fila empieza: "+(matriz.length-1));
        for (int fila=matriz.length-1;fila>=0;fila--){
            if(fila==0){
                if (matriz[fila][columna]){
                    subconjunto.add(array[fila]);
                }
                break;
            }
            if (matriz[fila-1][columna]==true)//celda de arriba es true, solo subo
                continue;
            else if(!matriz[fila-1][columna]){//Celda de arriba es false
                subconjunto.add(array[fila]);//Agrego al elemento donde estoy parado en la fila
                columna = valorColumna.get(columna) - array[fila];//Este es el valor de la columna dodne tengo que ir
                System.out.println("Tengo que ir a la columna etiquetada: "+columna);
                for (int key : valorColumna.keySet()){
                    if (valorColumna.get(key)==columna){//Saca la columna del 0
                        System.out.println("Valor: "+valorColumna.get(key)+" - Columna: "+key);
                        columna=key;
                        break;
                    }
                }
            }
            
        }
        System.out.println(subconjunto);
    }
    
    
    public static void main(String[] args) {
           int a[]={-15,-6,1,2,5,7};
           //sumarNumeros(a);
           subsetSum(a);
        
    }
}
