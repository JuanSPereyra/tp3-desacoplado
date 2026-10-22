package vista;

import java.util.Set;

import modelo.BackTracking;
import modelo.GeneradorGrafo;
import modelo.Grafo;
import modelo.Vertice;

public class TestStressPresentador {
	private PantallaCargarTestStress pantallaCargarTestStress;
	private InterfazGestorPantalla gestorInterfaz;

	public TestStressPresentador(PantallaCargarTestStress pantallaCargarTestStress, InterfazGestorPantalla gestorInterfaz) {
		
		this.pantallaCargarTestStress = pantallaCargarTestStress;
		this.gestorInterfaz = gestorInterfaz;
	}

	public void manejarClickVolverAlMenu() {
		gestorInterfaz.crearPantallaInicio();
	}

	public void manejarClickBtnSalir() {
		System.exit(0);
	}

	public void manejarClickBtnEmpezarTest(int indice) {

		pantallaCargarTestStress.borrarResultadoAnterior();
		
		GeneradorGrafo generador = new GeneradorGrafo();
		
		for (int i = 2; i <= 15; i++) {
		
			long inicio = System.currentTimeMillis();
			
			int cantidadAristas = calcularCantidad(i, indice);
			Grafo g = generador.grafoRandom(i, cantidadAristas, 0);
			
			Set<Vertice> resultadoIteracion= new BackTracking().resolverGrafo(g);		

			long fin = System.currentTimeMillis();
			double tiempo = (fin - inicio) / 1000.0;
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("Solucion: " + i + "\n");
			sb.append("Cantidad de Vertices:" + i + "\n");
			sb.append("Cantidad de Aristas:" + cantidadAristas + "\n");
			
			sb.append(resultadoIteracion.toString() + "\n");
			sb.append("Tiempo utilizado: " + tiempo + "\n");
			
			pantallaCargarTestStress.mostrarResultado(sb.toString());
		}
		
	}

	private static int calcularCantidad(int numVertice, int densidad) {
		
		return (numVertice * (numVertice-1) /2) * densidad /10;
	}

	public void manejarClickBtnDetenerTest() {
		// TODO Auto-generated method stub
		
	}
}
