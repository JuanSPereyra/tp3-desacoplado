package vista.interfaz;

import java.awt.event.ActionListener;

import estructuraDatosAux.Par;

public interface CargarDatosVista {

	void alSolicitarAgregarVertice(ActionListener listener);

	void alSolicitarAgregarArista(ActionListener listener);

	void alSolicitarEliminarVertice(ActionListener listener);

	void alSolicitarEliminarArista(ActionListener listener);

	void alSolicitarCalcularDominante(ActionListener listener);

	void alSolicitarVolverAlMenu(ActionListener listener);

	String obtenerTextoVertice();

	Par<String, String> obtenerOrigenDestinoDeArista();

	String obtenerVerticeAEliminar();

	Par<String, String> obtenerAristaAEliminar();

	void agregarVertice(String nombre);

	void agregarArista(String origen, String destino, String peso);

	void eliminarVertice(String nombre);

	void eliminarArista(String origen, String destino);

	void limpiarCampoVertice();

	void mostrarMensajeError(String mensaje);
}
