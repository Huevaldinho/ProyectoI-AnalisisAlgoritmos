
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
        int [] array = generarArrayRandom(7,-100,100);
        //int [] array = {-1,2,3,4};//error aproximacion, no es la mas cercana a 0
        //int [] array = {1,-2,-3,-4};
        //int [] array = {-5,-3,1,2,3,4};
        //int [] array={-2,2};
        //int [] array={-3,1};
        //int [] array={-25,-22,-12,1,3,6,10,15};
        Dinamico dinamico = new Dinamico();
        dinamico.subsetSum(array);
        
        FuerzaBruta fb= new FuerzaBruta();
        fb.findSets(array);
    }
}
