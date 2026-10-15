package presentador;

import vista.InterfazGestorPantalla;
import vista.CargarDatosVista;
import vista.PantallaResultado;

public class CargarDatosPresentador {
	private CargarDatosVista _cdVista;
	private InterfazGestorPantalla gestorInterfaz;
    
	public CargarDatosPresentador(CargarDatosVista cargarDatosVista, InterfazGestorPantalla gestorInterfaz) {
		this._cdVista = cargarDatosVista;
		this.gestorInterfaz = gestorInterfaz;
	}
	
	public void manejarClickAgregarVertice(String nombre) {
		// Solo para que no rompa al hacer clic
		_cdVista.agregarVertice(nombre);
		_cdVista.limpiarCampoVertice();
	}		
		
	public void manejarClickAgregarArista(String origen, String destino, String peso) {
		// Solo para que no rompa al hacer clic
		_cdVista.agregarArista(origen, destino, peso);
		_cdVista.limpiarCampoPeso();
	}

	public void manejarClickEliminarArista(String origen, String destino) {
		_cdVista.eliminarArista(origen, destino);
	}		
	
	public void manejarClickEliminarVertice(String nombre) {
		_cdVista.eliminarVertice(nombre);
	} 	
	
	// metodo que te lleva a la pantalla de resultado 
	public void manejarClickCalcularDominante() {
		PantallaResultado pantallaResultado = gestorInterfaz.crearPantallaResultado();
		ResultadoPresentador resultadoPresentador = new ResultadoPresentador(
				pantallaResultado, gestorInterfaz, null
		);
	}
		
	public void manejarClickVolverAlMenu() {
		gestorInterfaz.crearPantallaInicio();
	}	
	
	public void manejarClickBtnSalir() {
		System.exit(0);
	}
}