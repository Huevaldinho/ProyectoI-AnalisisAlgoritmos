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
        System.out.println("------Algoritmo Programacion Dinamica------");
        System.out.println("Conjunto base: " + Arrays.toString(array));
        System.out.println("Cantidad de elementos: "+array.length);
        if (array.length==0){
            System.out.println(subconjunto);
            return;
        }
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
        System.out.println("Subconjunto encontrado: "+subconjunto);
        System.out.println("Asignaciones: "+asig);
        System.out.println("Compraciones: "+comp);
    }/**
     *  Metodo para conseguir el subconjunto utilizando programacion dinamica
     * almacena resultados en una matriz que utiliza para no repetir lo mismo varias veces.
     * @param array: Array del conjunto base
     * @param matriz: Matriz donde se guardan resultados
     * @param columnasTotales: Numero total de columnas de matriz.
     */
    static void conseguirSubconjunto(int array[],boolean [][] matriz,int columnasTotales){
        asig+=3;//parametros
        long startTime = System.currentTimeMillis();
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
                    matriz[fila][columna] = true;
                    asig+=1;//true
                    continue;
                }
                comp+=1;//if valorColumna
                
                asig += 1;//declaracion for key
                //key es el index de las columnas
                for (int key : valorColumna.keySet()){//Llaves del hashmap
                    asig += 1; //del incremento
                    comp+=1;//for true
                    //valorColumna.get(key) valor de esa columna
                    //si el valor es igual al resultado de : valor de columna - valor del array en index fila
                    if (valorColumna.get(key)==valorColumna.get(columna)-array[fila]){
                        columnaBuscada=key;
                        asig+=1;//true
                        break;
                    }
                    comp+=1;//if
                }
                comp+=1;//for key false
                
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
        long endTime = System.currentTimeMillis() - startTime; 
        System.out.println("Tiempo total: "+endTime);
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
        int restaMayorMenor;
        int [][] separados = separadorPositivosNegativos(array);
        int [] nega=separados[0];
        int []posi=separados[1];
        Arrays.sort(nega);//Numeros negativos
        Arrays.sort(posi);//Numeros positivos
        menorPositivos = posi[0];
        System.out.println("Menor positivo: "+menorPositivos);
        mayorNegativos = nega[nega.length-1];
        System.out.println("Mayor negativo: "+mayorNegativos);
        restaMayorMenor = menorPositivos+mayorNegativos;//Se suma porque mayorNegativos es negativo o cero.
        if (abs(menorPositivos)<abs(mayorNegativos) && abs(menorPositivos)<abs(restaMayorMenor))
            subconjunto.add(menorPositivos);
        else if (abs(mayorNegativos)<abs(menorPositivos) && abs(mayorNegativos)<abs(restaMayorMenor))
            subconjunto.add(mayorNegativos);
        else{
            subconjunto.add(mayorNegativos);
            subconjunto.add(menorPositivos);
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
        if (matriz[matriz.length-1][columna]==false){
            aproximacion(array);
            return;
        }
        //Empieza desde la ultima fila
        //El ciclo sube de abajo hacia arriba la matriz
        for (int fila=matriz.length-1;fila>=0;fila--){
            if(fila==0&& columna>=0 ){//
                if (matriz[fila][columna]){
                    subconjunto.add(array[fila]);
                }
            }
            else if (fila==0)//la columna es negativa
                break;
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
}