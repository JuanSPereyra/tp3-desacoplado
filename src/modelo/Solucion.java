package modelo;

import java.util.HashSet;
import java.util.Set;


public class Solucion {
    private Instancia instancia;
    private Set<Vertice> vertices;
    private Set<Vertice> dominados;

    public Solucion(Instancia instancia) {
        this.instancia = instancia;
        this.vertices = new HashSet<>();
        this.dominados = new HashSet<>();
    }

    public void agregar(Vertice v) {
        vertices.add(v);
        dominados.add(v);
        dominados.addAll(instancia.vecinosDe(v));
    }

    public boolean contiene(Vertice v) {
        return vertices.contains(v);
    }

    public boolean estaDominado(Vertice v) {
        return dominados.contains(v);
    }

    public boolean dominioIgualGrafo() {
        return dominados.size() == instancia.getTamanio();
    }

    // vertices que pasarian a estar dominados si agregamos v
    public int nuevosCubiertos(Vertice v) {
    	int cantidad = 0;
    	
        if (!estaDominado(v)) {
            cantidad = 1;
        }
        for (Vertice vecino : instancia.vecinosDe(v)) {
            if (!estaDominado(vecino)) {
                cantidad++;
            }
        }
        return cantidad;
    }
    
    public int cardinal() {
        return vertices.size();
    }

    public Set<Vertice> getVertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public String toString() {
        return "Solucion " + vertices;
    }
}