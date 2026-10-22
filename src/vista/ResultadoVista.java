package vista;

import java.util.List;
import java.util.Set;

import util.Par;

public interface ResultadoVista {

	void mostrarResultado(String string);

	void completarComboSoluciones(int size);

	void mostrarGrafo(List<String> vertices, List<Par<String, String>> aristas, Set<String> solucionDominante);
	
	void mostrarMensajeError(String mensaje);
}
