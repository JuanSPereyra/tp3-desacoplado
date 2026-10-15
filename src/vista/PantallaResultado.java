package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import presentador.ResultadoPresentador;

public class PantallaResultado extends JFrame {

	private ResultadoPresentador resultadoPresentador;
	private JPanel panelFondo;
	private JLabel lblTitulo;
	private JTextArea txtResultado;
	private JButton btnVolver, btnSalir;

	public PantallaResultado(InterfazGestorPantalla gestorInterfaz) {
		configurarPantalla();
		crearLblTitulo();
		crearAreaResultado();
		crearBtnVolver(gestorInterfaz);
		crearBtnSalir();
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
		lblTitulo = new JLabel("Resultado del Conjunto Dominante Mínimo", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(100, 40, 700, 40);
		panelFondo.add(lblTitulo);
	}

	private void crearAreaResultado() {
		txtResultado = new JTextArea();
		txtResultado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtResultado.setForeground(new Color(30, 41, 59));
		txtResultado.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(txtResultado);
		scrollPane.setBounds(150, 110, 600, 300);
		panelFondo.add(scrollPane);
	}

	private void crearBtnVolver(InterfazGestorPantalla gestorInterfaz) {
		btnVolver = new JButton("Volver al Menú");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(37, 99, 235));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(150, 440, 280, 45);

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
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(239, 68, 68));
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSalir.setBounds(470, 440, 280, 45);

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
	}
}