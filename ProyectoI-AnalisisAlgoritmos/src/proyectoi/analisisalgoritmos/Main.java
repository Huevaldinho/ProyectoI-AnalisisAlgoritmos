
package proyectoi.analisisalgoritmos;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Felipe Obando y Sophya 
 */

public class Main{
    //static FuerzaBruta instanciaFuerzaBruta;
    static Dinamico instanciaProgramacionDinamica;
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
        //Cantidad numero en el conjunto: 3
        int [] array = {-3,1,2};
        int [] arrayAleatorio3 = generarArrayRandom(3,-100,100);
        System.out.println("QUEMADO FUERZA BRUTA: 3");
        FuerzaBruta instanciaFuerzaBrutaQuemado3 = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado3.findSets(array);
        
        
        System.out.println("ALEATORIO FUERZA BRUTA: 3");
        FuerzaBruta instanciaFuerzaBrutaAleatorio3 = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio3.findSets(arrayAleatorio3);
        
        
        System.out.println("QUEMADO DINAMICO: 3");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array);
        
        System.out.println("ALEATORIO DINAMICO: 3");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio3);
        //Cantidad numero en el conjunto: 4
        //Cantidad numero en el conjunto: 5
        //Cantidad numero en el conjunto: 6
        //Cantidad numero en el conjunto: 7
        //Cantidad numero en el conjunto: 10
        //Cantidad numero en el conjunto: 11
        //Cantidad numero en el conjunto: 12
        //Cantidad numero en el conjunto: 13
        //Cantidad numero en el conjunto: 14
        //Cantidad numero en el conjunto: 15
        //Cantidad numero en el conjunto: 20
        //Cantidad numero en el conjunto: 30 
    }
}
