package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolverGoloso {
    private Instancia instancia;
    private List<Solucion> conjuntoSoluciones;

    public SolverGoloso(Instancia instancia) {
        this.instancia = instancia;
    }
    
	public List<Solucion> resolver() {
		conjuntoSoluciones = new ArrayList<>();
		
		Solucion solucion1 = resolverPorCobertura();
		Solucion solucion2 = resolverPorGrado();
		Solucion solucion3 = resolverPorPesoDelVertice();
		
		
		conjuntoSoluciones.add(solucion1);
		conjuntoSoluciones.add(solucion2);
		conjuntoSoluciones.add(solucion3);
		

		return conjuntoSoluciones;
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
