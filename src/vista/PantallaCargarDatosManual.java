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
import javax.swing.SwingConstants;

public class PantallaCargarDatosManual extends JFrame {

	private JPanel panelFondo;
	private JLabel lblTitulo;
	private JButton btnVolver, btnIrResultado;

	public PantallaCargarDatosManual(InterfazGestorPantalla gestorInterfaz) {
		configurarPantalla();
		crearElementos(gestorInterfaz);
	}

	private void configurarPantalla() {
		setTitle("Carga Manual (MVP)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		panelFondo = new JPanel();
		panelFondo.setBackground(new Color(30, 41, 59));
		setContentPane(panelFondo);
		panelFondo.setLayout(null);
	}

	private void crearElementos(InterfazGestorPantalla gestorInterfaz) {
		lblTitulo = new JLabel("Pantalla de Carga Manual", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(100, 120, 700, 50);
		panelFondo.add(lblTitulo);

		btnIrResultado = new JButton("Ver Resultado");
		btnIrResultado.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnIrResultado.setForeground(Color.WHITE);
		btnIrResultado.setBackground(new Color(16, 185, 129)); // verde
		btnIrResultado.setFocusPainted(false);
		btnIrResultado.setBorderPainted(false);
		btnIrResultado.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIrResultado.setBounds(300, 220, 300, 50);

		btnIrResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorInterfaz.crearPantallaResultado();
			}
		});
		panelFondo.add(btnIrResultado);

		btnVolver = new JButton("Volver al Menú");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(239, 68, 68)); // rojo
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(300, 300, 300, 50);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorInterfaz.crearPantallaInicio();
			}
		});
		panelFondo.add(btnVolver);
	}
}