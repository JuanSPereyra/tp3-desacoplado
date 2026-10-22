package principal;

import java.util.Set;

import modelo.BackTracking;
import modelo.GeneradorGrafo;
import modelo.Grafo;
import modelo.Instancia;
import modelo.Solucion;
import modelo.SolverGoloso;
import modelo.Vertice;

public class Main {
	
	public static int porcent(int n) {
		return (n * (n-1) /2);
	}

	public static void main(String[] args) {
	    Instancia instancia = new Instancia();
	    for (String n : new String[] {"A", "B", "C", "D", "E", "F", "H"})
	    	
	    instancia.agregarVertice(n);
	    instancia.agregarArista("A", "B");
	    instancia.agregarArista("B", "C");
	    instancia.agregarArista("C", "E");
	    instancia.agregarArista("C", "D");
	    instancia.agregarArista("E", "F");
	    
	    

	    SolverGoloso solver = new SolverGoloso(instancia);

	    Solucion porCobertura= solver.resolverPorCobertura();
	    Solucion porGrado= solver.resolverPorGrado();
	    Solucion porpeso= solver.resolverPorPesoDelVertice();
	    
	    System.out.println("Cobertura: " + porCobertura);
	    System.out.println("Grado: "+ porGrado);
	    System.out.println("Peso: "+ porpeso );
	    
	    
	    Grafo grafo2 = new Grafo();
	    Vertice a= new Vertice("A");
	    Vertice b= new Vertice("B");
	    Vertice c= new Vertice("C");
	    Vertice d= new Vertice("D");
	    Vertice e= new Vertice("E");
	    Vertice f= new Vertice("F");
	    Vertice h=new Vertice("H");

	    grafo2.agregarVertice(a);
	    grafo2.agregarVertice(b);
	    grafo2.agregarVertice(c);
	    grafo2.agregarVertice(d);
	    grafo2.agregarVertice(e);
	    grafo2.agregarVertice(f);
	    grafo2.agregarVertice(h);
	    
	    grafo2.agregarArista(a, b);
	    grafo2.agregarArista(b, c);
	    grafo2.agregarArista(c, e);
	    grafo2.agregarArista(e, f);
	    grafo2.agregarArista(c, d);
	    
//	    Set<Vertice> r = new BackTracking().resolverGrafo(grafo2);
//	    System.out.println(r);
	    
	    Grafo grafo3=new Grafo();
	    Vertice v1=new Vertice("1");
	    Vertice v2=new Vertice("2");
	    Vertice v3=new Vertice("3");
	    Vertice v4=new Vertice("4");
	    Vertice v5=new Vertice("5");
	    Vertice v6=new Vertice("6");
	    grafo3.agregarVertice(v1);
	    grafo3.agregarVertice(v2);
	    grafo3.agregarVertice(v3);
	    grafo3.agregarVertice(v4);
	    grafo3.agregarVertice(v5);
	    grafo3.agregarVertice(v6);
//	    grafo3.agregarArista(v1, v2);
//	    grafo3.agregarArista(v1, v5);
	    grafo3.agregarArista(v5, v2);
	    grafo3.agregarArista(v1, v6);
	    grafo3.agregarArista(v3, v2);
	    grafo3.agregarArista(v3, v4);
	    grafo3.agregarArista(v4, v5);
	    grafo3.agregarArista(v6, v4);
	    
//	    Set<Vertice> ss=new BackTracking().resolverGrafo(grafo3);
//	    	System.out.println(ss);
	    	
	    	int vertice = 70;
	    	int aristas = porcent(vertice);
	    	
	    GeneradorGrafo generador = new GeneradorGrafo();
	    
	    double tiempoTotal = 0.0;
	    
	    for (int i = 2; i < 15; i++) {
	    
	    	long inicio = System.currentTimeMillis();
	    	System.out.println("Solucion cantidad: " + i);
	    	Grafo g = generador.grafoRandom(i, porcent(i)/2, 0);
	    	Set<Vertice> rrr = new BackTracking().resolverGrafo(g);
	    System.out.println(rrr);
	    
	    long fin = System.currentTimeMillis();
		double tiempo = (fin - inicio) / 1000.0;
		tiempoTotal += tiempo;
		System.out.println("n = " + i + ": " + tiempo + " seg.");
		
	    }
	    
	    System.out.println(tiempoTotal/100);
	    
	    //0.0017100000000000006 tiempo promedio 50 .5
	    // se supone
	    //0.006540000000000001 tiempo 50 .3
	    //0.02721 tiempo 50 .4
	    
	    
	    
	    
	    
//[13, 14, 38, 16, 19, 0, 1, 3, 5, 6, 9, 40, 41, 43, 21, 23, 45, 24, 25, 47, 49, 28, 30, 31, 33]
	}
	
	
}
