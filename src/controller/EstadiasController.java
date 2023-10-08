package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import dao.EstadiasDAO;
import factory.ConexionBase;
import model.Estadia;

public class EstadiasController {
	private EstadiasDAO estadiasDao;

	public EstadiasController() {
		Connection con = new ConexionBase().conectarBase();
		this.estadiasDao = new EstadiasDAO(con);
	}

	public void guardar(Estadia estadias) throws SQLException {
		this.estadiasDao.guardar(estadias);
	}

	public List<Estadia> mostrar() throws SQLException {
		return this.estadiasDao.mostrar();
	}

	public List<Estadia> buscar(String dominio) throws SQLException {
		return this.estadiasDao.buscarDominio(dominio);
	}

	public void actualizarEstadias(Estadia estadia) throws SQLException {
		this.estadiasDao.actualizar(estadia);
	}

	public void eliminar(String dominio) throws SQLException {
		this.estadiasDao.eliminar(dominio);
	}
}