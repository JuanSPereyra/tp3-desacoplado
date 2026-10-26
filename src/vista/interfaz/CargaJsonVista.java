package vista.interfaz;

import java.awt.event.ActionListener;

public interface CargaJsonVista {

	void alSolicitarSeleccionarArchivo(ActionListener listener);

	void alSolicitarVolverAlMenu(ActionListener listener);

	void alSolicitarVerResultados(ActionListener listener);

	void mostrarRutaArchivo(String ruta);

	void habilitarBotonResultado(boolean habilitar);

	void mostrarMensajeError(String mensaje);
}
