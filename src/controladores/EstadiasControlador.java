package controladores;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import dao.EstadiasDAO;
import factory.ConexionBase;
import modelo.Estadias;

public class EstadiasControlador {

	private EstadiasDAO estadiasDao;

	public EstadiasControlador() {
		Connection con = new ConexionBase().conectarBase();
		this.estadiasDao = new EstadiasDAO(con);
	}

	public void guardar(Estadias estadias) {
		this.estadiasDao.guardar(estadias);
	}

	public List<Estadias> mostrar() {
		return this.estadiasDao.mostrar();
	}

	public List<Estadias> buscar(String dominio) {
		return this.estadiasDao.buscarDominio(dominio);
	}

	public void actualizarEstadias(LocalDate dataE, Integer lugarAsignado, Object marca, String modelo, String dominio,
			String titular, String telefono, Integer dias, String valor) {
		this.estadiasDao.actualizar(dataE, lugarAsignado, marca, modelo, dominio, titular, telefono, dias, valor);
	}

	
	public void eliminar(String dominio) {
		this.estadiasDao.eliminar(dominio);
	}
}
