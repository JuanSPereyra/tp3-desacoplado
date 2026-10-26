package modelo;

import java.util.Comparator;
import java.util.List;

public class SolverGoloso {
	private Instancia instancia;
	private List<Solucion> conjuntoSoluciones;

	public SolverGoloso(Instancia instancia) {
		this.instancia = instancia;
	}

	public List<Solucion> resolver() {

		conjuntoSoluciones = List.of(

				resolverPorCobertura(),
				resolverPorMenosCobertura(),
				resolverPorGradoMayor(),
				resolverPorGradoMenor(),
				resolverPorNombre(),
				resolverPorNombreInverso(),
				resolverPorPesoDelVertice());

		return conjuntoSoluciones;
	}

	// estrategia 1: el que cubre mas vertices nuevos
	public Solucion resolverPorCobertura() {
		Solucion parcial = new Solucion(instancia);

		return resolver(parcial, (a, b) -> Integer.compare(parcial.nuevosCubiertos(b), parcial.nuevosCubiertos(a)));
	}

	// estrategia 2: opuesto al 1, el que cubre menos vertices nuevos
	public Solucion resolverPorMenosCobertura() {
		Solucion parcial = new Solucion(instancia);

		return resolver(parcial, (a, b) -> Integer.compare(parcial.nuevosCubiertos(a), parcial.nuevosCubiertos(b)));
	}

	// estrategia 3: el de mayor grado
	public Solucion resolverPorGradoMayor() {
		return resolver(new Solucion(instancia), (a, b) -> Integer.compare(instancia.grado(b), instancia.grado(a)));
	}

	// estrategia 4: inversa a la 3, toma menor grado primero
	public Solucion resolverPorGradoMenor() {
		return resolver(new Solucion(instancia), (a, b) -> Integer.compare(instancia.grado(a), instancia.grado(b)));
	}

	// estrategia 5: a-->z
	public Solucion resolverPorNombre() {
		return resolver(new Solucion(instancia), (a, b) -> a.getNombre().compareTo(b.getNombre()));
	}

	// estrategia 6: z--->a
	public Solucion resolverPorNombreInverso() {
		return resolver(new Solucion(instancia), (a, b) -> b.getNombre().compareTo(a.getNombre()));
	}

	// estrategia 7: peso de vertice mas urgente
	public Solucion resolverPorPesoDelVertice() {
		Solucion parcial = new Solucion(instancia);

		return resolver(parcial, (a, b) -> Double.compare(puntajePorVecino(b, parcial), puntajePorVecino(a, parcial)));
	}

	private Solucion resolver(Solucion sol, Comparator<Vertice> comparador) {

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
		if (c != 0) {
			return c < 0;
		}

		return candidato.getNombre().compareTo(actual.getNombre()) < 0;
	}

	private double puntajePorVecino(Vertice v, Solucion parcial) {
		double puntaje = 0;

		if (!parcial.estaDominado(v)) {
			puntaje = puntaje + peso(v);
		}
		for (Vertice vecino : instancia.vecinosDe(v)) {
			if (!parcial.estaDominado(vecino)) {
				puntaje = puntaje + peso(vecino);
			}
		}
		return puntaje;
	}

	private double peso(Vertice v) {
		return 1.0 / (instancia.grado(v) + 1);
	}
}
