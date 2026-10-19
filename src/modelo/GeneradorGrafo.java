package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorGrafo {
	
	public static Grafo random (int n, int m, long semilla) {
		if (n < 0 ) throw new IllegalArgumentException("Vertice no puede ser menor a 0");
		if (m < 0) throw new IllegalArgumentException("Arista no puede ser menor a 0");
		if (m > n * (n - 1) / 2) throw new IllegalArgumentException("Cantidad de Aristas supera el maximo" + n * (n - 1) / 2);
		
		Grafo grf = new Grafo();
		Random rnd = new Random(semilla);
		List<Vertice> vertices = new ArrayList<>();

		agregarVertices(n, grf, vertices);
		agregarAristas(n, m, grf, rnd, vertices);
		return grf;
	}

	public static Grafo random(int n, int m) {
	    return random(n, m, new Random().nextLong());
	}
	
	private static void agregarAristas(int n, int m, Grafo grf, Random rnd, List<Vertice> vertices) {
		int agregados=0;
		while (agregados <m) {
			Vertice a = vertices.get(rnd.nextInt(n));
			Vertice b = vertices.get(rnd.nextInt(n));
			Arista ar = new Arista(a, b);
			if(a != b && !grf.existeArista(ar)) {
				grf.agregarAristaSinPeso(a, b);
				agregados++;
			}
		}
	}
	private static void agregarVertices(int n, Grafo grf, List<Vertice> vertices) {
		for (int i=0; i<n; i++) {
		Vertice v = new  Vertice (String.valueOf(i));
		grf.agregarVertice(v);
		vertices.add(v);
		}
	}
}
