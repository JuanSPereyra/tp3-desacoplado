package vista;

import javax.swing.JFrame;

public class GestorPantallas implements InterfazGestorPantalla {

	private PantallaInicio pantallaInicio;
	private PantallaCargarDatosManual pantallaCargaDatos;
	private PantallaCargarDatosDesdeJSON pantallaCargaDesdeJSON;
	private PantallaResultado pantallaResultado;
	private PantallaCargarTestStress pantallaCargarTestStress;
	
	public void crearPantallaInicio() {
		pantallaInicio = new PantallaInicio(this);
		mostrarPantalla(pantallaInicio);
	}

	public void crearPantallaCargaDatos() {
		pantallaCargaDatos = new PantallaCargarDatosManual(this);
		mostrarPantalla(pantallaCargaDatos);
	}
	
	public void crearPantallaCargaDesdeJSON() {
	    pantallaCargaDesdeJSON = new PantallaCargarDatosDesdeJSON(this);
	    mostrarPantalla(pantallaCargaDesdeJSON);
	}
	
	public void crearPantallaTestStress() {
		pantallaCargarTestStress = new PantallaCargarTestStress(this);
		mostrarPantalla(pantallaCargarTestStress);	
	}
		
	public PantallaResultado crearPantallaResultado() {
		pantallaResultado = new PantallaResultado(this);
	    mostrarPantalla(pantallaResultado);
	    return pantallaResultado;
	}	
	
	public void mostrarPantallaCargaManual() {
		mostrarPantalla(pantallaCargaDatos);
	}
	
	public void mostrarPantallaCargaDesdeJSON() {
	    mostrarPantalla(pantallaCargaDesdeJSON);
	}

	private JFrame mostrarPantalla(JFrame pantalla) {
		pantalla.setVisible(true);
		ocultarPantallas(pantalla);
		return pantalla;
	}
		
	private void ocultarPantallas(JFrame pantallaActual) {
		JFrame[] todasLasPantallas = { pantallaInicio, pantallaCargaDatos, pantallaResultado, pantallaCargaDesdeJSON, pantallaCargarTestStress };

		for (JFrame pantalla : todasLasPantallas) {
			if (pantalla != pantallaActual && pantalla != null) {
				pantalla.setVisible(false);
			}
		}
	}
}