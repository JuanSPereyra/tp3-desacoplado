package principal;

import modelo.Instancia;
import modelo.Solucion;
import modelo.SolverGoloso;

public class Main {

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
	}
}
