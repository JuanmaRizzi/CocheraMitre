package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class Exito extends JDialog {
	private static final Logger logger = Logger.getLogger(Exito.class);
	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args) {
		try {
			Exito dialog = new Exito();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Exito() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/aH-40px.png")));
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel.setBounds(123, 11, 100, 100);
		contentPanel.add(lblNewLabel);
		JLabel lblNewLabel1 = new JLabel("Datos guardados satisfactoriamente");
		lblNewLabel1.setForeground(new Color(12, 138, 199));
		lblNewLabel1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel1.setBounds(27, 122, 322, 21);
		contentPanel.add(lblNewLabel1);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(actionEvent -> {
			dispose(); // sirve para cerrar la ventana actual
			MenuUsuario usuario = new MenuUsuario();
			usuario.setVisible(true);
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}