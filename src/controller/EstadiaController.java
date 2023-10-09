package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import dao.EstadiaDAO;
import factory.ConexionBase;
import model.Estadia;

public class EstadiaController {
	private EstadiaDAO estadiaDao;

	public EstadiaController() {
		Connection connection = new ConexionBase().conectarBase();
		this.estadiaDao = new EstadiaDAO(connection);
	}

	public void guardarEstadia(Estadia estadias) throws SQLException {
		this.estadiaDao.guardarEstadia(estadias);
	}

	public List<Estadia> buscarEstadias() throws SQLException {
		return this.estadiaDao.buscarEstadias();
	}

	public List<Estadia> buscarEstadiasPorDominio(String dominio) throws SQLException {
		return this.estadiaDao.buscarEstadiasPorDominio(dominio);
	}

	public void actualizarEstadia(Estadia estadia) throws SQLException {
		this.estadiaDao.actualizarEstadia(estadia);
	}

	public void eliminarEstadia(String dominio) throws SQLException {
		this.estadiaDao.eliminarEstadia(dominio);
	}
}