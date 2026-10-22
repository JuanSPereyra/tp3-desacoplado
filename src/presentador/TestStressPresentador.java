package presentador;

import swingWorker.TestStressWorker;
import vista.InterfazGestorPantalla;
import vista.PantallaCargarTestStress;

public class TestStressPresentador {
	private PantallaCargarTestStress pantallaCargarTestStress;
	private InterfazGestorPantalla gestorInterfaz;
	private TestStressWorker swingworker;

	public TestStressPresentador(PantallaCargarTestStress pantallaCargarTestStress, InterfazGestorPantalla gestorInterfaz) {
		
		this.pantallaCargarTestStress = pantallaCargarTestStress;
		this.gestorInterfaz = gestorInterfaz;
		
	}

	public void manejarClickVolverAlMenu() {
		manejarClickBtnDetenerTest();
		gestorInterfaz.crearPantallaInicio();
	}

	public void manejarClickBtnSalir() {
		System.exit(0);
	}

	public void manejarClickBtnEmpezarTest(int indice) {
		if (swingworker != null && !swingworker.isDone()) {
			return;
		}

		pantallaCargarTestStress.borrarResultadoAnterior();
		pantallaCargarTestStress.habilitarBotonesTestStress(true);
		
		swingworker = new TestStressWorker(pantallaCargarTestStress, indice);
		swingworker.execute();
	}

	public void manejarClickBtnDetenerTest() {
		if (swingworker != null && !swingworker.isDone()) {
			swingworker.detener();
		}
	}
}
