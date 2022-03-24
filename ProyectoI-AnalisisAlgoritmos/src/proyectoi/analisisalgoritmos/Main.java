/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoi.analisisalgoritmos;

import java.util.Random;

/**
 *
 * @author Usuario
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
        int []arreglo = new int[cantidadElementosArray];
        for (int i=0;i<cantidadElementosArray;i++){
            arreglo[i] = generarRandom(min,max);
        }
        return arreglo;
    }
  
    public static void main(String[] args){
        System.out.println("Main");
       //Prueba  de funciones de generar random y arreglo
        int [] array = generarArrayRandom(30,-100,100);
        for (int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
        //Falta modificar algoritmo de fuerza bruta
        //Falta encontrar o hacer el algoritmo dinamico
    }
}
