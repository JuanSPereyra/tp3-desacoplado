package vista;

public interface InterfazGestorPantalla {
	
	void crearPantallaInicio();

	void crearPantallaCargaDatos();

	void crearPantallaCargaDesdeJSON();
	
	PantallaResultado crearPantallaResultado();
	
	void mostrarPantallaCargaManual();
	
	void crearPantallaTestStress();
	
	void mostrarPantallaCargaDesdeJSON();
}