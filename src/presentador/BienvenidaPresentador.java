package presentador;
 
import vista.interfaz.InterfazGestorPantalla;
 
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
	
	public void manejarClickBotonTestStress() {
		gestorInterfaz.crearPantallaTestStress();
	}
	
	public void manejarClickBotonSalir() {
	    System.exit(0);
	}
}