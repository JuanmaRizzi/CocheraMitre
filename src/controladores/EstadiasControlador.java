package controladores;

import java.sql.Connection;

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

	
	
}
