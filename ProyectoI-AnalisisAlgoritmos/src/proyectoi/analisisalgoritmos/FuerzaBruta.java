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
 * @author Usuario
 */
public class FuerzaBruta {
    //Tomado de: https://algorithms.tutorialhorizon.com/print-all-subsets-of-an-array-with-a-sum-equal-to-zero/
    
    //Variable GLOBAL que va guardando la combinación que suma el número más cercano a cero
    static List<Integer> combMasCercana; 
    
    //Variable que guarda el número que suma la combinación (respaldo de currensum - GLOBAL)
    static int sumaMenor = 1000; 
    //También nos ayudará a saber si en algún momento se encuentra la combinación (sumMenor = 0)
    
    
    /***
     * 
     * @param arrA: Arreglo de numeros en el que se buscara si  hay subconjunto  que sume 0.
     * @return combMasCercana: Arreglo de números que suman cero o el número más cercano a cero. 
     */
    public List<Integer> findSets(int [] arrA){
        System.out.println("Given Array: " + Arrays.toString(arrA) + ", required sum: " + 0);
        Arrays.sort(arrA);//Ordena el arreglo
        List<Integer> combinationList = new ArrayList<>();
        combinationUtil(arrA, 0, 0, combinationList);
        //return (combMasCercana.stream().mapToInt(Integer::intValue).toArray()); //convierte la lista en un array sencillo. 
        return combMasCercana;
    }
    

    /**
     * 
     * @param arrA: Arreglo de numeros.
     * @param currSum: Variable que determina si un subconjunto sumado da 0.
     * @param start: Iterador del arreglo.
     * @param combinationList: Subconjunto que se revisa si sumado da 0.
     */
    public void combinationUtil(int arrA[], int currSum, int start, List<Integer> combinationList) {
        
        if(combinationList.size() > 0){
            if(currSum == 0){
              sumaMenor = 0;
              combMasCercana = new ArrayList<>(combinationList);
              return;
            }
            if(abs(currSum) < abs(sumaMenor)){ 
            sumaMenor = currSum;//respaldamos a quien va a ser nuestra nueva referencia para la siguiente comparacion
            combMasCercana = new ArrayList<>(combinationList); // Estamos frente a una combinación más cercana, entonces la guardamos
            }
        }
      

        for (int i = start; i < arrA.length; i++) { //recorremos todo el arreglo de números
            if (currSum + arrA[i] > 0){ //array is sorted, no need to check further
                break;
            }
            combinationList.add(arrA[i]);
            combinationUtil(arrA, currSum + arrA[i], i + 1, combinationList);
            combinationList.remove(combinationList.size() - 1);
        }
        
    }
    public static void main(String[] args) {
        int [] arrA = {7,-7,3,5,2,1};
        //int [] arrA = {9,-7,3,5,8,1};
        FuerzaBruta p = new FuerzaBruta();
        p.findSets(arrA);
        if (sumaMenor != 0){
            System.out.println("NO se encontró una combinación que sume cero.");
            System.out.println("La combinación más cercana a cero es: " +
                combMasCercana + " la cual suma: " + sumaMenor);
        }
        else{
            System.out.println("La combinación que suma cero es: "+ combMasCercana);
        }
        
    }
}