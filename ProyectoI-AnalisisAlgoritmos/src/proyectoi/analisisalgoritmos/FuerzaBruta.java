/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoi.analisisalgoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class FuerzaBruta {
    //Tomado de: https://algorithms.tutorialhorizon.com/print-all-subsets-of-an-array-with-a-sum-equal-to-zero/
    
    //Variable de lista
    
    
    //Agregar variable que lleve la combinacion mas cercana a 0 (usar valor absoluto para no jugar con signos)
    /***
     * 
     * @param arrA: Arreglo de numeros en el que se buscara si  hay subconjunto  que sume 0.
     */
    public void findSets(int [] arrA){
        System.out.println("Given Array: " + Arrays.toString(arrA) + ", required sum: " + 0);
        Arrays.sort(arrA);//Ordena el arreglo
        List<Integer> combinationList = new ArrayList<>();
        combinationUtil(arrA, 0, 0, combinationList);
    }
    /**
     * 
     * @param arrA: Arreglo de numeros.
     * @param currSum: Variable que determina si un subconjunto sumado da 0.
     * @param start: Iterador del arreglo.
     * @param combinationList: Subconjunto que se revisa si sumado da 0.
     */
    public void combinationUtil(int arrA[], int currSum, int start, List<Integer> combinationList) {
        if (currSum == 0 && combinationList.size() > 0) {
            System.out.println(combinationList);
            //Agregar variable global (de clase) para llevar la combinacion mas cercana a 0.
            return;
        }

        for (int i = start; i < arrA.length; i++) {
            if (currSum + arrA[i] > 0) //array is sorted, no need to check further
                break;
            combinationList.add(arrA[i]);
            combinationUtil(arrA, currSum + arrA[i], i + 1, combinationList);
            combinationList.remove(combinationList.size() - 1);
        }
    }
    public static void main(String[] args) {
        int [] arrA = {7,-7,3,5,2,1};
        FuerzaBruta p = new FuerzaBruta();
        p.findSets(arrA);
    }
}