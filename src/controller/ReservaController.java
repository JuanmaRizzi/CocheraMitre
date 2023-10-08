package controller;

import java.sql.Connection;
import java.sql.SQLException;
import dao.ReservaDAO;
import factory.ConexionBase;
import model.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		Connection connection = new ConexionBase().conectarBase();
		this.reservaDAO = new ReservaDAO(connection);
	}

	public void guardar(Reserva reserva) throws SQLException {
		this.reservaDAO.guardar(reserva);
	}
}