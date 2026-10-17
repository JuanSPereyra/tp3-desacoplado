package modelo;
import java.util.Set;

public class Instancia {
	private final Grafo grafo;

    public Instancia() {
    	grafo = new Grafo();
    }

    public Set<Vertice> getVertices(){ 
    	return grafo.obtenerVertices(); 
    }
    
    public Set<Vertice> vecinosDe(Vertice v){ 
    	return grafo.obtenerVecinos(v);
    }
    
    public int grado(Vertice v){ 
    	return grafo.grado(v); 
    }
    
    public int cantidadVertices(){
    	return grafo.cantidadVertices(); 
    }
    
    public int getTamanio(){ 
    	return grafo.cantidadVertices(); 
    }
    public void agregarVertice(String nombre) {
        grafo.agregarVertice(new Vertice(nombre));
    }

    public void agregarArista(String a, String b) {
        grafo.agregarArista(grafo.getVertice(a), grafo.getVertice(b));
    }
}
