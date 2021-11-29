package uy.edu.ucu.aed2;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TVertice<T> implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private TVertice predecesor;
    private int bacon;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    /**
     * @return the predecesor
     */
    public TVertice getPredecesor() {
        return predecesor;
    }

    /**
     * @param predecesor the predecesor to set
     */
    public void setPredecesor(TVertice predecesor) {
        this.predecesor = predecesor;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    public boolean conectadoA(Comparable etiquetaDestino) {
        setVisitado(true);
        for (TAdyacencia ady : adyacentes) {
            TVertice destino = ady.getDestino();
            if (destino.getEtiqueta().equals(etiquetaDestino)) {
                return true;
            }
            if (!destino.getVisitado()) {
                if (destino.conectadoA(etiquetaDestino)) return true;
            }
        }
        return false;
    }

   
	public int getBacon() {
		return this.bacon;
	}

	
	public void setBacon(int newBacon) {
		this.bacon = newBacon;
		
	}

    
    public int numBacon(Comparable actor) {
        TVertice x;
        Queue<TVertice> c = new LinkedList<>();
        this.setBacon(0);
        this.setVisitado(true);
        c.add(this);
        while (!c.isEmpty()) {
            x = c.poll();
           for (TAdyacencia y:  (List<TAdyacencia>)x.getAdyacentes()) {
                if (!y.getDestino().getVisitado()) {
                    y.getDestino().setBacon(x.getBacon() + 1);
                    y.getDestino().setVisitado(true);
                    c.add(y.getDestino());
                    if(y.getDestino().getEtiqueta().compareTo(actor) == 0){
                        return y.getDestino().getBacon();
                        
                    }
                }
            }
        }
        
        return -1;
    }

    public void listarContactos (Collection<TVertice> visitados, int maxSaltos){
        TVertice x;
        Queue<TVertice> c = new LinkedList<>();
        this.setBacon(0);
        this.setVisitado(true);
        c.add(this);
        visitados.add(this);
        while (!c.isEmpty()) {
            x = c.poll();
           for (TAdyacencia y:  (List<TAdyacencia>)x.getAdyacentes()) {
                if (!y.getDestino().getVisitado()) {
                    y.getDestino().setBacon(x.getBacon() + 1);
                    y.getDestino().setVisitado(true);
                    c.add(y.getDestino());
                    if(y.getDestino().getBacon() <= maxSaltos){
                        visitados.add(y.getDestino());
                        
                    }
                }
            }
        }
    }

    public void bpf(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);
        for (TAdyacencia ady : adyacentes) {
            TVertice destino = ady.getDestino();
            if (!destino.getVisitado()) {
                destino.bpf(visitados);
            }
        }
    }

    public boolean hayCamino(TVertice verticeDestino){
        setVisitado(true);
        boolean res = false;
        for (TAdyacencia ady : adyacentes){
            TVertice destino = ady.getDestino();
            if(!destino.getVisitado()){
                if (destino.getEtiqueta().compareTo(verticeDestino.getEtiqueta()) == 0){
                    return true;
                }else{
                    res = destino.hayCamino(verticeDestino);
            }
            }
        }
        return res;
    }

    public void obtenerAnillos(TAnillosContagio losAnillos , int maxDistancia){
        TVertice x;
        Queue<TVertice> c = new LinkedList<>();
        this.setBacon(0);
        this.setVisitado(true);
        c.add(this);
        losAnillos.agregarContagio(this.getBacon(), this.getEtiqueta().toString());
        while (!c.isEmpty()) {
            x = c.poll();
           for (TAdyacencia y:  (List<TAdyacencia>)x.getAdyacentes()) {
                if (!y.getDestino().getVisitado()) {
                    y.getDestino().setBacon(x.getBacon() + 1);
                    y.getDestino().setVisitado(true);
                    c.add(y.getDestino());
                    if(maxDistancia == 0 || y.getDestino().getBacon() <= maxDistancia){
                        losAnillos.agregarContagio(y.getDestino().getBacon(), y.getDestino().getEtiqueta().toString());
                    }
                    // IDEM QUE LA CONDICION DEL OR
                    // else{
                    //     if(y.getDestino().getBacon() <= maxDistancia){
                    //         losAnillos.agregarContagio(y.getDestino().getBacon(), y.getDestino().getEtiqueta().toString());
                    //     }
                    // }
                }
            }
        }
    }



    
    
}
