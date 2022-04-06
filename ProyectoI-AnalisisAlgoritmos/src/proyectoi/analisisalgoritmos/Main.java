/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoi.analisisalgoritmos;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Felipe Obando y Sophya 
 */

public class Main{
    /**
     *  
     * @param min: Numero menor para generar el random.
     * @param max: Numero mayor para generar el random.
     * @return Numero random entre [min,max]
     */
    public static int generarRandom(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
    /**
     * 
     * @param cantidadElementosArray: Cantidad de numeros que se generaran para el arreglo.
     * @param min: Rango minimo para generar el random.
     * @param max: Rango maximo para generar el random.
     * @return Arreglo de tamanno cantidadElementosArray con elementos en el rango [min,max].
     */
    public static int[] generarArrayRandom(int cantidadElementosArray,int min, int max){
        //int []arreglo = new int[cantidadElementosArray];//De esta forma se pueden repetir numeros
//        for (int i=0;i<cantidadElementosArray;i++){
//            arreglo[i] = generarRandom(min,max);
//        }
        ArrayList<Integer> arreglo = new ArrayList<>();
        int i=0;
        int n;
        while (i<cantidadElementosArray){
            n= generarRandom(min,max);
            if (!arreglo.contains(n)){//Si no esta en la lista lo agrega
                arreglo.add(n);
                i++;
            }//Si no esta continua sin incrementar el ciclo
        }
        return arreglo.stream().mapToInt(Integer::intValue).toArray();
    }
  
    public static void main(String[] args){
        
        //Prueba dinamico con array random
        int [] array = generarArrayRandom(2,-100,100);
        //int [] array = {-1,2,3,4};
        Dinamico dinamico = new Dinamico();
        dinamico.subsetSum(array);
    }
}
