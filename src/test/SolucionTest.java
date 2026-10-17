package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import modelo.Instancia;
import modelo.Solucion;
import modelo.Vertice;

public class SolucionTest {

    private Instancia instancia;   // A-B, B-C, C-D, C-E, E-F y H aislado
    private Solucion solucion;
    private Vertice a, b, c, d, e, f, h;

    @Before
    public void setUp() {
        instancia = new Instancia();
        for (String n : new String[] {"A", "B", "C", "D", "E", "F", "H"})
            instancia.agregarVertice(n);

        instancia.agregarArista("A", "B");
        instancia.agregarArista("B", "C");
        instancia.agregarArista("C", "D");
        instancia.agregarArista("C", "E");
        instancia.agregarArista("E", "F");

        solucion = new Solucion(instancia);

        a = new Vertice("A");
        b = new Vertice("B");
        c = new Vertice("C");
        d = new Vertice("D");
        e = new Vertice("E");
        f = new Vertice("F");
        h = new Vertice("H");
    }

    @Test
    public void solucionNuevaEstaVaciaTest() {
        assertEquals(0, solucion.cardinal());
        assertTrue(solucion.getVertices().isEmpty());
    }

    @Test
    public void solucionNuevaNoTieneDominadosTest() {
        assertFalse(solucion.estaDominado(a));     
    }

    @Test
    public void solucionNuevaNoDominaTodoTest() {
        assertFalse(solucion.dominioIgualGrafo());
    }

    @Test
    public void solucionDeInstanciaVaciaDominaTodoTest() {
        assertTrue(new Solucion(new Instancia()).dominioIgualGrafo());
    }

    @Test
    public void agregarVerticeLoDejaEnLaSolucionTest() {
        solucion.agregar(c);
        assertTrue(solucion.contiene(c));    
    }
    
    @Test
    public void agregarVerticeLoAgregoASolucionTest() {
    	solucion.agregar(c);
    	assertEquals(1, solucion.cardinal());
    }

    @Test
    public void agregarVerticeNoAgregaSusVecinosALaSolucionTest() {
        solucion.agregar(c);
        assertNoContiene(Arrays.asList(b, d, e));
    }

    @Test
    public void agregarVerticeDominaALosVecinosYASiMismoTest() {
        solucion.agregar(c);
        assertDominados(Arrays.asList(c, b, d, e));
    }

    @Test
    public void agregarVerticeNoDominaALosNoVecinosTest() {
        solucion.agregar(c);
        assertNoDominados(Arrays.asList(a, f, h));
    }

    @Test
    public void agregarElMismoVerticeDosVecesNoAumentaElCardinalTest() {
        agregarTodos(Arrays.asList(c, c));
        assertEquals(1, solucion.cardinal());
    }

    @Test
    public void agregarVerticeAisladoSoloSeDominaASiMismoTest() {
        solucion.agregar(h);
        assertDominados(Arrays.asList(h));
        assertNoDominados(Arrays.asList(a));
    }

    @Test
    public void noContieneVerticeNoAgregadoTest() {
        assertNoContiene(Arrays.asList(a));
    }

    @Test
    public void dominadoNoImplicaContenidoTest() {
        solucion.agregar(c);
        assertDominados(Arrays.asList(b));
        assertNoContiene(Arrays.asList(b));
    }

    @Test
    public void nuevosCubiertosAlInicioTest() {
        //                              
        assertNuevosCubiertos(new int[] {2, 3, 4, 2, 3, 2, 1});// A  B  C  D  E  F  H
    }

    @Test
    public void nuevosCubiertosSeActualizaTrasAgregarTest() {
        solucion.agregar(c);
        assertNuevosCubiertos(new int[] {1, 1, 0, 0, 1, 1, 1});
    }

    @Test
    public void dominaTodoEsFalsoSiFaltanVerticesTest() {
        agregarTodos(Arrays.asList(c, a, e));
        assertFalse(solucion.dominioIgualGrafo());
    }

    @Test
    public void dominaTodoEsVerdaderoCuandoSeCubrenTodosTest() {
        agregarTodos(Arrays.asList(c, a, e, h));
        assertTrue(solucion.dominioIgualGrafo());
    }

    @Test
    public void getVerticesDevuelveLosElegidosTest() {
        agregarTodos(Arrays.asList(c, h));
        assertEquals(new HashSet<>(Arrays.asList(c, h)), solucion.getVertices());
    }

    @Test
    public void modificarVerticesObtenidosNoAfectaALaSolucionTest() {
        solucion.agregar(c);
        solucion.getVertices().clear();
        assertEquals(1, solucion.cardinal());
    }

    @Test
    public void toStringIncluyeLosVerticesElegidosTest() {
        solucion.agregar(c);
        assertTrue(solucion.toString().contains("C"));
    }
    
    private void assertNoContiene(List<Vertice> vertices) {
        for (Vertice v : vertices) {
            assertFalse(solucion.contiene(v));
        }
    }

    private void assertDominados(List<Vertice> vertices) {
        for (Vertice v : vertices) {
            assertTrue(solucion.estaDominado(v));
        }
    }

    private void assertNoDominados(List<Vertice> vertices) {
        for (Vertice v : vertices) {
            assertFalse(solucion.estaDominado(v));
        }
    }

    private void agregarTodos(List<Vertice> vertices) {
        for (Vertice v : vertices) {
            solucion.agregar(v);
        }
    }

    // esperados en el orden: a, b, c, d, e, f, h
    private void assertNuevosCubiertos(int[] esperados) {
        List<Vertice> vertices = Arrays.asList(a, b, c, d, e, f, h);
        for (int i = 0; i < vertices.size(); i++) {
            assertEquals(esperados[i], solucion.nuevosCubiertos(vertices.get(i)));
        }
    }
}