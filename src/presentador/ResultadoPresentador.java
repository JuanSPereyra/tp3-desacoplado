package presentador;

import modelo.Grafo;
import vista.InterfazGestorPantalla;
import vista.PantallaResultado;

public class ResultadoPresentador {

	private PantallaResultado pantallaResultado;
	private InterfazGestorPantalla gestorInterfaz;
	private Grafo grafo;

	public ResultadoPresentador(PantallaResultado pantallaResultado, InterfazGestorPantalla gestorInterfaz, Grafo grafo) {
		this.pantallaResultado = pantallaResultado;
		this.gestorInterfaz = gestorInterfaz;
		this.grafo = grafo;
	}
	/*
	public void calcularSolucionDominante() {
		// Lógica para calcular el Conjunto Dominante Mínimo sobre el grafo
	}*/

	public void manejarClickVolverAlMenu() {
		gestorInterfaz.crearPantallaInicio();
	}

	public void manejarClickBtnSalir() {
		System.exit(0);
	}
}