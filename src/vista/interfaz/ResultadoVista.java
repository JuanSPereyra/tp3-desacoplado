package vista.interfaz;

import java.util.*;
import estructuraDatosAux.Par;
import estructuraDatosAux.VerticeVisual;

public interface ResultadoVista {

	void mostrarResultado(String string);

	void completarComboSoluciones(List<String> nombreHeuristica);

	void mostrarGrafo(List<VerticeVisual> vertices, List<Par<String, String>> aristas);
	
	void mostrarMensajeError(String mensaje);


}
