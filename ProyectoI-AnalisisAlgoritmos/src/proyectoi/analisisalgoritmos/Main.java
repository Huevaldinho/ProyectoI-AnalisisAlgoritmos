
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
    static FuerzaBruta instanciaFuerzaBrutaQuemado;
    static FuerzaBruta instanciaFuerzaBrutaAleatorio;
    /**
     * Metodo para generar un numero randon en un rango de min a max. 
     * @param min: Numero menor para generar el random.
     * @param max: Numero mayor para generar el random.
     * @return Numero random entre [min,max]
     */
    public static int generarRandom(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
    /**
     *  Metodo para generar un array de tamano cantidadElementosArray con un rango de entre min y max.
     * @param cantidadElementosArray: Cantidad de numeros que se generaran para el arreglo.
     * @param min: Rango minimo para generar el random.
     * @param max: Rango maximo para generar el random.
     * @return Arreglo de tamanno cantidadElementosArray con elementos en el rango [min,max].
     */
    public static int[] generarArrayRandom(int cantidadElementosArray,int min, int max){
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
        //variables para todas las pruebas
        int [] arrayAleatorio;
        int min=-100;
        int max=100;

        //Cantidad numero en el conjunto: 3
        System.out.println("3 ELEMENTOS");
        int [] array = {-3,1,2};
        arrayAleatorio = generarArrayRandom(array.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 3");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 3");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 3");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array);
        
        System.out.println("ALEATORIO DINAMICO: 3");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
        //Cantidad numero en el conjunto: 4
        System.out.println("4 ELEMENTOS");
        
        int [] array4 = {-8,3,2,5};
        arrayAleatorio = generarArrayRandom(array4.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 4");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array4);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 4");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 4");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array4);
        
        System.out.println("ALEATORIO DINAMICO: 4");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
       
        //Cantidad numero en el conjunto: 5
        System.out.println("5 ELEMENTOS");
        
        int [] array5 = {-13,-7,3,8,10};
        arrayAleatorio = generarArrayRandom(array5.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 5");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array5);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 5");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(arrayAleatorio);
        
        
        System.out.println("QUEMADO DINAMICO: 5");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array5);
        
        System.out.println("ALEATORIO DINAMICO: 5");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
        
        //Cantidad numero en el conjunto: 6
        System.out.println("6 ELEMENTOS");
        
        int [] array6 = {7,2,8,-13,-3,-1};
        arrayAleatorio = generarArrayRandom(array6.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 6");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array6);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 6");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 6");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array6);
        
        System.out.println("ALEATORIO DINAMICO: 6");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
        
        //Cantidad numero en el conjunto: 7
        System.out.println("7 ELEMENTOS");
        
        int [] array7  = {-41,-9,10,15,3,2,20};
        arrayAleatorio = generarArrayRandom(array7.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 7");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array7);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 7");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 7");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array7);
        
        System.out.println("ALEATORIO DINAMICO: 7");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
        
        //Cantidad numero en el conjunto: 10
        System.out.println("10 ELEMENTOS");
        
        int [] array10 = {8,2,3,9,-12,-6,-4};
        arrayAleatorio = generarArrayRandom(array10.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 10");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array10);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 10");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 10");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array10);
        
        System.out.println("ALEATORIO DINAMICO: 10");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
        //Cantidad numero en el conjunto: 11
        System.out.println("11 ELEMENTOS");
        
        int [] array11 = {-77,-11,-13,-23,-40,30,6,3,1,15,17};
        arrayAleatorio = generarArrayRandom(array11.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 11");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array11);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 11");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DIMAICO: 11");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array11);
        
        System.out.println("ALEATORIO DINAMICO: 11");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        

        //Cantidad numero en el conjunto: 12
        System.out.println("12 ELEMENTOS");
        
        int [] array12 = {-4,-5,-2,3,6,7,10,13,17,-90,20,30}; //-4-2,6
        arrayAleatorio = generarArrayRandom(array12.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 12");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array12);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 12");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 12");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array12);
        
        System.out.println("ALEATORIO DINAMICO: 12");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
        
        //Cantidad numero en el conjunto: 13
        System.out.println("13 ELEMENTOS");
        
        int [] array13 = {-80,20,10,50,-73,-78,-61,-82,-54,-11,-68,-3,11}; 
        arrayAleatorio = generarArrayRandom(array13.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 13");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array13);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 13");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 13");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array13);
        
        System.out.println("ALEATORIO DINAMICO: 13");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
 
        //Cantidad numero en el conjunto: 14
        System.out.println("14 ELEMENTOS");
        
        int [] array14 = {7,12,21,-4,-2,-1,-3,5,8,9,10,11,-13,-18};
        arrayAleatorio = generarArrayRandom(array14.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 14");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array14);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 14");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 14");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array14);
        
        System.out.println("ALEATORIO DINAMICO: 14");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
        //Cantidad numero en el conjunto: 15
        System.out.println("15 ELEMENTOS");
        
        int [] array15 = {-19,9,1,5,14,6,17,-16,-15,-13,11,-33,-44,-55,-41};
        arrayAleatorio = generarArrayRandom(array15.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 15");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array15);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 15");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 15");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array15);
        
        System.out.println("ALEATORIO DINAMICO: 15");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");

        
        //Cantidad numero en el conjunto: 20
        System.out.println("20 ELEMENTOS");
        
        int [] array20 = {11,2,13,4,16,8,32,23,9,-20,-1,-5,-18,-45,-24,-7,17,19,-30,-21};
        arrayAleatorio = generarArrayRandom(array20.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 20");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array20);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 20");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 20");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array20);
        
        System.out.println("ALEATORIO DINAMICO: 20");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
        
        //Cantidad numero en el conjunto: 30 
        System.out.println("30 ELEMENTOS");
        
        int [] array30  = {17, -59, 38, 74, 27, -26, -27, 53, -95, -65, -87, 98, 30,
        -71, 56, -53, -68, 44, -24, -97, 29, 18, -34, -29, -10, 19, 1, -20, -50, 25};
        arrayAleatorio = generarArrayRandom(array30.length,min,max);
        
        System.out.println("QUEMADO FUERZA BRUTA: 30");
        instanciaFuerzaBrutaQuemado = new FuerzaBruta();
        instanciaFuerzaBrutaQuemado.findSets(array30);
        
        System.out.println("ALEATORIO FUERZA BRUTA: 30");
        instanciaFuerzaBrutaAleatorio = new FuerzaBruta();
        instanciaFuerzaBrutaAleatorio.findSets(arrayAleatorio);
        
        System.out.println("QUEMADO DINAMICO: 30");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(array30);
        
        System.out.println("ALEATORIO DINAMICO: 30");
        instanciaProgramacionDinamica = new Dinamico();
        instanciaProgramacionDinamica.findSets(arrayAleatorio);
        System.out.println("-------------------------------------");
    }
}
