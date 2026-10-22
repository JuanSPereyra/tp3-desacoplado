package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import presentador.TestStressPresentador;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class PantallaCargarTestStress extends JFrame {
	
	private TestStressPresentador testStressPresentador;
	private JButton btnDetenerTest, btnSalir;
	private Container panelFondo;
	private JComboBox<Object> comboDensidades;
	private JLabel lblTitulo;
	private JTextArea txtResultado;
	private JButton btnVolver;
	private JButton btnEmpezarTest;
	private JLabel lblElegirDensidad;
	private JProgressBar barraProgreso;

	public PantallaCargarTestStress(InterfazGestorPantalla gestorInterfaz) {

		this.testStressPresentador = new TestStressPresentador(this, gestorInterfaz);

		configurarPantalla();
		crearLblTitulo();
		crearLblElegirDensidad();
		crearAreaResultado();
		crearBarraProgreso();
		crearBtnVolver(gestorInterfaz);
		crearBtnSalir();
		crearBtnEmpezarTest();
		crearComboSoluciones();
		crearBtnDetenerTest();

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
		lblTitulo = new JLabel("TEST DE STRESS", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(248, 250, 252));
		lblTitulo.setBounds(100, 30, 700, 40);
		panelFondo.add(lblTitulo);
	}
	
	private void crearLblElegirDensidad() {
		lblElegirDensidad = new JLabel("Elegir Densidad", SwingConstants.CENTER);
		lblElegirDensidad.setForeground(new Color(248, 250, 252));
		lblElegirDensidad.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblElegirDensidad.setBounds(73, 366, 219, 40);
		panelFondo.add(lblElegirDensidad);
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
		scrollPane.setBounds(78, 69, 742, 274);
		panelFondo.add(scrollPane);
	}
	
	private void crearBarraProgreso() {
		barraProgreso = new JProgressBar(0, 100);
		barraProgreso.setStringPainted(true);
		barraProgreso.setForeground(new Color(0, 128, 128));
		barraProgreso.setBackground(new Color(15, 23, 42));
		barraProgreso.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
		barraProgreso.setBounds(78, 465, 742, 25);
		panelFondo.add(barraProgreso);
	}

	private void crearBtnVolver(InterfazGestorPantalla gestorInterfaz) {
		btnVolver = new JButton("Volver al Menú");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(37, 99, 235));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(150, 508, 280, 45);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (testStressPresentador != null) {
					testStressPresentador.manejarClickVolverAlMenu();
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
		btnSalir.setBounds(470, 508, 280, 45);

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					testStressPresentador.manejarClickBtnSalir();
			}
		});
		panelFondo.add(btnSalir);
	}
	
	private void crearBtnEmpezarTest() {
		btnEmpezarTest = new JButton("Comenzar Test\t");
		btnEmpezarTest.setOpaque(true);
		btnEmpezarTest.setForeground(Color.WHITE);
		btnEmpezarTest.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnEmpezarTest.setFocusPainted(false);
		btnEmpezarTest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEmpezarTest.setBorderPainted(false);
		btnEmpezarTest.setBackground(new Color(0, 128, 128));
		btnEmpezarTest.setBounds(302, 363, 161, 40);

		agregarListenerBtnEmpezarTest();
		
		panelFondo.add(btnEmpezarTest);
	}
	
	private void agregarListenerBtnEmpezarTest() {
		
		btnEmpezarTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indice = comboDensidades.getSelectedIndex();
				testStressPresentador.manejarClickBtnEmpezarTest(indice+1);
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void crearComboSoluciones() {
		comboDensidades = new JComboBox<>();
		comboDensidades.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"}));
		comboDensidades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboDensidades.setBackground(new Color(128, 0, 128));
		comboDensidades.setFont(new Font("Verdana", Font.BOLD, 14));
		comboDensidades.setSize(169, 32);
		comboDensidades.setLocation(100, 416);
		comboDensidades.setForeground(new Color(240, 248, 255));
		
		getContentPane().add(comboDensidades, BorderLayout.SOUTH);
	}
	
	private void crearBtnDetenerTest() {
		btnDetenerTest = new JButton("Detener Test\t");
		btnDetenerTest.setOpaque(true);
		btnDetenerTest.setForeground(Color.WHITE);
		btnDetenerTest.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnDetenerTest.setFocusPainted(false);
		btnDetenerTest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDetenerTest.setBorderPainted(false);
		btnDetenerTest.setBackground(new Color(255, 128, 64));
		btnDetenerTest.setBounds(302, 411, 161, 40);

		agregarListenerBtnDetenerTest();
		btnDetenerTest.setEnabled(false);
		panelFondo.add(btnDetenerTest);
	}
	
	private void agregarListenerBtnDetenerTest() {
		btnDetenerTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testStressPresentador.manejarClickBtnDetenerTest();
			}
		});
	}

	public void mostrarResultado(String texto) {
		txtResultado.append(texto + "\n");
	}
	
	public void borrarResultadoAnterior(){
		txtResultado.setText("");
		barraProgreso.setValue(0);
	}
	
	public void actualizarProgreso(int porcentaje) {
		barraProgreso.setValue(porcentaje);
	}

	public void habilitarBotonesTestStress(boolean corriendo) {
		btnEmpezarTest.setEnabled(!corriendo);
		comboDensidades.setEnabled(!corriendo);
		btnDetenerTest.setEnabled(corriendo);
	}

}
