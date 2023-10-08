package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.Format;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import org.apache.log4j.Logger;
import com.toedter.calendar.JDateChooser;
import controller.EstadiasController;
import model.Estadia;

@SuppressWarnings("serial")
public class RegistroEstadia extends JFrame {
	private static final Logger logger = Logger.getLogger(RegistroEstadia.class);
	private JPanel contentPane;
	private JDateChooser txtFechaEntrada;
	private JTextField txtLugarAsignado;
	private JComboBox<Format> txtmarca;
	private JTextField txtModelo;
	private JTextField txtDominio;
	private JTextField txtTitular;
	private JTextField txtTelefono;
	private JTextField txtDias;
	private JTextField txtValor;
	private JLabel labelExit;
	private JLabel labelAtras;
	private int xMouse;
	private int yMouse;
	private transient EstadiasController estadiasController;
	private static final String ROBOTO = "Roboto";
	private static final String ROBOTO_BLACK = "Roboto Black";
	private static final String ERROR_GUARDAR_ESTADIA = "HA OCURRIDO UN ERROR AL INTENTAR GUARDAR LA ESTADÍA: ";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				RegistroEstadia frame = new RegistroEstadia();
				frame.setVisible(true);
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
		});
	}

	public RegistroEstadia() {
		this.estadiasController = new EstadiasController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroEstadia.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font(ROBOTO, Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font(ROBOTO, Font.PLAIN, 12));
		txtFechaEntrada.setBounds(560, 75, 289, 36);
		txtFechaEntrada.getCalendarButton().setBounds(560, 300, 289, 36);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		contentPane.add(txtFechaEntrada);
		txtTitular = new JTextField();
		txtTitular.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		txtTitular.setBounds(560, 298, 289, 36);
		txtTitular.setColumns(10);
		txtTitular.setBackground(Color.WHITE);
		txtTitular.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTitular);
		txtModelo = new JTextField();
		txtModelo.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		txtModelo.setBounds(560, 190, 285, 33);
		txtModelo.setColumns(10);
		txtModelo.setBackground(Color.WHITE);
		txtModelo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtModelo);
		txtDominio = new JTextField();
		txtDominio.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		txtDominio.setBounds(560, 244, 285, 36);
		txtDominio.setColumns(10);
		txtDominio.setBackground(Color.WHITE);
		txtDominio.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtDominio);
		txtmarca = new JComboBox<>();
		txtmarca.setBounds(560, 135, 285, 33);
		txtmarca.setBackground(SystemColor.text);
		txtmarca.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		txtmarca.setModel(new DefaultComboBoxModel(new String[]{"Abarth", "Alfa Romeo", "Aro", "Asia", "Asia Motors", "Aston Martin", "Audi", "Austin",
				"Auverland", "Bentley", "Bertone", "Bmw", "Cadillac", "Chevrolet", "Chrysler", "Citroen", "Corvette", "Dacia", "Daewoo", "Daf", "Daihatsu",
				"Daimler", "Dodge", "Ferrari", "Fiat", "Ford", "Galloper", "Gmc", "Honda", "Hummer", "Hyundai", "Infiniti", "Innocenti", "Isuzu", "Iveco",
				"Iveco-pegaso", "Jaguar", "Jeep", "Kia", "Lada", "Lamborghini", "Lancia", "Land-rover", "Ldv", "Lexus", "Lotus", "Mahindra", "Maserati", "Maybach",
				"Mazda", "Mercedes-benz", "Mg", "Mini", "Mitsubishi", "Morgan", "Nissan", "Opel", "Peugeot", "Pontiac", "Porsche", "Renault", "Rolls-royce",
				"Rover", "Saab", "Santana", "Seat", "Skoda", "Smart", "Ssangyong", "Subaru", "Suzuki", "Talbot", "Tata", "Toyota", "Umm", "Vaz", "Volkswagen",
				"Volvo", "Wartburg", "Otro"}));
		contentPane.add(txtmarca);
		// en la label del nombre y nacionalidad puede estar el moco
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(560, 55, 255, 14);
		lblCheckIn.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblCheckIn);
		JLabel lblMarca = new JLabel("MARCA");
		lblMarca.setBounds(560, 115, 253, 14);/* (560, 135, 285, 33) */
		lblMarca.setForeground(SystemColor.textInactiveText);
		lblMarca.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblMarca);
		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setBounds(560, 175, 255, 14);
		lblModelo.setForeground(SystemColor.textInactiveText);
		lblModelo.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblModelo);
		JLabel lblDominio = new JLabel("DOMINIO");
		lblDominio.setBounds(560, 230, 255, 14);
		lblDominio.setForeground(SystemColor.textInactiveText);
		lblDominio.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblDominio);
		JLabel lblTitular = new JLabel("TITULAR");
		lblTitular.setBounds(560, 285, 255, 14);
		lblTitular.setForeground(SystemColor.textInactiveText);
		lblTitular.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblTitular);
		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(560, 345, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblTelefono);
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		txtTelefono.setBounds(560, 365, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);
		JLabel lblTitulo = new JLabel("REGISTRO ESTADIA");
		lblTitulo.setBounds(560, 5, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 20));
		contentPane.add(lblTitulo);
		JLabel lblLugarAsignado = new JLabel("LUGAR ASIGNADO");
		lblLugarAsignado.setBounds(560, 405, 253, 14);
		lblLugarAsignado.setForeground(SystemColor.textInactiveText);
		lblLugarAsignado.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblLugarAsignado);
		JLabel lblDias = new JLabel("DIAS");
		lblDias.setBounds(560, 459, 253, 14);
		lblDias.setForeground(SystemColor.textInactiveText);
		lblDias.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblDias);
		JLabel lblValor = new JLabel("VALOR");
		lblValor.setBounds(560, 510, 253, 14);
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 14));
		contentPane.add(lblValor);
		txtLugarAsignado = new JTextField();
		txtLugarAsignado.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		txtLugarAsignado.setBounds(560, 422, 285, 33);
		txtLugarAsignado.setColumns(10);
		txtLugarAsignado.setBackground(Color.WHITE);
		txtLugarAsignado.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtLugarAsignado);
		txtDias = new JTextField();
		txtDias.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		txtDias.setBounds(560, 472, 285, 33);
		txtDias.setColumns(10);
		txtDias.setBackground(Color.WHITE);
		txtDias.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtDias);
		txtDias.addPropertyChangeListener(propertyChangeEvent -> calcularValor(txtDias));
		txtValor = new JTextField();
		txtValor.setFont(new Font(ROBOTO, Font.PLAIN, 13));
		txtValor.setBounds(560, 524, 285, 33);
		txtValor.setColumns(10);
		txtValor.setBackground(Color.WHITE);
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtValor);
		JSeparator separator11 = new JSeparator();
		separator11.setBounds(560, 170, 289, 2);
		separator11.setForeground(new Color(12, 138, 199));
		separator11.setBackground(new Color(12, 138, 199));
		contentPane.add(separator11);
		JSeparator separator12 = new JSeparator();
		separator12.setBounds(560, 111, 289, 2);
		separator12.setForeground(new Color(12, 138, 199));
		separator12.setBackground(new Color(12, 138, 199));
		contentPane.add(separator12);
		JSeparator separator121 = new JSeparator();
		separator121.setBounds(560, 225, 289, 2);
		separator121.setForeground(new Color(12, 138, 199));
		separator121.setBackground(new Color(12, 138, 199));
		contentPane.add(separator121);
		JSeparator separator122 = new JSeparator();
		separator122.setBounds(560, 280, 289, 2);
		separator122.setForeground(new Color(12, 138, 199));
		separator122.setBackground(new Color(12, 138, 199));
		contentPane.add(separator122);
		JSeparator separator123 = new JSeparator();
		separator123.setBounds(560, 335, 289, 2);
		separator123.setForeground(new Color(12, 138, 199));
		separator123.setBackground(new Color(12, 138, 199));
		contentPane.add(separator123);
		JSeparator separator124 = new JSeparator();
		separator124.setBounds(560, 398, 289, 2);
		separator124.setForeground(new Color(12, 138, 199));
		separator124.setBackground(new Color(12, 138, 199));
		contentPane.add(separator124);
		JSeparator separator125 = new JSeparator();
		separator125.setBounds(560, 455, 289, 2);
		separator125.setForeground(new Color(12, 138, 199));
		separator125.setBackground(new Color(12, 138, 199));
		contentPane.add(separator125);
		JSeparator separator126 = new JSeparator();
		separator126.setBounds(560, 505, 289, 2);
		separator126.setForeground(new Color(12, 138, 199));
		separator126.setBackground(new Color(12, 138, 199));
		contentPane.add(separator126);
		JSeparator separator127 = new JSeparator();
		separator127.setBounds(560, 557, 289, 2);
		separator127.setForeground(new Color(12, 138, 199));
		separator127.setBackground(new Color(12, 138, 199));
		contentPane.add(separator127);
		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(723, 600, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calcularValor(txtDias);
				guardarEstadias();
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroEstadia.class.getResource("/imagenes/reservas-img-3.png")));
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroEstadia.class.getResource("/imagenes/Ha-100px.png")));
		JPanel btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		contentPane.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
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
		btnexit.setBackground(Color.white);
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.black);
		labelExit.setFont(new Font(ROBOTO, Font.PLAIN, 18));
	}

	private void guardarEstadias() {
		try {
			if(!txtmarca.getSelectedItem().toString().equals("") && !txtModelo.getText().isEmpty() && !txtDominio.getText().isEmpty()
					&& !txtTitular.getText().isEmpty() && !txtTelefono.getText().isEmpty() && !txtLugarAsignado.getText().isEmpty()) {
				int lugarAsignado = Integer.parseInt(txtLugarAsignado.getText());
				int diasString = Integer.parseInt(txtDias.getText());
				LocalDate fechaEntrada = LocalDate.parse(((JTextField)txtFechaEntrada.getDateEditor().getUiComponent()).getText());
				Estadia estadia = new Estadia();
				estadia.setDias(diasString);
				estadia.setDominio(txtDominio.getText());
				estadia.setFechaEntrada(fechaEntrada);
				estadia.setLugarAsignado(lugarAsignado);
				estadia.setMarca(String.valueOf(txtmarca.getSelectedItem()));
				estadia.setModelo(txtModelo.getText());
				estadia.setTelefono(txtTelefono.getText());
				estadia.setTitular(txtTitular.getText());
				estadia.setValor(txtValor.getText());
				this.estadiasController.guardar(estadia);
				Exito exito = new Exito();
				exito.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos");
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(this, ERROR_GUARDAR_ESTADIA + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void calcularValor(JTextField txtDias) {
		if(!txtDias.getText().equals("")) {
			int dias = Integer.parseInt(txtDias.getText());
			int noche = 1500;
			int valor = dias * noche;
			txtValor.setText("$" + valor);
		} else {
			txtValor.setText("$" + 1500);
		}
	}

	public void limpiarValor() {
		txtValor.setText("");
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x" e "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}