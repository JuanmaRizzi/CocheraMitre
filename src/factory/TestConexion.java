package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
	public static void main(String[] args) throws SQLException {
		ConexionBase conexionBase = new ConexionBase();
		Connection connection = conexionBase.conectarBase();
		connection.close();
	}
}