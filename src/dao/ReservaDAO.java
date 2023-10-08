package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Reserva;

public class ReservaDAO {
	private Connection connection;

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}

	public void guardar(Reserva reserva) throws SQLException {
		String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago)" + "VALUES (?,?,?,?)";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setObject(1, reserva.getFechaEntrada());
			preparedStatement.setObject(2, reserva.getFechaSalida());
			preparedStatement.setString(3, reserva.getValor());
			preparedStatement.setObject(4, reserva.getFormaDePago());
			preparedStatement.executeUpdate();
			try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
				while(resultSet.next()) {
					reserva.setId(resultSet.getInt(1));
				}
			}
		}
	}
}