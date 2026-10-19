package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Arista;
import modelo.GeneradorGrafo;
import modelo.Grafo;


public class GeneradorGrafoTest {

	@Test
	public void generarCantidadDeVertices() {
		Grafo g = GeneradorGrafo.random(5,0);
		assertEquals(5, g.cantidadVertices());
	}

	@Test
	public void generarCantidadDeAristas() {
		Grafo g = GeneradorGrafo.random(5,4);
		assertEquals(4, g.cantidadAristas());
	}
	
	@Test
	public void puedeGenerarGrafoCompleto() {
	    int n = 8;
	    int maximo = n * (n - 1) / 2;
	    Grafo g = GeneradorGrafo.random(n, maximo);

	    assertEquals(maximo, g.cantidadAristas());
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            if (i != j) {
	            	Arista ar = new Arista(g.getVertice(String.valueOf(i)), g.getVertice(String.valueOf(j)));
	                assertTrue(g.existeArista(ar));
	            }
	        }
	    }
	}
	
	@Test(timeout = 2000, expected = IllegalArgumentException.class)
	public void rechazaMasAristasQueLasPosibles() {
	    GeneradorGrafo.random(4, 7);
	}
	
	@Test
	public void grafoVacio() {
	    Grafo g = GeneradorGrafo.random(0, 0);
	    assertEquals(0, g.cantidadAristas());
	}
	
	@Test
	public void unSoloVerticeSinAristas() {
	    Grafo g = GeneradorGrafo.random(1, 0);
	    assertEquals(0, g.cantidadAristas());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void rechazaVerticeNegativo() {
		GeneradorGrafo.random(-1, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void rechazaAristaNegativa() {
		GeneradorGrafo.random(4, -1);
	}
	
	@Test
	public void mismaSemillaGeneraMismoGrafo() {
	    int n = 8;
	    Grafo g1 = GeneradorGrafo.random(n, 10, 42L);
	    Grafo g2 = GeneradorGrafo.random(n, 10, 42L);

	    for (int i = 0; i < n; i++) {
	        for (int j = i + 1; j < n; j++) {
	            Arista a1 = new Arista(g1.getVertice(String.valueOf(i)), g1.getVertice(String.valueOf(j)));
	            Arista a2 = new Arista(g2.getVertice(String.valueOf(i)), g2.getVertice(String.valueOf(j)));
	            assertEquals(g1.existeArista(a1), g2.existeArista(a2));
	        }
	    }
	}
	
}

	