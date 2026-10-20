package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import presentador.ResultadoPresentador;
import util.Par;
import java.awt.Component;

@SuppressWarnings("serial")
public class PantallaResultado extends JFrame {

	private ResultadoPresentador resultadoPresentador;
	private JPanel panelFondo;
	private JLabel lblTitulo;
	private JTextArea txtResultado;
	private JButton btnVolver, btnSalir;
	private JPanel panelGrafo;
	private JComboBox<Object> comboSoluciones;
	private JButton btnModificarDatos;

	public PantallaResultado(InterfazGestorPantalla gestorInterfaz) {
		configurarPantalla();
		crearLblTitulo();
		crearAreaResultado();
		crearBtnVolver(gestorInterfaz);
		crearBtnSalir();
		crearAreaMostrarGrafo();
		crearComboSoluciones();
		crearBtnModificarDatos();
	}

	private void crearBtnModificarDatos() {
		btnModificarDatos = new JButton("Modificar datos");  
	    btnModificarDatos.setOpaque(true);
	    btnModificarDatos.setForeground(Color.WHITE);
	    btnModificarDatos.setFont(new Font("Segoe UI", Font.BOLD, 15));
	    btnModificarDatos.setFocusPainted(false);
	    btnModificarDatos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnModificarDatos.setBorderPainted(false);
	    btnModificarDatos.setBackground(new Color(255, 128, 64));
	    btnModificarDatos.setBounds(100, 444, 330, 32);
	    panelFondo.add(btnModificarDatos);
	    
	    agregarListenerBtnModificarDatos();
	    
	    panelFondo.add(btnSalir);
	}

	private void agregarListenerBtnModificarDatos() {
		btnModificarDatos.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	resultadoPresentador.manejarClickModificarDatos();
	        }
	    });
	}

	private void crearComboSoluciones() {
        comboSoluciones = new JComboBox<>();
        comboSoluciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboSoluciones.setBackground(new Color(128, 0, 128));
        comboSoluciones.setFont(new Font("Verdana", Font.BOLD, 14));
        comboSoluciones.setSize(330, 32);
        comboSoluciones.setLocation(470, 444);
        comboSoluciones.setForeground(new Color(240, 248, 255));
        comboSoluciones.addActionListener(e -> {
            int indice = comboSoluciones.getSelectedIndex();
            resultadoPresentador.mostrarSolucion(indice);
        });
        getContentPane().add(comboSoluciones, BorderLayout.SOUTH);
	}

	private void crearAreaMostrarGrafo() {
		panelGrafo = new JPanel();
	    panelGrafo.setBounds(460, 90, 370, 343);
	    panelGrafo.setBackground(new Color(15, 23, 42));
	    panelGrafo.setForeground(new Color(241, 245, 249));
	    panelFondo.add(panelGrafo);
		
	}

	
	public void mostrarGrafo(List<String> vertices, List<Par<String, String>> aristas, Set<String> solucionDominante) {
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		Map<String, Object> vertexMap = new HashMap<>();

		graph.getModel().beginUpdate();
		
		try {

		for (String v : vertices) {
			String estilo = "shape=ellipse;"
					+ "strokeColor=purple;"
					+ "fillColor=yellow;"
					+ "gradientColor=blue;"
					+ "gradientDirection=east";
			if (solucionDominante.contains(v)) {
				estilo = "shape=doubleEllipse;"
						+ "strokeColor=white;"
						+ "fillColor=white;"
						+ "gradientColor=red;"
						+ "gradientDirection=north";
			}
			Object nodo = graph.insertVertex(parent, null, v, 0, 0, 50, 50, estilo);
			vertexMap.put(v, nodo);
		}


		for (Par<String, String> arista : aristas) {
			String estilo = "strokeColor=blue;"
					+ "endArrow=none";
			graph.insertEdge(parent, null, "", vertexMap.get(arista.getIzquierda()), vertexMap.get(arista.getDerecha()), estilo);
		}
			} finally {
			graph.getModel().endUpdate();
		}
	
		graph.setCellsMovable(true);
		
		graph.setCellsEditable(false);
		graph.setCellsResizable(false);
		graph.setCellsDisconnectable(false);
		graph.setConnectableEdges(false);
		graph.setAllowDanglingEdges(false);
		graph.setCellsBendable(false);
		graph.setDropEnabled(false);
		
		mxCircleLayout layout = new mxCircleLayout(graph);
		layout.execute(graph.getDefaultParent());

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setCenterZoom(true);
		graphComponent.getGraphHandler().setCloneEnabled(false);
		graphComponent.getGraphHandler().setImagePreview(false);
		graphComponent.setConnectable(false);
		
		graphComponent.getViewport().setBackground(Color.black);
		
		graphComponent.addMouseWheelListener(e -> {
			if (e.getWheelRotation() < 0) {
		    	graphComponent.zoomIn();
		    } else {
		    	graphComponent.zoomOut();
		    }
		});
		
		panelGrafo.removeAll();
		panelGrafo.setLayout(new BorderLayout());
		panelGrafo.add(graphComponent, BorderLayout.CENTER);
		panelGrafo.revalidate();
		panelGrafo.repaint();
	}

	public void setPresentador(ResultadoPresentador resultadoPresentador) {
		this.resultadoPresentador = resultadoPresentador;
	}

	private void configurarPantalla() {
		setTitle("Conjunto Dominante Mínimo — Resultado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		panelFondo = new JPanel();
		panelFondo.setBackground(new Color(30, 41, 59));
		setContentPane(panelFondo);
		panelFondo.setLayout(null);
	}

	private void crearLblTitulo() {
		lblTitulo = new JLabel("RESULTADO DEL CONJUNTO DOMINANTE", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(248, 250, 252));
		lblTitulo.setBounds(100, 30, 700, 40);
		panelFondo.add(lblTitulo);
	}

	private void crearAreaResultado() {
		txtResultado = new JTextArea();
		txtResultado.setFont(new Font("Consolas", Font.PLAIN, 15)); 
		txtResultado.setBackground(new Color(15, 23, 42));
		txtResultado.setForeground(new Color(241, 245, 249));
		txtResultado.setEditable(false);
		txtResultado.setMargin(new Insets(15, 15, 15, 15));

		JScrollPane scrollPane = new JScrollPane(txtResultado);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
		scrollPane.setBounds(80, 90, 370, 343);
		panelFondo.add(scrollPane);
	}

	private void crearBtnVolver(InterfazGestorPantalla gestorInterfaz) {
		btnVolver = new JButton("Volver al Menú");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(37, 99, 235));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(150, 487, 280, 45);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (resultadoPresentador != null) {
					resultadoPresentador.manejarClickVolverAlMenu();
				} else {
					gestorInterfaz.crearPantallaInicio();
				}
			}
		});
		panelFondo.add(btnVolver);
	}

	private void crearBtnSalir() {
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(239, 68, 68));
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSalir.setBounds(470, 487, 280, 45);

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (resultadoPresentador != null) {
					resultadoPresentador.manejarClickBtnSalir();
				} else {
					System.exit(0);
				}
			}
		});
		panelFondo.add(btnSalir);
	}

	public void mostrarResultado(String texto) {
		txtResultado.setText(texto);
		txtResultado.setCaretPosition(0); 
	}
	
	public void completarComboSoluciones (int cantidadSoluciones) {
		comboSoluciones.removeAllItems();

		for (int i = 0; i < cantidadSoluciones; i++) {
			comboSoluciones.addItem("Ver solución " + (i+1));
        }
	}
}