package swingWorker;


import java.util.List;
import java.util.Set;
import javax.swing.SwingWorker;
import modelo.BackTracking;
import modelo.GeneradorGrafo;
import modelo.Grafo;
import modelo.Vertice;
import vista.PantallaCargarTestStress;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestStressWorker extends SwingWorker<String, String> {

	private PantallaCargarTestStress pantalla;
	private int densidad;
	private volatile BackTracking backTrackingActual;
	private int recibidos = 0;
	private final int solucionesMaximas=70;

	public TestStressWorker(PantallaCargarTestStress pantalla, int densidad) {
		this.pantalla = pantalla;
		this.densidad = densidad;
	}
	
	public void detener() {
		cancel(true);
		BackTracking bt = backTrackingActual;
		if (bt != null) {
			bt.cancelar();
		}
	}
	
	@Override
	protected String doInBackground() {
		GeneradorGrafo generador = new GeneradorGrafo();
		double total = 0;

		for (int i = 1; i <= solucionesMaximas && !isCancelled(); i++) {

			long inicio = System.currentTimeMillis();

			int cantidadAristas = calcularCantidad(i, densidad);
			Set<Vertice> resultado = resolverGrafo(generador, i, cantidadAristas);

			if (!isCancelled()) {
				double tiempo = (System.currentTimeMillis() - inicio) / 1000.0;
				total += tiempo;

				publish(armarTexto(i, cantidadAristas, resultado, tiempo));
				pausar(250);
			}
		}
		BigDecimal redondeado = new BigDecimal(total).setScale(3, RoundingMode.HALF_UP);
		return "TestStress Finalizado." + "\n" + "Tiempo total de la ejecucion: " + redondeado + " segundos";
	}
	
	private static int calcularCantidad(int numVertice, int densidad) {
		return (numVertice * (numVertice - 1) / 2) * densidad / 10;
	}

	private Set<Vertice> resolverGrafo(GeneradorGrafo generador, int vertices, int aristas) {
		Grafo g = generador.grafoRandom(vertices, aristas, 0);

		backTrackingActual = new BackTracking();
		return backTrackingActual.resolverGrafo(g);
	}
	
	private String armarTexto(int solucion, int aristas, Set<Vertice> resultado, double tiempo) {
		//se que 4 parametros son muchos, pero es para que este mas lindo el codigo principal
		StringBuilder sb = new StringBuilder();
		sb.append("Solucion: " + solucion + "\n");
		sb.append("Cantidad de Vertices:" + solucion + "\n");
		sb.append("Cantidad de Aristas:" + aristas + "\n");
		sb.append(conjuntoEntreLLaves(resultado) + "\n");
		sb.append("Tiempo utilizado: " + tiempo + "\n");
		return sb.toString();
	}
	
	private String conjuntoEntreLLaves(Set<Vertice> conjunto) {
		StringBuilder sb = new StringBuilder("{");
		boolean primero = true;
		for (Vertice v : conjunto) {
			if (!primero) {
				sb.append(", ");
			}
			sb.append(v); 
			primero = false;
		}
		sb.append("}");
		return sb.toString();
	}

	private void pausar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {}
	}
	
	@Override
	protected void process(List<String> textos) {
		for (String texto : textos) {
			pantalla.mostrarResultado(texto);
		}
		recibidos += textos.size();
		pantalla.actualizarProgreso(recibidos * 100 / solucionesMaximas);
	}
	
	@Override
	protected void done() {
		if (isCancelled()) {
			pantalla.mostrarResultado("TestStress detenido.");
		} else {
			try {
				pantalla.mostrarResultado(get());
			} catch (Exception e) {
				e.printStackTrace();
				pantalla.mostrarResultado("Error en el TestStress.");
			}
		}
		pantalla.habilitarBotonesTestStress(false);
	}
}