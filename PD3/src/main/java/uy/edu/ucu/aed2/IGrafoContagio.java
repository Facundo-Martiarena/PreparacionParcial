package uy.edu.ucu.aed2;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface IGrafoContagio {

 public TAnillosContagio anillosDeProbablesContagiados(String personaCOVID, int maxDistancia); 

}
