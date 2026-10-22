package presentador;

public interface ITestListener {

	void mostrarResultado(String texto);

	void actualizarProgreso(int porcentaje);

	void finalizarTest();
	
	void habilitarBotonesTestStress(boolean b);
}
