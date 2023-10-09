package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import views.Login;
import views.MenuUsuario;

public class UsuarioController implements ActionListener {
	private Login vista;

	public UsuarioController(Login vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre = vista.getNombre();
		String contrasenia = vista.getContrasenia();
		if(Usuario.validarUsuario(nombre, contrasenia)) {
			MenuUsuario menu = new MenuUsuario();
			menu.setVisible(true);
			vista.dispose();
		} else {
			JOptionPane.showMessageDialog(vista, "Usuario o Contraseña invalidos");
		}
	}
}