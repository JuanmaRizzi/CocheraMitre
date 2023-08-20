package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import controladores.EstadiasControlador;
import controladores.ReservaControlador;
import modelo.Estadias;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class RegistroEstadia extends JFrame {

	private JPanel contentPane;
	private JTextField txtLugarAsignado;
	private JTextField txtModelo;
	private JTextField txtDominio;
	private JTextField txtTitular;
	private JTextField txtTelefono;
	private JComboBox<Format> txtmarca;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;
	
	private EstadiasControlador estadiasControl;
	private ReservaControlador reservasControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroEstadia frame = new RegistroEstadia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistroEstadia() {

		this.estadiasControl = new EstadiasControlador();
		this.reservasControl = new ReservaControlador();

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(RegistroEstadia.class.getResource("/imagenes/lOGO-50PX.png")));
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
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		txtTitular = new JTextField();
		txtTitular.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTitular.setBounds(560, 350, 289, 36);
		txtTitular.setColumns(10);
		txtTitular.setBackground(Color.WHITE);
		txtTitular.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTitular);

		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtModelo.setBounds(560, 204, 285, 33);
		txtModelo.setColumns(10);
		txtModelo.setBackground(Color.WHITE);
		txtModelo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtModelo);

		txtDominio = new JTextField();
		txtDominio.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtDominio.setBounds(560, 278, 285, 36);
		txtDominio.setColumns(10);
		txtDominio.setBackground(Color.WHITE);
		txtDominio.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtDominio);

		txtmarca = new JComboBox();
		txtmarca.setBounds(560, 135, 285, 33);
		txtmarca.setBackground(SystemColor.text);
		txtmarca.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtmarca.setModel(new DefaultComboBoxModel(new String[] { "Abarth", "Alfa Romeo", "Aro", "Asia", "Asia Motors",
				"Aston Martin", "Audi", "Austin", "Auverland", "Bentley", "Bertone", "Bmw", "Cadillac", "Chevrolet",
				"Chrysler", "Citroen", "Corvette", "Dacia", "Daewoo", "Daf", "Daihatsu", "Daimler", "Dodge", "Ferrari",
				"Fiat", "Ford", "Galloper", "Gmc", "Honda", "Hummer", "Hyundai", "Infiniti", "Innocenti", "Isuzu",
				"Iveco", "Iveco-pegaso", "Jaguar", "Jeep", "Kia", "Lada", "Lamborghini", "Lancia", "Land-rover", "Ldv",
				"Lexus", "Lotus", "Mahindra", "Maserati", "Maybach", "Mazda", "Mercedes-benz", "Mg", "Mini",
				"Mitsubishi", "Morgan", "Nissan", "Opel", "Peugeot", "Pontiac", "Porsche", "Renault", "Rolls-royce",
				"Rover", "Saab", "Santana", "Seat", "Skoda", "Smart", "Ssangyong", "Subaru", "Suzuki", "Talbot", "Tata",
				"Toyota", "Umm", "Vaz", "Volkswagen", "Volvo", "Wartburg", "Otro" }));
		contentPane.add(txtmarca);

		// en la label del nombre y nacionalidad puede estar el moco
		JLabel lblMarca = new JLabel("MARCA");
		lblMarca.setBounds(560, 115, 253, 14);/* (560, 135, 285, 33) */
		lblMarca.setForeground(SystemColor.textInactiveText);
		lblMarca.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblMarca);

		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setBounds(560, 189, 255, 14);
		lblModelo.setForeground(SystemColor.textInactiveText);
		lblModelo.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblModelo);

		JLabel lblDominio = new JLabel("DOMINIO");
		lblDominio.setBounds(560, 256, 255, 14);
		lblDominio.setForeground(SystemColor.textInactiveText);
		lblDominio.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblDominio);

		
		
		JLabel lblTitular = new JLabel("TITULAR");
		lblTitular.setBounds(560, 326, 255, 14);
		lblTitular.setForeground(SystemColor.textInactiveText);
		lblTitular.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTitular);

		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(560, 406, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 424, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);

		JLabel lblTitulo = new JLabel("REGISTRO ESTADIA");
		lblTitulo.setBounds(606, 55, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblTitulo);

		JLabel lblLugarAsignado = new JLabel("LUGAR ASIGNADO");
		lblLugarAsignado.setBounds(560, 474, 253, 14);
		lblLugarAsignado.setForeground(SystemColor.textInactiveText);
		lblLugarAsignado.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblLugarAsignado);

		txtLugarAsignado = new JTextField();
		txtLugarAsignado.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtLugarAsignado.setBounds(560, 495, 285, 33);
		txtLugarAsignado.setColumns(10);
		txtLugarAsignado.setBackground(Color.WHITE);
		txtLugarAsignado.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//String numeroLugarAsignado = String.valueOf(lugarAsignado);
		// txtLugarAsignado.setText(id);
		contentPane.add(txtLugarAsignado);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);

		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(723, 560, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
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
		imagenFondo.setIcon(new ImageIcon(RegistroEstadia.class.getResource("/imagenes/registro1.png")));

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
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
	}
	
	private void guardarEstadias() {
		if(!txtmarca.getSelectedItem().toString().equals("") && !txtModelo.equals("") && !txtDominio.equals("") && 
				!txtTitular.equals("") && !txtTelefono.equals("") /*&&  txtLugarAsignado.equals("")*/ ) {
			int NLugarAsignado = Integer.parseInt(txtLugarAsignado.getText());
			Estadias estadias = new Estadias(null, null, txtmarca.getSelectedItem().toString(), txtModelo.getText(), txtDominio.getText(), txtTitular.getText(),txtTelefono.getText(), NLugarAsignado);
			this.estadiasControl.guardar(estadias);
			Exito exito = new Exito();
			exito.setVisible(true);
			dispose();
		}else {
			JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos");
		}
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
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
