package presentador;
 
import vista.InterfazGestorPantalla;
 
public class BienvenidaPresentador {
 
	private InterfazGestorPantalla gestorInterfaz;
 
	public BienvenidaPresentador(InterfazGestorPantalla gestorInterfaz) {
		this.gestorInterfaz = gestorInterfaz;
	}	
	
	public void manejarClickBotonCargaManual() {
		gestorInterfaz.crearPantallaCargaDatos();
	}	
	
	public void manejarClickBotonCargaDocumento() {
		gestorInterfaz.crearPantallaCargaDesdeJSON();
	}	
	
	public void manejarClickBotonSalir() {
	    System.exit(0);
	}
}