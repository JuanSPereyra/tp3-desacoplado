package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import generadorRandomWrapper.GeneradorPrefijado;
import generadorRandomWrapper.GeneradorRandom;
import modelo.backtracking.GeneradorGrafo;
import modelo.grafo.Grafo;
import modelo.grafo.Vertice;

public class GeneradorGrafoTest {

	GeneradorGrafo generador;

	@Before
	public void inicializar() {
		generador = new GeneradorGrafo();
		GeneradorGrafo.setGenerador(new GeneradorRandom());
	}

	@Test
	public void generarCantidadDeVertices() {
		Grafo g = generador.grafoRandom(5, 0);
		assertEquals(5, g.cantidadVertices());
	}

	@Test
	public void generarCantidadDeAristas() {
		Grafo g = generador.grafoRandom(5, 4);
		assertEquals(4, g.cantidadAristas());
	}
	
	@Test
	public void rechazarMismoVerticeParaArista() {
		GeneradorGrafo.setGenerador(new GeneradorPrefijado(2));
		generador.iniciarGrafoYVertices(3);
		boolean resultadoDeLaInsercion = generador.agregado();
		assertFalse(resultadoDeLaInsercion);	
	}

	@Test
	public void puedeGenerarGrafoCompleto() {
		int n = 8;
		int maximo = n * (n - 1) / 2;

		Grafo g = generador.grafoRandom(n, maximo);

		assertEquals(maximo, g.cantidadAristas());
		for (int i = 0; i < n; i++) {
			Vertice v1 = g.getVertice(String.valueOf(i));
			for (int j = 0; j < n; j++) {
				if (i != j) {
					Vertice v2 = g.getVertice(String.valueOf(j));
					assertTrue(g.existeArista(v1, v2));
				}
			}
		}
	}

	@Test(timeout = 2000, expected = IllegalArgumentException.class)
	public void rechazaMasAristasQueLasPosibles() {
		generador.grafoRandom(4, 7);
	}

	@Test
	public void grafoVacio() {
		Grafo g = generador.grafoRandom(0, 0);
		assertEquals(0, g.cantidadAristas());
	}

	@Test
	public void unSoloVerticeSinAristas() {
		Grafo g = generador.grafoRandom(1, 0);
		assertEquals(0, g.cantidadAristas());
	}

	@Test(expected = IllegalArgumentException.class)
	public void rechazaVerticeNegativo() {
		generador.grafoRandom(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rechazaAristaNegativa() {
		generador.grafoRandom(4, -1);
	}
}
	