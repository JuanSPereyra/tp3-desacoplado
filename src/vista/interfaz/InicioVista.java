package vista.interfaz;

import java.awt.event.ActionListener;

public interface InicioVista {
	
	void alSolicitarCargaManual(ActionListener listener);
	
	void alSolicitarCargaDocumento(ActionListener listener);
	
	void alSolicitarTestStress(ActionListener listener);
	
	void alSolicitarSalir(ActionListener listener);
}
