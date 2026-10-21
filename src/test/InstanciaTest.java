package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import modelo.Instancia;
import modelo.Vertice;

public class InstanciaTest {

    private Instancia instancia;   // A-B, B-C, C-D, C-E, E-F y H aislado
    private Instancia vacia;
    private Vertice a, b, c, d, e, f, h;

    @Before
    public void setUp() {
        vacia = new Instancia();
        instancia = new Instancia();

        for (String n : new String[] {"A", "B", "C", "D", "E", "F", "H"})
            instancia.agregarVertice(n);

        instancia.agregarArista("A", "B");
        instancia.agregarArista("B", "C");
        instancia.agregarArista("C", "D");
        instancia.agregarArista("C", "E");
        instancia.agregarArista("E", "F");

        a = new Vertice("A");
        b = new Vertice("B");
        c = new Vertice("C");
        d = new Vertice("D");
        e = new Vertice("E");
        f = new Vertice("F");
        h = new Vertice("H");
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConVerticeInexistenteLanzaExcepcionTest() {
        vacia.agregarVertice("A");
        vacia.agregarArista("A", "Z");
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaDuplicadaLanzaExcepcionTest() {
        instancia.agregarArista("A", "B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaDuplicadaInvertidaLanzaExcepcionTest() {
        instancia.agregarArista("B", "A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaHaciaSiMismoLanzaExcepcionTest() {
        instancia.agregarArista("A", "A");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void vecinosDeVerticeInexistenteLanzaExcepcionTest() {
        instancia.vecinosDe(new Vertice("Z"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void gradoDeVerticeInexistenteLanzaExcepcionTest() {
        instancia.grado(new Vertice("Z"));
    }

    @Test
    public void instanciaNuevaNoTieneVerticesTest() {
        assertEquals(0, vacia.getTamanio());
    }
    
    @Test public void instaciaNuevaEstaVaciaTest() {
    	assertTrue(vacia.getVertices().isEmpty());
    }

    @Test
    public void agregarVerticeAumentaElTamanoTest() {
        vacia.agregarVertice("A");
        assertEquals(1, vacia.getTamanio());
    }
    
    public void agregarVerticeVaciaLoContieneTest() {
        vacia.agregarVertice("A");
        assertTrue(vacia.getVertices().contains(a));
    }

    @Test (expected = IllegalArgumentException.class)
    public void agregarVerticeRepetidoNoAumentaElTamanoTest() {
        vacia.agregarVertice("A");
        vacia.agregarVertice("A");
    }

    @Test
    public void getTamanoCuentaTodosLosVerticesTest() {
        assertEquals(7, instancia.getTamanio());
    }

    @Test
    public void getVerticesDevuelveTodosLosVerticesTest() {
        Set<Vertice> esperados = new HashSet<>(Arrays.asList(a, b, c, d, e, f, h));
        assertEquals(esperados, instancia.getVertices());
    }

    @Test
    public void modificarVerticesObtenidosNoAfectaALaInstanciaTest() {
        instancia.getVertices().clear();
        assertEquals(7, instancia.getTamanio());
    }

    @Test
    public void vecinosDeUnVerticeTest() {
        Set<Vertice> esperados = new HashSet<>(Arrays.asList(b, d, e));
        assertEquals(esperados, instancia.vecinosDe(c));
    }

    @Test
    public void vecinosSonSimetricosTest() {
        assertTrue(instancia.vecinosDe(a).contains(b));
        assertTrue(instancia.vecinosDe(b).contains(a));
    }

    @Test
    public void vecinosDeVerticeAisladoEsVacioTest() {
        assertTrue(instancia.vecinosDe(h).isEmpty());
    } 

    @Test
    public void gradoDeCadaVerticeATest() {
        assertEquals(1, instancia.grado(a));
    }
    @Test
    
    public void gradoDeCadaVerticeBTest() {
    	assertEquals(2, instancia.grado(b));
    }
    
    @Test
    public void gradoDeCadaVerticeCTest() {
        assertEquals(3, instancia.grado(c));
    }
    @Test
    public void gradoDeCadaVerticeDTest() {
    	assertEquals(1, instancia.grado(d));
    }
    @Test
    public void gradoDeCadaVerticeETest() {
        assertEquals(2, instancia.grado(e));
    }
    @Test
    public void gradoDeCadaVerticeFTest() {
        assertEquals(1, instancia.grado(f));
    }
    @Test
    public void gradoDeCadaVerticeHTest() {
        assertEquals(0, instancia.grado(h));
    }

    @Test
    public void modificarVecinosObtenidosNoAfectaALaInstanciaTest() {
        instancia.vecinosDe(c).clear();
        assertEquals(3, instancia.grado(c));
    }
    
    @Test
    public void cantidadVerticesDeInstanciaVaciaEsCeroTest() {
        assertEquals(0, vacia.cantidadVertices());
    }

    @Test
    public void cantidadVerticesCuentaTodosLosVerticesTest() {
        assertEquals(7, instancia.cantidadVertices());
    }

    @Test
    public void cantidadVerticesAumentaAlAgregarUnoTest() {
        vacia.agregarVertice("A");
        assertEquals(1, vacia.cantidadVertices());
    }

    @Test (expected = IllegalArgumentException.class)
    public void cantidadVerticesNoCambiaAlAgregarRepetidoTest() {
        vacia.agregarVertice("A");
        vacia.agregarVertice("A");
    }

    @Test
    public void cantidadVerticesNoCambiaAlAgregarAristasTest() {
        assertEquals(2, grafoParaCantidad().cantidadVertices());
    }
    
    private Instancia grafoParaCantidad() {
    	vacia.agregarVertice("A");
        vacia.agregarVertice("B");
    	vacia.agregarArista("A", "B");
		return vacia;
    }
}