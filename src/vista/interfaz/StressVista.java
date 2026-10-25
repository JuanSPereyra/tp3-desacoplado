package vista.interfaz;

import java.awt.event.ActionListener;

import estructuraDatosAux.Par;

public interface StressVista {

	void alSolicitarVolverAlMenu(ActionListener listener);

	void alSolicitarSalir(ActionListener listener);

	void alSolicitarEmpezarTest(ActionListener listener);

	void alSolicitarDetenerTest(ActionListener listener);

	Par<String, Integer> datosParaTest();

	void borrarResultadoAnterior();

	void habilitarBotonesTestStress(boolean corriendo);

	void mostrarResultado(String texto);

	void actualizarProgreso(int porcentaje);

	void mostrarMensajeError(String mensaje);
	
}
