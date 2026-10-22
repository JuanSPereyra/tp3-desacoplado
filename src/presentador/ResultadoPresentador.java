package presentador;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import modelo.Instancia;
import modelo.Solucion;
import modelo.SolverGoloso;
import modelo.Vertice;
import util.Par;
import vista.interfaz.InterfazGestorPantalla;
import vista.interfaz.ResultadoVista;

public class ResultadoPresentador {

	private ResultadoVista _rVista;
	private InterfazGestorPantalla gestorInterfaz;
	private Instancia instancia;
	private List<Solucion> soluciones;

	public ResultadoPresentador(ResultadoVista resultadoVista, InterfazGestorPantalla gestorInterfaz, Instancia instancia) {
		this._rVista = resultadoVista;
		this.gestorInterfaz = gestorInterfaz;
		this.instancia = instancia;
	}

	public void calcularSolucionDominante() {

		SolverGoloso solver = new SolverGoloso(instancia);
		soluciones = solver.resolver();
		
		_rVista.completarComboSoluciones(soluciones.size());

		StringBuilder sb = construirRespuestaEnString();
		_rVista.mostrarResultado(sb.toString());
	}

	private StringBuilder construirRespuestaEnString() {
		StringBuilder sb = new StringBuilder();
		int num = 1;

		for (Solucion sol : soluciones) {
			sb.append("Solucion " + num).append("\n");
			sb.append("Total de vértices en la solución: ");
			sb.append(sol.cardinal()).append("\n");
			sb.append("Vértices seleccionados:\n");
			
			sb.append("{");
			
			for (Vertice v : sol.getVertices()) {
				sb.append(v.getNombre()).append(", ");
			}

			sb.deleteCharAt(sb.lastIndexOf(","));
			sb.deleteCharAt(sb.lastIndexOf(" "));
			sb.append("}");
			sb.append("\n\n");

			num++;
		}
		return sb;
	}

	public void mostrarSolucion(int indice) {
		
		Solucion sol = soluciones.get(indice);

		List<String> vertices = instancia.getVertices().stream().map(Vertice::getNombre).toList();

		List<Par<String, String>> aristas = instancia.getAristasComoNombres();

		Set<String> solucionDominante = sol.getVertices().stream().map(Vertice::getNombre).collect(Collectors.toSet());

		_rVista.mostrarGrafo(vertices, aristas, solucionDominante);	
	}

	public void manejarClickVolverAlMenu() {
		gestorInterfaz.crearPantallaInicio();
	}

	public void manejarClickBtnSalir() {
		System.exit(0);
	}

	public void manejarClickModificarDatos() {
		gestorInterfaz.mostrarPantallaCargaManual();
	}
}