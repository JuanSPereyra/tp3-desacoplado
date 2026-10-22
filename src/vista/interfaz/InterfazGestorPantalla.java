package vista.interfaz;

import vista.pantalla.PantallaResultado;

public interface InterfazGestorPantalla {
	
	void crearPantallaInicio();

	void crearPantallaCargaDatos();

	void crearPantallaCargaDesdeJSON();
	
	void crearPantallaTestStress();
	
	PantallaResultado crearPantallaResultado();
	
	void mostrarPantallaCargaManual();
	
	void mostrarPantallaCargaDesdeJSON();
}