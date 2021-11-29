package uy.edu.ucu.aed2;
import java.util.Collection;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public class TGrafoContagios extends TGrafoNoDirigido implements IGrafoContagio{
    
    public TGrafoContagios(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAnillosContagio anillosDeProbablesContagiados(String personaCOVID, int maxDistancia) {
        TVertice verticePersonaCovid = this.buscarVertice(personaCOVID);
        TAnillosContagio anillosContagio = new TAnillosContagio();
        if(verticePersonaCovid != null){
            verticePersonaCovid.obtenerAnillos(anillosContagio, maxDistancia);
        }
        return anillosContagio;
    }
    
  

          
    

}
