package presentador;

import modelo.Instancia;
import vista.CargarDatosVista;
import vista.InterfazGestorPantalla;
import vista.PantallaResultado;

public class CargarDatosPresentador {
	private CargarDatosVista _cdVista;
	private InterfazGestorPantalla gestorInterfaz;
	private Instancia instancia;
    
	public CargarDatosPresentador(CargarDatosVista cargarDatosVista, InterfazGestorPantalla gestorInterfaz) {
		this._cdVista = cargarDatosVista;
		this.gestorInterfaz = gestorInterfaz;
		this.instancia = new Instancia(); 
	}
	
	public void manejarClickAgregarVertice(String nombre) {
		try {
			if (nombre == null || nombre.trim().isEmpty()) {
				_cdVista.mostrarMensajeError("El nombre del vértice no puede estar vacío.");
				return;
			}
			// agregamos al modelo de negocio
			instancia.agregarVertice(nombre.trim());
			
			// actualizamos la vista 
			_cdVista.agregarVertice(nombre.trim());
			_cdVista.limpiarCampoVertice();
		} catch (Exception e) {
			_cdVista.mostrarMensajeError(e.getMessage());
		}
	}		
		
	public void manejarClickAgregarArista(String origen, String destino) {
		try {
			if (origen == null || destino == null || origen.equals(destino)) {
				_cdVista.mostrarMensajeError("Debe seleccionar origen y destino válidos (y distintos)");
				return;
			}
			// agregamos al modelo de negocio
			instancia.agregarArista(origen, destino);
			
			// actualizamos la vista
			_cdVista.agregarArista(origen, destino, "");
		} catch (Exception e) {
			_cdVista.mostrarMensajeError(e.getMessage());
		}
	}

	public void manejarClickEliminarArista(String origen, String destino) {
		_cdVista.eliminarArista(origen, destino);
	}		
	
	public void manejarClickEliminarVertice(String nombre) {
		_cdVista.eliminarVertice(nombre);
	} 	
	
	public void manejarClickCalcularDominante() {
		try {
	        if (instancia.getVertices().isEmpty()) {
	            _cdVista.mostrarMensajeError("Debe agregar al menos un vértice para calcular el conjunto dominante");
	            return;
	        }

	        PantallaResultado pantallaResultado = gestorInterfaz.crearPantallaResultado();
	        
	        // le pasamos la instancia cargada al ResultadoPresentador
	        ResultadoPresentador resultadoPresentador = new ResultadoPresentador(
	                pantallaResultado, gestorInterfaz, instancia
	        );
	        
	        // vinculamos el presentador a la pantalla y ejecutamos el cálculo
	        pantallaResultado.setPresentador(resultadoPresentador);
	        resultadoPresentador.calcularSolucionDominante();

	    } catch (Exception e) {
	        _cdVista.mostrarMensajeError(e.getMessage());
	    }
	}
		
	public void manejarClickVolverAlMenu() {
		gestorInterfaz.crearPantallaInicio();
	}	
	
	public void manejarClickBtnSalir() {
		System.exit(0);
	}
}