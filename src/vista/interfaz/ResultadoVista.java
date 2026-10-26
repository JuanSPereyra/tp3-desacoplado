package vista.interfaz;

import java.awt.event.ActionListener;
import java.util.*;
import estructuraDatosAux.Par;
import estructuraDatosAux.VerticeVisual;

public interface ResultadoVista {

	void alSolicitarMostrarSolucion(ActionListener listener);

	void alSolicitarVolverAlMenu(ActionListener listener);

	void alSolicitarSalir(ActionListener listener);

	void alSolicitarModificarDatos(ActionListener listener);

	int solucionElegida();

	void mostrarResultado(String string);

	void completarComboSoluciones(List<String> nombreHeuristica);

	void mostrarGrafo(List<VerticeVisual> vertices, List<Par<String, String>> aristas);

	void mostrarMensajeError(String mensaje);
}
