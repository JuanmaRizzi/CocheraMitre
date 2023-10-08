package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MenuUsuario extends JFrame {
	private static final Logger logger = Logger.getLogger(MenuUsuario.class);
	private JPanel contentPane;
	private int xMouse;
	private int yMouse;
	private JLabel labelExit;
	private JLabel labelRegistro;
	private static final String ROBOTO = "Roboto";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MenuUsuario frame = new MenuUsuario();
				frame.setVisible(true);
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
		});
	}

	public MenuUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(12, 138, 199));
		panelMenu.setBounds(0, 0, 257, 609);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setBounds(50, 58, 150, 150);
		panelMenu.add(lblNewLabel2);
		lblNewLabel2.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/aH-150px.png")));
		JPanel btnRegistro = new JPanel();
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				RegistroEstadia estadias = new RegistroEstadia();
				estadias.setVisible(true);
				dispose();
			}
		});
		btnRegistro.setBounds(0, 255, 257, 56);
		btnRegistro.setBackground(new Color(12, 138, 199));
		panelMenu.add(btnRegistro);
		btnRegistro.setLayout(null);
		labelRegistro = new JLabel("Registro de estadias");
		labelRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/reservado.png")));
		labelRegistro.setForeground(SystemColor.text);
		labelRegistro.setBounds(25, 11, 205, 34);
		labelRegistro.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistro.add(labelRegistro);
		JPanel btnBusqueda = new JPanel();
		btnBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBusqueda.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBusqueda.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				try {
					Busqueda busqueda = new Busqueda();
					busqueda.setVisible(true);
					dispose();
				} catch(Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		btnBusqueda.setBounds(0, 312, 257, 56);
		btnBusqueda.setBackground(new Color(12, 138, 199));
		panelMenu.add(btnBusqueda);
		btnBusqueda.setLayout(null);
		JLabel lblBusquedaDeReservas = new JLabel("Búsqueda");
		lblBusquedaDeReservas.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/pessoas.png")));
		lblBusquedaDeReservas.setBounds(27, 11, 200, 34);
		lblBusquedaDeReservas.setHorizontalAlignment(SwingConstants.LEFT);
		lblBusquedaDeReservas.setForeground(Color.WHITE);
		lblBusquedaDeReservas.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		btnBusqueda.add(lblBusquedaDeReservas);
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		panelMenu.add(separator);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(891, 0, 53, 36);
		header.add(btnexit);
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		JPanel panelFecha = new JPanel();
		panelFecha.setBackground(new Color(118, 187, 223));
		panelFecha.setBounds(256, 84, 688, 121);
		contentPane.add(panelFecha);
		panelFecha.setLayout(null);
		JLabel lblNewLabel1 = new JLabel("Sistema de reservas Cochera Mitre");
		lblNewLabel1.setBounds(180, 11, 356, 42);
		panelFecha.add(lblNewLabel1);
		lblNewLabel1.setForeground(Color.WHITE);
		lblNewLabel1.setFont(new Font(ROBOTO, Font.PLAIN, 24));
		JLabel labelFecha = new JLabel("New label");
		labelFecha.setBounds(35, 64, 294, 36);
		panelFecha.add(labelFecha);
		labelFecha.setForeground(Color.WHITE);
		labelFecha.setFont(new Font(ROBOTO, Font.PLAIN, 33));
		Date fechaActual = new Date(); // fecha y hora actual
		String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual); // formatear la fecha en una cadena
		labelFecha.setText("Hoy es " + fecha); // setear la representacion en cadena de la fecha
		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setFont(new Font(ROBOTO, Font.BOLD, 24));
		lblNewLabel.setBounds(302, 234, 147, 46);
		contentPane.add(lblNewLabel);
		String textoDescripcion = "<html><body>Sistema de gestion de Cochera. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
		JLabel labelDescripcion = new JLabel(textoDescripcion);
		labelDescripcion.setFont(new Font(ROBOTO, Font.PLAIN, 17));
		labelDescripcion.setBounds(312, 291, 598, 66);
		contentPane.add(labelDescripcion);
		String textoDescripcion1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus clientes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
		JLabel labelDescripcion1 = new JLabel(textoDescripcion1);
		labelDescripcion1.setFont(new Font(ROBOTO, Font.PLAIN, 17));
		labelDescripcion1.setBounds(311, 345, 569, 88);
		contentPane.add(labelDescripcion1);
		JLabel lblNewLabel3 = new JLabel("- Registro de Reservas y Huéspedes");
		lblNewLabel3.setFont(new Font(ROBOTO, Font.PLAIN, 17));
		lblNewLabel3.setBounds(312, 444, 295, 27);
		contentPane.add(lblNewLabel3);
		JLabel lblNewLabel31 = new JLabel("- Edición de Reservas y Huéspedes existentes");
		lblNewLabel31.setFont(new Font(ROBOTO, Font.PLAIN, 17));
		lblNewLabel31.setBounds(312, 482, 355, 27);
		contentPane.add(lblNewLabel31);
		JLabel lblNewLabel32 = new JLabel("- Eliminar todo tipo de registros");
		lblNewLabel32.setFont(new Font(ROBOTO, Font.PLAIN, 17));
		lblNewLabel32.setBounds(312, 520, 295, 27);
		contentPane.add(lblNewLabel32);
	}

	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}// GEN-LAST:event_headerMousePressed

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}