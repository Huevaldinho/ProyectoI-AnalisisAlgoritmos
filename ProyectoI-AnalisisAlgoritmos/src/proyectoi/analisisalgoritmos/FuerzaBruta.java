/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoi.analisisalgoritmos;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sumit Jain
 * Tomado de: https://algorithms.tutorialhorizon.com/print-all-subsets-of-an-array-with-a-sum-equal-to-zero/
 * Modificado para guardar el subconjunto que sumado de cero o el mas cercano.
 */

public class FuerzaBruta{
    //Variables GLOBALES para guardar las ASIGNACIONES y COMPARACIONES
    static int asig;
    static int comp;
    //Variable GLOBAL que va guardando la combinación que suma el número más cercano a cero
    static List<Integer> combMasCercana; 
    //Variable que guarda el número que suma la combinación (respaldo de currensum - GLOBAL)
    static int sumaMenor = 1000; 
    //También nos ayudará a saber si en algún momento se encuentra la combinación (sumMenor = 0)
    
    /***
     *  Metodo para verificar que el algoritmo no sea null, de lo contrario llama
     * al algoritmo de fuerza bruta.
     * @param array: Arreglo de numeros en el que se buscara si  hay subconjunto  que sume 0.
     * @return combMasCercana: Arreglo de números que suman cero o el número más cercano a cero. 
     */
    public List<Integer> findSets(int [] array){
        Arrays.sort(array);//Ordena el arreglo
        System.out.println("------Algoritmo Fuerza Bruta------");
        System.out.println("Conjunto base: " + Arrays.toString(array));
        System.out.println("Cantidad de elementos: "+array.length);
        List<Integer> subconjunto = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        fuerzaBruta(array, 0, 0, subconjunto); 
        long endTime = System.currentTimeMillis() - startTime; 
        System.out.println("Tiempo total: "+endTime);
        if(combMasCercana==null && array.length==0){//Si esto se cumple siginifica que es conjunto vacio
            combMasCercana = new ArrayList<>();
            sumaMenor = 0;
        }
        
        System.out.println("Subconjunto encontrado: "+ combMasCercana);
        System.out.println("Asignaciones: "+asig);
        System.out.println("Compraciones: "+comp);
        return combMasCercana;
    }
    /**
     * Algoritmo que encuentra (si existe) el subconjunto que sumado de cero. Si no
     * encuentra el subconjunto que sumado se acerca mas a cero.
     * @param array: Arreglo de numeros.
     * @param suma: Variable que determina si un subconjunto sumado da 0.
     * @param iteradorFor: Iterador del arreglo.
     * @param subconjunto: Subconjunto que se revisa si sumado da 0.
     */
    public void fuerzaBruta(int array[], int suma, int iteradorFor, List<Integer> subconjunto) {
        if(!subconjunto.isEmpty()){
            if(suma == 0){
                sumaMenor = 0;
                combMasCercana = new ArrayList<>(subconjunto);
                asig +=2;
                //return;
            }
            comp ++;
            if(abs(suma) < abs(sumaMenor)){ 
                sumaMenor = suma;//respaldamos a quien va a ser nuestra nueva referencia para la siguiente comparacion
                combMasCercana = new ArrayList<>(subconjunto); // Estamos frente a una combinación más cercana, entonces la guardamos
                asig +=2;
            }
            comp ++;
        }
        comp ++; //Siempre se va a comparar, sea True o False. 
        for (int i = iteradorFor; i < array.length; i++) { //recorremos todo el arreglo de números
            asig += 2; //asignación del for (i) y del incremento
            comp ++; //Cada vez que el for sea true. 
            subconjunto.add(array[i]); 
            asig ++; //Por agregar un elemento al subconjunto
            fuerzaBruta(array, suma + array[i], i + 1, subconjunto); //asignaciones y comparaciones se suman dentro.
            subconjunto.remove(subconjunto.size() - 1);
            asig ++; //Al quitar un elemento del subconjunto
        }
        comp++; //Cada vez que la comparación no se cumpla. 
        asig += 1; //Cada vez que la comparación no se cumpla, igual se hace la asignación. 
    }
    public static void main(String[] args) {
        //int [] arrA = {7,-7,3,5,2,1};
        //int [] arrA={-5,-3,-2,5,8};
        //int [] arrA={};
        int [] arrA = {1,2,3,4,5,6,7,8,9,10,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10};//{-500,-573,-584,-654,-745,344,-123,-574,-873,125,654,626,126,753,67,854,647,727,976,312};
        //int [] arrA = {9,-7,3,5,8,1};
        //int [] arrA = {2,3,4,5,10,15,20,30,40,50};
        //int [] arrA = {-20,-30,-40,-5,-10,-15,-20,-30,-40,-50,-1};
        FuerzaBruta p = new FuerzaBruta();
        
        p.findSets(arrA);
        
//        if (sumaMenor != 0){
//            System.out.println("NO se encontró una combinación que sume cero.");
//            System.out.println("La combinación más cercana a cero es: " +
//                combMasCercana + " la cual suma: " + sumaMenor);
//        }
//        else{
//            System.out.println("La combinación que suma cero es: "+ combMasCercana);
//        }
//        //System.out.println("Cantidad de recursiones con "+arrA.length+" elementos es: "+contarRecursion);
//        System.out.println("Cantidad de asignaciones: " + asig);
//        System.out.println("Cantidad de comparaciones: " + comp);
    }
}