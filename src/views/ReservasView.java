package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.util.Calendar;
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
import controller.ReservaController;
import model.Reserva;

@SuppressWarnings("serial")
public class ReservasView extends JFrame {
	private static final Logger logger = Logger.getLogger(ReservasView.class);
	private JPanel contentPane;
	private JTextField txtValor;
	private JDateChooser txtFechaEntrada;
	private JDateChooser txtFechaSalida;
	private JComboBox<String> txtFormaPago;
	private int xMouse;
	private int yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	private transient ReservaController reservaController;
	private static final String ROBOTO = "Roboto";
	private static final String ROBOTO_BLACK = "Roboto Black";
	private static final String ERROR = "ERROR";
	private static final String ERROR_GUARDAR_ESTADIA = "HA OCURRIDO UN ERROR AL INTENTAR GUARDAR LA ESTADÍA: ";
	private static final String ERROR_COMPLETAR_CAMPOS = "Debe completar todos los campos.";
	private static final String ERROR_FECHAS = "La fecha de entrada no puede ser posterior a la de salida";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ReservasView frame = new ReservasView();
				frame.setVisible(true);
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
		});
	}

	public ReservasView() {
		super("Reserva");
		this.reservaController = new ReservaController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		// Código que crea los elementos de la interfáz gráfica
		JSeparator separator12 = new JSeparator();
		separator12.setForeground(SystemColor.textHighlight);
		separator12.setBounds(68, 195, 289, 2);
		separator12.setBackground(SystemColor.textHighlight);
		panel.add(separator12);
		JSeparator separator13 = new JSeparator();
		separator13.setForeground(SystemColor.textHighlight);
		separator13.setBackground(SystemColor.textHighlight);
		separator13.setBounds(68, 453, 289, 2);
		panel.add(separator13);
		JSeparator separator11 = new JSeparator();
		separator11.setForeground(SystemColor.textHighlight);
		separator11.setBounds(68, 281, 289, 11);
		separator11.setBackground(SystemColor.textHighlight);
		panel.add(separator11);
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 169, 14);
		lblCheckIn.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 18));
		panel.add(lblCheckIn);
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 187, 14);
		lblCheckOut.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 18));
		panel.add(lblCheckOut);
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 18));
		panel.add(lblFormaPago);
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 60, 219, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font(ROBOTO, Font.BOLD, 20));
		panel.add(lblTitulo);
		JPanel panel1 = new JPanel();
		panel1.setBounds(428, 0, 482, 560);
		panel1.setBackground(new Color(12, 138, 199));
		panel.add(panel1);
		panel1.setLayout(null);
		JLabel logo = new JLabel("");
		logo.setBounds(197, 25, 104, 107);
		panel1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 196, 14);
		lblValor.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 16));
		panel.add(lblValor);
		JSeparator separator1 = new JSeparator();
		separator1.setForeground(SystemColor.textHighlight);
		separator1.setBounds(68, 362, 289, 2);
		separator1.setBackground(SystemColor.textHighlight);
		panel.add(separator1);
		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
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
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel1.add(btnexit);
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font(ROBOTO, Font.PLAIN, 18));
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
		header.setBackground(Color.WHITE);
		panel.add(header);
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font(ROBOTO, Font.PLAIN, 23));
		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		// Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font(ROBOTO, Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 161, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		panel.add(txtFechaEntrada);
		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font(ROBOTO, Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 246, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		txtFechaSalida.addPropertyChangeListener(propertyChangeListener -> calcularValor(txtFechaEntrada, txtFechaSalida));
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);
		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(78, 328, 43, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font(ROBOTO_BLACK, Font.BOLD, 13));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);
		txtFormaPago = new JComboBox<>();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font(ROBOTO, Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel<>(new String[]{"Transferencia", "Dinero en efectivo"}));
		panel.add(txtFormaPago);
		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				if(txtFechaEntrada.getDate() != null && txtFechaSalida.getDate() != null) {
					guardarReserva();
				} else {
					JOptionPane.showMessageDialog(null, ERROR_COMPLETAR_CAMPOS);
				}
			}
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnsiguiente.add(lblSiguiente);
	}

	private void guardarReserva() {
		if(txtFechaEntrada.getDate() != null && txtFechaSalida.getDate() != null && !txtValor.getText().isEmpty()
				&& !txtFormaPago.getSelectedItem().toString().equals("")) {
			LocalDate dataE = LocalDate.parse(((JTextField)txtFechaEntrada.getDateEditor().getUiComponent()).getText());
			LocalDate dataS = LocalDate.parse(((JTextField)txtFechaSalida.getDateEditor().getUiComponent()).getText());
			Reserva reserva = new Reserva(dataE, dataS, txtValor.getText(), txtFormaPago.getSelectedItem().toString());
			try {
				reservaController.guardar(reserva);
				RegistroEstadia registro = new RegistroEstadia();
				registro.setVisible(true);
				dispose();
			} catch(Exception e) {
				JOptionPane.showMessageDialog(this, ERROR_GUARDAR_ESTADIA, ERROR, JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, ERROR_COMPLETAR_CAMPOS);
		}
	}

	public void calcularValor(JDateChooser dataE, JDateChooser dataS) {
		if(dataE.getDate() != null && dataS.getDate() != null) {
			if(dataE.getDate().after(dataS.getDate())) {
				JOptionPane.showMessageDialog(this, ERROR_FECHAS, ERROR, JOptionPane.ERROR_MESSAGE);
			}
			Calendar inicio = dataE.getCalendar();
			Calendar fin = dataS.getCalendar();
			int dias = -1; // para que cuente desde el dia siguiente
			int noche = 1500;
			int valor;
			while(inicio.before(fin) || inicio.equals(fin)) {
				dias++;
				inicio.add(Calendar.DATE, 1);
			}
			valor = dias * noche;
			txtValor.setText("$" + valor);
		}
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