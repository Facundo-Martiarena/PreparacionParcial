package uy.edu.ucu.aed2;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
    //private Collection<TArista> aristas  = new LinkedList<TArista>();

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        for (TArista laa : this) {
            if ((laa.getEtiquetaOrigen().equals(etOrigen)) && laa.getEtiquetaDestino().equals(etDestino)) {
                return laa;
            }
        }

        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
       TArista tA = null;
        TArista tAMin = null;
        Double costoMin = Double.MAX_VALUE;
        for (Comparable u : VerticesU) {
            for (Comparable v : VerticesV) {
                tA = buscar(u,v);
                if (tA != null && tA.costo < costoMin) {
                    tAMin = tA;
                    costoMin = tA.costo;
                }
            }
        }
        return tAMin;
        //---------COMPLETAR ALGORITMO--------
        // para todo u en Vertices U
        // para todo v en Vertices V
        // tA =buscar (u, v)
        // si tA <> null y tA.costo < costoMin entonces
        // tAMin = tA y costoMin = tA.costo
        // fin para todo v
        // fin para todo u
        // devolver tAMin
        //To change body of generated methods, choose Tools | Templates.
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for(TArista tA : this){
            salida.append(tA.getEtiquetaOrigen()+SEPARADOR_ELEMENTOS_IMPRESOS+tA.getEtiquetaDestino()+SEPARADOR_ELEMENTOS_IMPRESOS+tA.getCosto()+"\n");
        }
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        TArista tempArista;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }

    public TAristas copiarTAristasOrdenado() {
        Comparator comp = new Comparator<TArista>() {
            public int compare(TArista ar1, TArista ar2){
                return (int) (ar1.getCosto() - ar2.getCosto());  
            } 
        };
        
        sort(comp);
        TAristas aristasNuevas = new TAristas();
        for (TArista ar : this) {
            aristasNuevas.add(ar);
        }
        return aristasNuevas;
    }

    
}

