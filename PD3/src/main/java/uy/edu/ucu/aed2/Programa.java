package uy.edu.ucu.aed2;

import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TGrafoNoDirigido listaDeContactos = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
          "PD3/src/main/java/uy/edu/ucu/aed2/barrio.txt", 
          "PD3/src/main/java/uy/edu/ucu/aed2/distancias.txt", 
          false, TGrafoNoDirigido.class);


      // cargar grafo con casas y distancias
        // TGrafoRedElectrica laRed =  (TGrafoRedElectrica) UtilGrafos.cargarGrafo(
        //         "PD3/src/main/java/uy/edu/ucu/aed2/barrio.txt",
        //         "PD3/src/main/java/uy/edu/ucu/aed2/distancias.txt",
        //         false, TGrafoRedElectrica.class);
       
        /*
           
        calcular la mejor red electrica\
        listar en el archivo "redelectrica.txt" el costo total del cableado
        y las conexiones establecidas, una por linea (origen, destino, costo)
        
        */
        // TAristas aristasRed = laRed.mejorRedElectrica();
        // System.out.println(aristasRed.imprimirEtiquetas()); 
        // Double costoCableado = 0d;
        // for (TArista tArista : aristasRed) {
        //   costoCableado += tArista.getCosto();
        // }
        // System.out.println(costoCableado);

        int maxContact = 2;
        Collection<TVertice> contactos = listaDeContactos.listarContactos("CASA2", maxContact);
        


        // StringBuilder imprimir = new StringBuilder();
        // for (int i=1; i<= maxContact; i++){
          
        //   imprimir.append(contactos.stream().findFirst().get().getEtiqueta());
        //   for (TVertice tVertice : contactos) {
        //     if (tVertice.getBacon() == i){
        //       imprimir.append("-" + tVertice.getEtiqueta()+"-"+tVertice.getBacon());
        //     }
        //   }
        //   imprimir.append("\n");
        // }
        // System.out.println(imprimir);
          
          
        
        for (TVertice tVertice : contactos) {
          System.out.println(tVertice.getEtiqueta() + "-" + tVertice.getBacon());
        }



    }
}
