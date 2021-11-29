package uy.edu.ucu.aed2;

import java.util.TreeSet;

public class Parcial2 {

    public static void main(String[] args) {
        
        
        // CREAR EL GRAFO CON PERSONAS.TXT y CONTACTOS.TXT
        
        TGrafoContagios grafoTrazabilidad = (TGrafoContagios) UtilGrafos.cargarGrafo(
                "PD3/src/main/java/uy/edu/ucu/aed2/personas.txt",
                "PD3/src/main/java/uy/edu/ucu/aed2/contactos.txt",
                false, TGrafoContagios.class);

    
    
        
        String personaCOVID = "Tom_Hanks";
        int maxDistancia = 0;
        
        // invocar al método "anillosDeProbablesContagiados" con estos parámetros;
        TAnillosContagio anillosContagio = grafoTrazabilidad.anillosDeProbablesContagiados(personaCOVID, maxDistancia);

        // emitir por consola la cantidad de contactos que se encuentran a la distancia
        // de la personaCOVID indicada EN EL PIZARRÓN: distAnilloBuscar

         //int distAnilloBuscar = SE INDICARA EN EL PIZARRÓN

         //MANEJO DE MAP
         StringBuilder laCadena = new StringBuilder();
         laCadena.append("Persona con covid: " + anillosContagio.get(0).first() + "\n");
         for (Integer i : anillosContagio.keySet()) {
            TreeSet<String> personasEnContacto = anillosContagio.get(i);
            laCadena.append("Anillo: " + i + " -> ");
            for (String persona : personasEnContacto){
                laCadena.append(persona + " | ");
            }
            laCadena.append("\n");
         }

        System.out.println(laCadena);

                 
         // EMITIR EL ARCHIVO "ANILLOS.TXT", CON EL FORMATO INDICADO EN LA LETRA, 
         // CON LOS CONJUNTOS DE CONTACTOS HASTA UNA DISTANCIA MENOR O IGUAL A LA INDICADA
         // EN EL PIZARRÓN: distMaxParaArchivoListado
        
        // int distMaxParaArchivoListado = // SE INDICARÁ EN EL PIZARRÓN
                  
        
        
        
       
    }
}
