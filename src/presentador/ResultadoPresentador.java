package presentador;

import java.util.List;

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
		List<Solucion> soluciones = solver.resolver();

		StringBuilder sb = new StringBuilder();
		
		Integer num = 1;
		
		for (Solucion sol : soluciones) {
			sb.append("Solucion " + num).append("\n");
			sb.append("Total de vértices en la solución: ")
		      .append(sol.cardinal()).append("\n");
		    sb.append("Vértices seleccionados:\n");
		    for (Vertice v : sol.getVertices()) {
		        sb.append(". ").append(v.getNombre()).append("\n");
		    }
		    sb.append("\n");
		    
		    num ++;
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