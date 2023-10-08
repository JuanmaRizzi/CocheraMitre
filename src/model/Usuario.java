package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import factory.ConexionBase;

public class Usuario {
	private static final Logger logger = Logger.getLogger(Usuario.class);
	private String nombre;
	private String contrasenia;

	public Usuario() {
	}

	public Usuario(String nombre, String contrasenia) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public static boolean validarUsuario(String nombre, String contrasenia) {
		ConexionBase conexionBase = new ConexionBase();
		Connection connection = conexionBase.conectarBase();
		ResultSet resultSet = null;
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE nombre=? AND contrasenia=?")) {
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, contrasenia);
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
			} catch(SQLException e2) {
				logger.error(e2.getMessage(), e2);
			}
		}
	}
}