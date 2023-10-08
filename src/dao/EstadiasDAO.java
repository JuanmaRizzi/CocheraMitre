package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Estadia;

public class EstadiasDAO {
	private Connection connection;

	public EstadiasDAO(Connection connection) {
		this.connection = connection;
	}

	public void guardar(Estadia estadias) throws SQLException {
		String sql = "INSERT INTO estadias (fecha_entrada, lugar_asignado, marca, modelo, dominio, titular, telefono,dias, valor )"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setObject(1, estadias.getFechaEntrada());
			preparedStatement.setInt(2, estadias.getLugarAsignado());
			preparedStatement.setObject(3, estadias.getMarca());
			preparedStatement.setString(4, estadias.getModelo());
			preparedStatement.setString(5, estadias.getDominio());
			preparedStatement.setString(6, estadias.getTitular());
			preparedStatement.setString(7, estadias.getTelefono());
			preparedStatement.setInt(8, estadias.getDias());
			preparedStatement.setString(9, estadias.getValor());
			preparedStatement.execute();
			try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
				while(resultSet.next()) {
					estadias.setId(resultSet.getInt(1));
				}
			}
		}
	}

	public List<Estadia> mostrar() throws SQLException {
		String sql = "SELECT fecha_entrada, lugar_asignado, marca, modelo, dominio, titular, telefono,dias, valor FROM estadias";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.execute();
			return this.transformarResultado(preparedStatement);
		}
	}

	public List<Estadia> buscarDominio(String dominio) throws SQLException {
		String sql = "SELECT fecha_entrada, lugar_asignado, marca, modelo, dominio, titular, telefono,dias, valor FROM estadias WHERE dominio = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dominio);
			preparedStatement.execute();
			return this.transformarResultado(preparedStatement);
		}
	}

	public void actualizar(Estadia estadia) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE estadias SET "
				+ "fecha_entrada=?, lugar_asignado=?, marca=?, modelo=?, dominio=?, titular=?, telefono=?, dias=?, valor=? WHERE dominio = ?")) {
			preparedStatement.setObject(1, estadia.getFechaEntrada());
			preparedStatement.setString(2, String.valueOf(estadia.getLugarAsignado()));
			preparedStatement.setString(3, estadia.getMarca());
			preparedStatement.setString(4, estadia.getModelo());
			preparedStatement.setString(5, estadia.getDominio());
			preparedStatement.setString(6, estadia.getTitular());
			preparedStatement.setString(7, estadia.getTelefono());
			preparedStatement.setString(8, String.valueOf(estadia.getDias()));
			preparedStatement.setString(9, estadia.getValor());
			preparedStatement.setString(10, estadia.getDominio());
			preparedStatement.execute();
		}
	}

	public void eliminar(String dominio) throws SQLException {
		try(PreparedStatement stm = connection.prepareStatement("DELETE FROM estadias WHERE dominio = ?")) {
			stm.setString(1, dominio);
			stm.execute();
		}
	}

	private List<Estadia> transformarResultado(PreparedStatement pstm) throws SQLException {
		List<Estadia> listaEstadias = new ArrayList<>();
		try(ResultSet resultSet = pstm.getResultSet()) {
			while(resultSet.next()) {
				Estadia estadia = new Estadia();
				estadia.setDias(Integer.valueOf(resultSet.getString("dias")));
				estadia.setDominio(resultSet.getString("dominio"));
				estadia.setFechaEntrada(resultSet.getDate("fecha_entrada").toLocalDate());
				estadia.setLugarAsignado(Integer.valueOf(resultSet.getString("lugar_asignado")));
				estadia.setMarca(resultSet.getString("marca"));
				estadia.setModelo(resultSet.getString("modelo"));
				estadia.setTelefono(resultSet.getString("telefono"));
				estadia.setTitular(resultSet.getString("titular"));
				estadia.setValor(resultSet.getString("valor"));
				listaEstadias.add(estadia);
			}
		}
		return listaEstadias;
	}
}