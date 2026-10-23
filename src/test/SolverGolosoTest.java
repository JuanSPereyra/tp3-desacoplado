package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import modelo.Instancia;
import modelo.Solucion;
import modelo.SolverGoloso;
import modelo.Vertice;

public class SolverGolosoTest {

    private Instancia instancia;   // A-B, B-C, C-D, C-E, E-F y H aislado
    private SolverGoloso solver;

    @Before
    public void setUp() {
        instancia = new Instancia();
        cargarVertices(instancia, Arrays.asList("A", "B", "C", "D", "E", "F", "H"));
        instancia.agregarArista("A", "B");
        instancia.agregarArista("B", "C");
        instancia.agregarArista("C", "D");
        instancia.agregarArista("C", "E");
        instancia.agregarArista("E", "F");
        solver = new SolverGoloso(instancia);
    }

    //cobertura 

    @Test
    public void coberturaDevuelveConjuntoDominanteTest() {
        Solucion s = solver.resolverPorCobertura();
        assertTrue(esDominante(instancia, s));
    }

    @Test
    public void coberturaIncluyeAlVerticeAisladoTest() {
        assertTrue(solver.resolverPorCobertura().contiene(new Vertice("H")));
    }

    @Test
    public void coberturaDaElConjuntoEsperadoTest() {
        // H (aislado), C (cubre 4), luego A y E por desempate de nombre
        assertEquals(conjunto(Arrays.asList("H", "C", "A", "E")), solver.resolverPorCobertura().getVertices());
    }

    //grado 

    @Test
    public void gradoDevuelveConjuntoDominanteTest() {
        Solucion s = solver.resolverPorGradoMayor();
        assertTrue(esDominante(instancia, s));
    }

    @Test
    public void gradoIncluyeAlVerticeAisladoTest() {
        assertTrue(solver.resolverPorGradoMayor().contiene(new Vertice("H")));
    }

    @Test
    public void gradoDaElConjuntoEsperadoTest() {
        // H, C (grado 3), luego B y E (grado 2, desempate por nombre)
        assertEquals(conjunto(Arrays.asList("H", "C", "B", "E")), solver.resolverPorGradoMayor().getVertices());
    }

    //pesoVertice

    @Test
    public void pesoVerticeDevuelveConjuntoDominanteTest() {
        Solucion s = solver.resolverPorPesoDelVertice();
        assertTrue(esDominante(instancia, s));
    }

    @Test
    public void pesoVerticeIncluyeAlVerticeAisladoTest() {
        assertTrue(solver.resolverPorPesoDelVertice().contiene(new Vertice("H")));
    }


    @Test
    public void resolverDosVecesDaElMismoResultadoTest() {
        Set<Vertice> primera = solver.resolverPorCobertura().getVertices();
        assertEquals(primera, solver.resolverPorCobertura().getVertices());
    }


    @Test
    public void instanciaVaciaDaSolucionVaciaTest() {
        Solucion s = new SolverGoloso(new Instancia()).resolverPorCobertura();
        assertEquals(0, s.cardinal());
    }

    @Test
    public void unSoloVerticeEntraEnLaSolucionTest() {
        Solucion s = new SolverGoloso(instanciaConAislados(Arrays.asList("X"))).resolverPorGradoMayor();
        assertEquals(1, s.cardinal());
    }

    @Test
    public void todosAisladosEntranTodosTest() {
        Instancia aislados = instanciaConAislados(Arrays.asList("X", "Y", "Z"));
        assertEquals(3, new SolverGoloso(aislados).resolverPorPesoDelVertice().cardinal());
    }

    @Test
    public void unaSolaAristaNecesitaUnVerticeTest() {
        Instancia par = instanciaConAislados(Arrays.asList("X", "Y"));
        par.agregarArista("X", "Y");
        assertEquals(1, new SolverGoloso(par).resolverPorCobertura().cardinal());
    }

    @Test
    public void estrellaSeResuelveConElCentroTest() {
        Instancia estrella = estrella();
        assertEquals(conjunto(Arrays.asList("X")), new SolverGoloso(estrella).resolverPorGradoMayor().getVertices());
    }

    @Test
    public void estrellaCubreTodoConLasTresEstrategiasTest() {
        SolverGoloso s = new SolverGoloso(estrella());
        assertEquals(1, s.resolverPorCobertura().cardinal() + s.resolverPorGradoMayor().cardinal() + s.resolverPorPesoDelVertice().cardinal() - 2);
    }

    //grafo enunciado tp = {2, 4}

    @Test
    public void coberturaEncuentraElOptimoDelEnunciadoTest() {
        Instancia enunciado = enunciadoDelTp();
        assertEquals(2, new SolverGoloso(enunciado).resolverPorCobertura().cardinal());
    }

    @Test
    public void gradoEncuentraElOptimoDelEnunciadoTest() {
        Instancia enunciado = enunciadoDelTp();
        assertEquals(conjunto(Arrays.asList("2", "4")), new SolverGoloso(enunciado).resolverPorGradoMayor().getVertices());
    }

    @Test
    public void pesoVerticeDominaElGrafoDelEnunciadoTest() {
        Instancia enunciado = enunciadoDelTp();
        assertTrue(esDominante(enunciado, new SolverGoloso(enunciado).resolverPorPesoDelVertice()));
    }


    private void cargarVertices(Instancia i, List<String> nombres) {
        for (String n : nombres)
            i.agregarVertice(n);
    }

    private Instancia instanciaConAislados(List<String> nombres) {
        Instancia i = new Instancia();
        cargarVertices(i, nombres);
        return i;
    }

    private Instancia estrella() {
        Instancia i = instanciaConAislados(Arrays.asList("X", "A", "B", "C"));
        for (String hoja : Arrays.asList("A", "B", "C"))
            i.agregarArista("X", hoja);
        return i;
    }

    private Instancia enunciadoDelTp() {
        Instancia i = new Instancia();
        String[] vertices = {"1", "2", "3", "4", "5", "6"};
        String[][] aristas = {{"6", "4"}, {"4", "5"}, {"4", "3"}, {"5", "1"}, {"5", "2"}, {"1", "2"}, {"3", "2"}};

        for (String v : vertices)
            i.agregarVertice(v);
        for (String[] a : aristas)
            i.agregarArista(a[0], a[1]);
        return i;
    }

    private Set<Vertice> conjunto(List<String> nombres) {
        Set<Vertice> ret = new HashSet<>();
        for (String n : nombres)
            ret.add(new Vertice(n));
        return ret;
    }

    // Verificacion independiente del solver, todo vertice esta en la solucion
    private boolean esDominante(Instancia i, Solucion s) {
        for (Vertice v : i.getVertices())
            if (!s.contiene(v) && !tieneVecinoEnSolucion(i, v, s))
                return false;
        return true;
    }

    private boolean tieneVecinoEnSolucion(Instancia i, Vertice v, Solucion s) {
        for (Vertice vecino : i.vecinosDe(v))
            if (s.contiene(vecino))
                return true;
        return false;
    }
}
