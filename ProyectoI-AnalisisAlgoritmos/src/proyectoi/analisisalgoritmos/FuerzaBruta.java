
package proyectoi.analisisalgoritmos;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Sumit Jain
 * Tomado de: https://algorithms.tutorialhorizon.com/print-all-subsets-of-an-array-with-a-sum-equal-to-zero/
 * Modificado para guardar el subconjunto que sumado de cero o el mas cercano.
 */

public class FuerzaBruta{
    //Variables GLOBALES para guardar las ASIGNACIONES y COMPARACIONES
    static long asig;
    static long comp;
    //Variable GLOBAL que va guardando la combinación que suma el número más cercano a cero
    static List<Integer> combMasCercana; 
    //Variable que guarda el número que suma la combinación (respaldo de currensum - GLOBAL)
    static int sumaMenor = 1000; 
    //También nos ayudará a saber si en algún momento se encuentra la combinación (sumMenor = 0)
    static long startTime;
    static long endTime;
    public FuerzaBruta(){
        asig = 0;
        comp = 0;
        List<Integer> combMasCercana; 
        sumaMenor = 1000; 
        startTime = 0;
        endTime = 0;
    }

    /***
     *  Metodo para verificar que el algoritmo no sea null, de lo contrario llama
     * al algoritmo de fuerza bruta.
     * @param array: Arreglo de numeros en el que se buscara si  hay subconjunto  que sume 0.
     */
    public void findSets(int [] array){
        Arrays.sort(array);//Ordena el arreglo
        System.out.println("------------");
        System.out.println("Cantidad de elementos: "+array.length);
        System.out.println("Conjunto base: " + Arrays.toString(array));
        List<Integer> subconjunto = new ArrayList<>();
        
        startTime = System.nanoTime();
        fuerzaBruta(array, 0, 0, subconjunto); 
        endTime = System.nanoTime() - startTime; 
        
        //System.out.println("Equivalente a "+ (double)endTime/1000000000+" segundos.");
        
        if(combMasCercana==null && array.length==0){//Si esto se cumple siginifica que es conjunto vacio
            combMasCercana = new ArrayList<>();
            sumaMenor = 0;
        }
        if (IntStream.of(combMasCercana.stream().mapToInt(i -> i).toArray()).sum()!=0)
            System.out.println("No hay subconjunto que sumado de cero, el mas aproximado es: ");
        System.out.println("Subconjunto encontrado: "+ combMasCercana);
        System.out.println("Asignaciones: "+asig);
        System.out.println("Comparaciones: "+comp);
        System.out.println("Cantidad lineas ejecutadas: "+(asig+comp));
        //Para que no muestre el número como 4E, 6E...etc (Imprime 6 decimales)
        System.out.printf("Tiempo total %.6f", (double)endTime/1000000000);
        System.out.println(" segundos.");
        System.out.println("Cantidad de lineas del algoritmo: "+44+"\n");
       
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
        asig+=4;//parametros
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
        asig += 1;//declaracion i for
        for (int i = iteradorFor; i < array.length; i++) { //recorremos todo el arreglo de números
            asig += 1; //incremento de i
            comp ++; //Cada vez que el for sea true. 
            subconjunto.add(array[i]); 
            asig ++; //Por agregar un elemento al subconjunto
            fuerzaBruta(array, suma + array[i], i + 1, subconjunto); //asignaciones y comparaciones se suman dentro.
            subconjunto.remove(subconjunto.size() - 1);
            asig ++; //Al quitar un elemento del subconjunto
        }
        comp++; //Cada vez que la comparación no se cumpla. 
    }
}