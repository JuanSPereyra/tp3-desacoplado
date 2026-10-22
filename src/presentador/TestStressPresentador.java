package presentador;

import swingWorker.TestStressWorker;
import vista.interfaz.StressVista;
import vista.interfaz.InterfazGestorPantalla;

public class TestStressPresentador implements ITestListener{
	private StressVista _tsVista;
	private InterfazGestorPantalla gestorInterfaz;
	private TestStressWorker swingworker;

	public TestStressPresentador(StressVista testStressVista, InterfazGestorPantalla gestor) {
		
		this._tsVista = testStressVista;
		this.gestorInterfaz = gestor;	
	}

	public void manejarClickBtnEmpezarTest(int indice) {
		if (swingworker != null && !swingworker.isDone()) {
			return;
		}

		_tsVista.borrarResultadoAnterior();
		_tsVista.habilitarBotonesTestStress(true);
		
		swingworker = new TestStressWorker(this, indice);
		swingworker.execute();
	}

	public void manejarClickBtnDetenerTest() {
		if (swingworker != null && !swingworker.isDone()) {
			swingworker.detener();
		}
	}
	
	public void manejarClickVolverAlMenu() {
		manejarClickBtnDetenerTest();
		gestorInterfaz.crearPantallaInicio();
	}

	public void manejarClickBtnSalir() {
		System.exit(0);
	}
	
	@Override
	public void mostrarResultado(String texto) {
		_tsVista.mostrarResultado(texto);
	}

	@Override
	public void actualizarProgreso(int porcentaje) {
		_tsVista.actualizarProgreso(porcentaje);
	}

	@Override
	public void finalizarTest() {
		_tsVista.habilitarBotonesTestStress(false);
	}

	@Override
	public void habilitarBotonesTestStress(boolean b) {
		_tsVista.habilitarBotonesTestStress(b);
	}
}
