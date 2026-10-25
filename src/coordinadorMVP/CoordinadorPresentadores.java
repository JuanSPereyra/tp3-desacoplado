package coordinadorMVP;

import vista.interfaz.InterfazGestorPantalla;
import vista.pantalla.*;
import presentador.*;
import modelo.Instancia;

public class CoordinadorPresentadores {
	private InterfazGestorPantalla gestorInterfaz; 
	private Instancia instancia;

	private PantallaInicio pantallaInicio;
	private PantallaCargarDatosManual pantallaCargaManual;
	private PantallaResultado pantallaResultado;
	private PantallaTestStress pantallaTestStress;

	public CoordinadorPresentadores() {
		this.gestorInterfaz = new GestorPantallas();
	}

	public void iniciarAplicacion() {
		
		pantallaInicio = new PantallaInicio();		
		new BienvenidaPresentador(pantallaInicio, this);
		
		gestorInterfaz.mostrarPantalla(pantallaInicio); 
	}

	public void irACargaManual() {
		
		this.instancia = new Instancia();
		
		pantallaCargaManual = new PantallaCargarDatosManual();
		new CargarDatosPresentador(pantallaCargaManual, instancia, this);

		gestorInterfaz.mostrarPantalla(pantallaCargaManual); 
	}
	
	public void irAResultado() {

		pantallaResultado = new PantallaResultado();
		new ResultadoPresentador(pantallaResultado, instancia, this);

		gestorInterfaz.mostrarPantalla(pantallaResultado); 
		
	}
	
	public Object irACargaDesdeJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	public void irATestStress() {
		
		pantallaTestStress = new PantallaTestStress();		
		new TestStressPresentador(pantallaTestStress, this);
		
		gestorInterfaz.mostrarPantalla(pantallaTestStress);
	}

	public void mostrarPantallaCargaManual() {
		gestorInterfaz.mostrarPantalla(pantallaCargaManual);	
	}


}
