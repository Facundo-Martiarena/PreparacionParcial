package uy.edu.ucu.aed2;

import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // cargar grafo con casas y distancias
        TGrafoRedElectrica laRed =  (TGrafoRedElectrica) UtilGrafos.cargarGrafo(
                "PD3/src/main/java/uy/edu/ucu/aed2/barrio.txt",
                "PD3/src/main/java/uy/edu/ucu/aed2/distancias.txt",
                false, TGrafoRedElectrica.class);
       
        /*
           
        calcular la mejor red electrica\
        listar en el archivo "redelectrica.txt" el costo total del cableado
        y las conexiones establecidas, una por linea (origen, destino, costo)
        
        */
        TAristas aristasRed = laRed.mejorRedElectrica();
        System.out.println(aristasRed.imprimirEtiquetas()); 
        Double costoCableado = 0d;
        for (TArista tArista : aristasRed) {
          costoCableado += tArista.getCosto();
        }
        System.out.println(costoCableado);
    }
}
