/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoi.analisisalgoritmos;

/**
 *
 * @author Felipe Obando y Sophya 
 */
public class ProyectoIAnalisisAlgoritmos {

    /**
     * @param args the command line arguments
     */

    
    /*
    El método System.currentTimeMillis() devuelve la hora actual en milisegundos.
    Sin embargo, para medir la ejecución de un código (que puede ocurrir en menos de 1 milisegundo),
    se debe utilizar System.nanoTime(), que mide con precisión de nanosegundos.
    
    public static <E> long calculateETOrdered(int repetitions, int n) {
        long startTime = System.nanoTime();
        // ...
        // El resto del código
        long endTime = System.nanoTime();
        return (endTime-startTime);
    }
    */
    public static void pruebaContarMilisegundosPrograma(){
        for(int i=0;i<1000;i++){
            System.out.println("i: "+i);
        }
    }
    
    public static void main(String[] args) {
        //Tiempo guarda tiempo inicial en milisegundos de todo el programa
        long totalPrograma = System.currentTimeMillis();
        
        //Guarda el milisegundo antes de que llame a la funcion que queremos probar
        long startTime = System.currentTimeMillis();
        //Llama funcion
        pruebaContarMilisegundosPrograma();
        //Toma el tiempo justo despues de que termino de ejecutarse la funcion y 
        //le resta el tiempo que tomo antes de llamar a la funcion
        long endTime = System.currentTimeMillis() - startTime; 
        //Muestra el tiempo
        System.out.println("Tiempo funcion prueba contar milisegundos: "+endTime+" milisegundos");
        
        //Esto es nada mas para que el programa haga otras cosas y el tiempo final
        //se diferente al de la funcion.
        int a=0;
        for(int i=0;i<5000;i++){   
            a++;
            a*=10000;
            for (int j=0;j<i;j++)
                a-=1;
        }
        
        //Tiempo total de programa
        long finalTotalPrograma = System.currentTimeMillis()-totalPrograma;
        System.out.println("Total programa: "+finalTotalPrograma);
    }
    
}
