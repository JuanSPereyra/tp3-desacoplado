package presentador;

import modelo.Instancia;
import modelo.Solucion;
import modelo.SolverGoloso;
import modelo.Vertice;
import vista.InterfazGestorPantalla;
import vista.PantallaResultado;

public class ResultadoPresentador {

	private PantallaResultado pantallaResultado;
	private InterfazGestorPantalla gestorInterfaz;
	private Instancia instancia;

	public ResultadoPresentador(PantallaResultado pantallaResultado, InterfazGestorPantalla gestorInterfaz, Instancia instancia) {
		this.pantallaResultado = pantallaResultado;
		this.gestorInterfaz = gestorInterfaz;
		this.instancia = instancia;
	}

	public void calcularSolucionDominante() {
		if (instancia == null || instancia.getVertices().isEmpty()) {
			pantallaResultado.mostrarResultado("No hay vértices en el grafo para calcular.");
			return;
		}

		SolverGoloso solver = new SolverGoloso(instancia);
		Solucion solucion = solver.resolverPorCobertura();

		StringBuilder sb = new StringBuilder();
		sb.append("Conjunto Dominante Mínimo\n");
		sb.append("Total de vértices en la solución: ").append(solucion.cardinal()).append("\n\n");
		sb.append("Vértices seleccionados:\n");

		for (Vertice v : solucion.getVertices()) {
			sb.append(". ").append(v.getNombre()).append("\n");
		}

		pantallaResultado.mostrarResultado(sb.toString());
	}

	public void manejarClickVolverAlMenu() {
		gestorInterfaz.crearPantallaInicio();
	}

	public void manejarClickBtnSalir() {
		System.exit(0);
	}
}