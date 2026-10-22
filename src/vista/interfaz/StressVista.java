package vista.interfaz;

public interface StressVista {

	void borrarResultadoAnterior();

	void habilitarBotonesTestStress(boolean b);

	void mostrarResultado(String texto);

	void actualizarProgreso(int porcentaje);

	void mostrarMensajeError(String mensaje);
}
