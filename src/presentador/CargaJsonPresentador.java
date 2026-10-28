package presentador;

import coordinadorMVP.CoordinadorPresentadores;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import datos.JsonLoader;
import datos.JsonLoader.AristaJson;
import datos.JsonLoader.GrafoWrapper;

import modelo.Instancia;
import vista.interfaz.CargaJsonVista;

public class CargaJsonPresentador {
	private CargaJsonVista _vista;
	private CoordinadorPresentadores coordinador;
	private Instancia instancia;
	private GrafoWrapper datosGrafo;

	public CargaJsonPresentador(CargaJsonVista vista, Instancia instancia, CoordinadorPresentadores coordinador) {
		this._vista = vista;
		this.instancia = instancia;
		this.coordinador = coordinador;

		iniciarListeners();
	}

	private void iniciarListeners() {
		listenerSeleccionarArchivo();
		listenerVolverMenu();
		listenerVerResultado();
	}

	private void listenerSeleccionarArchivo() {
		_vista.listenerSeleccionarArchivo(e -> abrirExploradorArchivos());
	}

	private void listenerVolverMenu() {
		_vista.listenerVolverAlMenu(e -> coordinador.iniciarAplicacion());
	}

	private void listenerVerResultado() {
		_vista.listenerVerResultados(e -> coordinador.irAResultado());
	}

	private void abrirExploradorArchivos() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Seleccionar grafo en formato JSON");

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos JSON (*.json)", "json");
		fileChooser.setFileFilter(filtro);
		fileChooser.setAcceptAllFileFilterUsed(false);

		int seleccion = fileChooser.showOpenDialog(null);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = fileChooser.getSelectedFile();
			_vista.mostrarRutaArchivo(archivoSeleccionado.getName());

			try {
				this.datosGrafo = JsonLoader.cargarGrafo(archivoSeleccionado.getAbsolutePath());

				cargarVertices();
				cargarAristas();

				_vista.habilitarBotonResultado(true);

			} catch (Exception e) {
				_vista.mostrarMensajeError("Error al cargar la estructura del grafo.");
				_vista.habilitarBotonResultado(false);
				_vista.mostrarRutaArchivo("Ningún archivo seleccionado");
			}
		}
	}

	private void cargarAristas() {
		if (datosGrafo.getAristas() != null) {
			for (AristaJson arista : datosGrafo.getAristas()) {
				instancia.agregarArista(arista.getOrigen().trim(), arista.getDestino().trim());
			}
		}
	}

	private void cargarVertices() {
		if (datosGrafo.getVertices() != null) {
			for (String v : datosGrafo.getVertices()) {
				instancia.agregarVertice(v.trim());
			}
		}
	}
}
