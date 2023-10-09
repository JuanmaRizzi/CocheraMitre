package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;
import controller.UsuarioController;

public class Login extends JFrame {
	private static final Logger logger = Logger.getLogger(Login.class);
	private static final long serialVersionUID = 1234567890987654321L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	private int xMouse;
	private int yMouse;
	private JLabel labelExit;
	private static final String ROBOTO = "Roboto";
	private static final String INGRESE_NOMBRE_USUARIO = "Ingrese su nombre de usuario";
	private static final String ASTERISCOS = "********";
	private static final String ROBOTO_BLACK = "Roboto Black";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Login frame = new Login();
				frame.setVisible(true);
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
		});
	}

	public Login() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 788, 527);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(12, 138, 199));
		panel1.setBounds(484, 0, 304, 527);
		panel.add(panel1);
		panel1.setLayout(null);
		JLabel imgHotel = new JLabel("");
		imgHotel.setBounds(0, 0, 304, 538);
		panel1.add(imgHotel);
		imgHotel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/img-parking-login-.png")));
		JPanel btnexit = new JPanel();
		btnexit.setBounds(251, 0, 53, 36);
		panel1.add(btnexit);
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
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setForeground(SystemColor.text);
		labelExit.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(txtUsuario.getText().equals(INGRESE_NOMBRE_USUARIO)) {
					txtUsuario.setText("");
					txtUsuario.setForeground(Color.black);
				}
				if(String.valueOf(txtContrasenia.getPassword()).isEmpty()) {
					txtContrasenia.setText(ASTERISCOS);
					txtContrasenia.setForeground(Color.gray);
				}
			}
		});
		txtUsuario.setFont(new Font(ROBOTO, Font.PLAIN, 16));
		txtUsuario.setText(INGRESE_NOMBRE_USUARIO);
		txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtUsuario.setForeground(SystemColor.activeCaptionBorder);
		txtUsuario.setBounds(65, 256, 324, 32);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 120, 215));
		separator.setBounds(65, 292, 324, 2);
		panel.add(separator);
		JLabel labelTitulo = new JLabel("INICIAR SESIÓN");
		labelTitulo.setForeground(SystemColor.textHighlight);
		labelTitulo.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 26));
		labelTitulo.setBounds(65, 149, 202, 26);
		panel.add(labelTitulo);
		JSeparator separator1 = new JSeparator();
		separator1.setBackground(SystemColor.textHighlight);
		separator1.setBounds(65, 393, 324, 2);
		panel.add(separator1);
		txtContrasenia = new JPasswordField();
		txtContrasenia.setText(ASTERISCOS);
		txtContrasenia.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(String.valueOf(txtContrasenia.getPassword()).equals(ASTERISCOS)) {
					txtContrasenia.setText("");
					txtContrasenia.setForeground(Color.black);
				}
				if(txtUsuario.getText().isEmpty()) {
					txtUsuario.setText(INGRESE_NOMBRE_USUARIO);
					txtUsuario.setForeground(Color.gray);
				}
			}
		});
		txtContrasenia.setForeground(SystemColor.activeCaptionBorder);
		txtContrasenia.setFont(new Font(ROBOTO, Font.PLAIN, 16));
		txtContrasenia.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtContrasenia.setBounds(65, 353, 324, 32);
		panel.add(txtContrasenia);
		JLabel labelUsuario = new JLabel("USUARIO");
		labelUsuario.setForeground(SystemColor.textInactiveText);
		labelUsuario.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 20));
		labelUsuario.setBounds(65, 219, 107, 26);
		panel.add(labelUsuario);
		JLabel lblContrasenia = new JLabel("CONTRASEÑA");
		lblContrasenia.setForeground(SystemColor.textInactiveText);
		lblContrasenia.setFont(new Font(ROBOTO_BLACK, Font.PLAIN, 20));
		lblContrasenia.setBounds(65, 316, 140, 26);
		panel.add(lblContrasenia);
		JButton btnLogin = new JButton();
		btnLogin.addActionListener(new UsuarioController(this));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(SystemColor.textHighlight);
			}
		});
		btnLogin.setBackground(SystemColor.textHighlight);
		btnLogin.setBounds(65, 431, 122, 44);
		panel.add(btnLogin);
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel lblNewLabel = new JLabel("ENTRAR");
		lblNewLabel.setBounds(0, 0, 122, 44);
		btnLogin.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.controlLtHighlight);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/lOGO-50PX.png")));
		lblNewLabel1.setBounds(65, 65, 48, 59);
		panel.add(lblNewLabel1);
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
		header.setBackground(SystemColor.window);
		header.setBounds(0, 0, 784, 36);
		panel.add(header);
		header.setLayout(null);
	}

	public String getNombre() {
		return txtUsuario.getText();
	}

	public String getContrasenia() {
		return new String(txtContrasenia.getPassword());
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