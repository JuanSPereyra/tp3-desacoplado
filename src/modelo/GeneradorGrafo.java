package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorGrafo {
	private int cantidadVertices;
	private int cantidadAristas;
	long semilla;
	private Grafo grafoARetornar;
	private List<Vertice> listaDeVertices;
	
	public Grafo grafoRandom(int cantidadVertices, int cantidadAristas, long semilla) {	
		return random(cantidadVertices, cantidadAristas, semilla);
	}
	
	private Grafo random (int cantidadVertices, int cantidadAristas, long semilla) {
		
		this.cantidadVertices = cantidadVertices;
		this.cantidadAristas = cantidadAristas;
		this.semilla = semilla;
		
		this.grafoARetornar = new Grafo();
		this.listaDeVertices = new ArrayList<Vertice>();
				
		validarCantVertices(cantidadVertices);
		validadCantAristas(cantidadAristas);	
		validarMaximoAristas(cantidadVertices, cantidadAristas);

		agregarVertices();
		agregarAristas();
		
		return grafoARetornar;
	}

	private void validarCantVertices(int cantidadVertices) {
		if (cantidadVertices < 0 ) throw new IllegalArgumentException("Vertice no puede ser menor a 0");
	}
	
	private void validadCantAristas(int cantidadVertices) {
		if (cantidadVertices < 0) throw new IllegalArgumentException("Arista no puede ser menor a 0");
	}
	
	private void validarMaximoAristas(int cantidadVertices, int cantidadAristas) {		
		int cantMaxAristas = cantidadVertices * (cantidadVertices - 1) / 2;
		
		if (cantidadAristas > cantMaxAristas) throw new IllegalArgumentException("Cantidad de Aristas supera el maximo" + cantMaxAristas);
	}
	
	private void agregarAristas() {
		
		Random rnd = new Random(semilla);
		
		int agregados = 0;
		int n = cantidadVertices;
		int m = cantidadAristas;
		
		while (agregados < m) {
			Vertice a = listaDeVertices.get(rnd.nextInt(n));
			Vertice b = listaDeVertices.get(rnd.nextInt(n));
			
			if(!a.equals(b) && !grafoARetornar.existeArista(a,b)) {
				grafoARetornar.agregarArista(a, b);
				agregados++;
			}
		}
	}
	
	private void agregarVertices() {		
		int n = cantidadVertices;
		
		for (int i=0; i<n; i++) {
			Vertice v = new  Vertice (String.valueOf(i));
			grafoARetornar.agregarVertice(v);
			listaDeVertices.add(v);
		}
	}
}
