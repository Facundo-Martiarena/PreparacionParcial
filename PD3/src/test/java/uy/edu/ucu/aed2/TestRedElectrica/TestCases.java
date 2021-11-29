package uy.edu.ucu.aed2.TestRedElectrica;

import org.junit.Before;
import org.junit.Test;

import uy.edu.ucu.aed2.TAnillosContagio;
import uy.edu.ucu.aed2.TArista;
import uy.edu.ucu.aed2.TAristas;
import uy.edu.ucu.aed2.TGrafoContagios;
import uy.edu.ucu.aed2.TGrafoDirigido;
import uy.edu.ucu.aed2.TGrafoNoDirigido;
import uy.edu.ucu.aed2.TGrafoRedElectrica;
import uy.edu.ucu.aed2.TVertice;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCases {
    
    Collection<TVertice> vertices;
    TAristas as;
    TGrafoRedElectrica g;
    TGrafoNoDirigido g2;
    TGrafoContagios gContagios;

    @Before
    public void setUp(){
        vertices = new LinkedList<>();
        TVertice v1 = new TVertice<>("1");
        TVertice v2 = new TVertice<>("2");
        TVertice v3 = new TVertice<>("3");
        TVertice v4 = new TVertice<>("4");
        TVertice v5 = new TVertice<>("5");
        
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
    }

    @Test
    public void mejorRedElectrica(){
        TArista a12 = new TArista("1", "2", 2);
        TArista a13 = new TArista("1", "3", 5);
        TArista a34 = new TArista("3", "4", 6);
        TArista a24 = new TArista("2", "4", 1);
        TArista a32 = new TArista("3", "2", 3);
        TArista a14 = new TArista("1", "4", 4);
        TArista a25 = new TArista("2", "5", 7);
        TArista a45 = new TArista("4", "5", 8);
        
        as = new TAristas();
        as.add(a12);
        as.add(a13);
        as.add(a34);
        as.add(a24);
        as.add(a32);
        as.add(a14);
        as.add(a25);
        as.add(a45);

        g = new TGrafoRedElectrica(vertices, as);

        TAristas aristasRed = g.mejorRedElectrica();
        Double costoCableado = 0d;
        for (TArista tArista : aristasRed) {
          costoCableado += tArista.getCosto();
        }
        Double expected = 13d;
        //assertEquals(2, aristasRed.size());
        assertEquals(expected,costoCableado);
    }

    @Test
    public void hayCamino(){
        TArista a12 = new TArista("1", "2", 2);
        TArista a23 = new TArista("2", "3", 5);
        TArista a34 = new TArista("3", "4", 6);
        
        
        as = new TAristas();
        as.add(a12);
        as.add(a23);
        as.add(a34);
        
        g2 = new TGrafoNoDirigido(vertices, as);

        boolean camino = g2.hayCamino("1", "4");
        boolean noCamino = g2.hayCamino("1", "5");
        
        assertTrue(camino);
        assertFalse(noCamino);
    }

    @Test
    public void anillosContagio(){
        TArista a12 = new TArista("1", "2", 2);
        TArista a13 = new TArista("1", "3", 5);
        TArista a34 = new TArista("3", "4", 6);
        TArista a24 = new TArista("2", "4", 1);
        TArista a32 = new TArista("3", "2", 3);
        TArista a14 = new TArista("1", "4", 4);
        TArista a25 = new TArista("2", "5", 7);
        TArista a45 = new TArista("4", "5", 8);
        
        as = new TAristas();
        as.add(a12);
        as.add(a13);
        as.add(a34);
        as.add(a24);
        as.add(a32);
        as.add(a14);
        as.add(a25);
        as.add(a45);
        
        gContagios = new TGrafoContagios(vertices, as);
        TAnillosContagio aContagio = gContagios.anillosDeProbablesContagiados("1", 1);
        int expected = aContagio.get(1).size();
       
        assertEquals(expected, 3);
    }

    @Test
    public void anillosContagioDeCero(){
        TArista a12 = new TArista("1", "2", 2);
        TArista a13 = new TArista("1", "3", 5);
        TArista a34 = new TArista("3", "4", 6);
        TArista a24 = new TArista("2", "4", 1);
        TArista a32 = new TArista("3", "2", 3);
        TArista a45 = new TArista("4", "5", 8);
        
        as = new TAristas();
        as.add(a12);
        as.add(a13);
        as.add(a34);
        as.add(a24);
        as.add(a32);
        as.add(a45);
        
        gContagios = new TGrafoContagios(vertices, as);
        TAnillosContagio aContagio = gContagios.anillosDeProbablesContagiados("1", 0);
        int expected = aContagio.size() - 1;
       
        assertEquals(expected, 3);
    }




    
}