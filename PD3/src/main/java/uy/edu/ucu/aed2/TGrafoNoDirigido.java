package uy.edu.ucu.aed2;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoRedElectrica   {
protected TAristas lasAristas = new TAristas() ;
       /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
       super(vertices, aristas);     
      lasAristas.insertarAmbosSentidos(aristas);
       
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }
public TAristas getLasAristas() {
        return lasAristas;
    }

    // public TGrafoNoDirigido Kruskal() {
    //     TGrafoNoDirigido arbolCostoMinimo = new TGrafoNoDirigido(getVertices().values(),new TAristas());
    //     for (TArista tArista : lasAristas) {
    //         System.out.println(tArista);
    //     }
    //     TAristas aristasOrdenadas = lasAristas.copiarTAristasOrdenado();
    //     int aristasAgregadas = 0;
    //     while (aristasAgregadas != getVertices().size() - 1){
    //         TArista aristaMin = aristasOrdenadas.removeFirst();
    //         TVertice verticeOrigen = arbolCostoMinimo.buscarVertice(aristaMin.getEtiquetaOrigen());
    //         TVertice verticeDestino = arbolCostoMinimo.buscarVertice(aristaMin.getEtiquetaDestino());
    //         if (!arbolCostoMinimo.conectados(verticeOrigen, verticeDestino)){
    //             arbolCostoMinimo.insertarArista(aristaMin);
    //             arbolCostoMinimo.getLasAristas().add(aristaMin);
    //             arbolCostoMinimo.getLasAristas().add(aristaMin.aristaInversa());
    //             aristasAgregadas++;
    //         }
    //     }
    //     return arbolCostoMinimo;
    // }

    public boolean conectados(TVertice verticeOrigen, TVertice verticeDestino) {
        desvisitarVertices();
        if (verticeOrigen != null && verticeDestino != null) {
            if (verticeOrigen.getEtiqueta().equals(verticeDestino.getEtiqueta())) return true;
            return verticeOrigen.conectadoA(verticeDestino.getEtiqueta());
        }
        return false;
    }

    @Override
    public TAristas mejorRedElectrica() {
        TGrafoNoDirigido arbolCostoMinimo = new TGrafoNoDirigido(getVertices().values(),new TAristas());
        for (TArista tArista : lasAristas) {
            System.out.println(tArista);
        }
        TAristas aristasOrdenadas = lasAristas.copiarTAristasOrdenado();
        int aristasAgregadas = 0;
        while (aristasAgregadas != getVertices().size() - 1){
            TArista aristaMin = aristasOrdenadas.removeFirst();
            TVertice verticeOrigen = arbolCostoMinimo.buscarVertice(aristaMin.getEtiquetaOrigen());
            TVertice verticeDestino = arbolCostoMinimo.buscarVertice(aristaMin.getEtiquetaDestino());
            if (!arbolCostoMinimo.conectados(verticeOrigen, verticeDestino)){
                arbolCostoMinimo.insertarArista(aristaMin);
                arbolCostoMinimo.getLasAristas().add(aristaMin);
                arbolCostoMinimo.getLasAristas().add(aristaMin.aristaInversa());
                aristasAgregadas++;
            }
        }

        return arbolCostoMinimo.lasAristas;

        
    }

    
  


}
