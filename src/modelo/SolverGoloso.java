package modelo;

import java.util.Comparator;

public class SolverGoloso {
    private Instancia instancia;

    public SolverGoloso(Instancia instancia) {
        this.instancia = instancia;
    }

    //estrategia 1: el que cubre mas vertices nuevos
    public Solucion resolverPorCobertura() {
    	Solucion parcial = new Solucion(instancia);
    	return resolver(parcial,(a, b) -> Integer.compare(parcial.nuevosCubiertos(b), parcial.nuevosCubiertos(a)));
    }

    //Estrategia 2: el de mayor grado
    public Solucion resolverPorGrado() {
        return resolver(new Solucion(instancia),(a, b) -> Integer.compare(instancia.grado(b), instancia.grado(a)));
    }
    //estraegia 3: peso de vertice mas urgente
    public Solucion resolverPorPesoDelVertice() {
        Solucion parcial = new Solucion(instancia);
        return resolver(parcial,(a,b) -> Double.compare(puntajePorVecino(b, parcial), puntajePorVecino(a, parcial)));
            
    }

    private Solucion resolver(Solucion sol, Comparator<Vertice> comparador) {
        // Los aislados siempre entran
        for (Vertice v : instancia.getVertices()) {
            if (instancia.grado(v) == 0) {
                sol.agregar(v);
            }
        }
        while (!sol.dominioIgualGrafo()) {
            sol.agregar(elegirMejor(sol, comparador));
        }
        return sol;
    }
    
    private Vertice elegirMejor(Solucion parcial, Comparator<Vertice> comparador) {
        Vertice mejor = null;

        for (Vertice v : instancia.getVertices()) {
            if (!parcial.contiene(v) && parcial.nuevosCubiertos(v) != 0) {
                if (mejor == null || esMejor(v, mejor, comparador)) {
                	mejor = v;
                }
            }
        }
        return mejor;
    }

    private boolean esMejor(Vertice candidato, Vertice actual, Comparator<Vertice> comparador) {
        int c = comparador.compare(candidato, actual);
        if (c != 0) {//si no hay empate devuelve dependiendo la comparacion
        	return c < 0;
        }
        //si tienen misma cantidad, determina por nombre
        return candidato.getNombre().compareTo(actual.getNombre()) < 0; 
    }
    
    private double puntajePorVecino(Vertice v, Solucion parcial) {
        double puntaje = 0;

        if (!parcial.estaDominado(v)) {
            puntaje =puntaje+ peso(v);
        }
        for (Vertice vecino : instancia.vecinosDe(v)) {
            if (!parcial.estaDominado(vecino)) {
            	puntaje =puntaje+ peso(vecino);
            }
        }
        return puntaje;
    }

    // Menos vecinos, mas urgente
    private double peso(Vertice v) {
        return 1.0 / (instancia.grado(v) + 1);
    }
}
