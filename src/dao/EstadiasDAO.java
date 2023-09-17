package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Estadias;
import modelo.Reserva;

public class EstadiasDAO {

	private Connection con;
	
	
	public EstadiasDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Estadias estadias) {
		
		try {
			String sql = "INSERT INTO estadias (fecha_entrada, lugar_asignado, marca, modelo, dominio, titular, telefono,dias, valor )"
					+"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setObject(1, estadias.getDataE());
				pstm.setInt(2, estadias.getLugarAsignado());
				pstm.setObject(3, estadias.getMarca());
				pstm.setString(4, estadias.getModelo());
				pstm.setString(5, estadias.getDominio());
				pstm.setString(6, estadias.getTitular());
				pstm.setString(7, estadias.getTelefono());
				pstm.setInt(8, estadias.getDias());
				pstm.setString(9, estadias.getValor());
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()) {
					while(rst.next()) {
						estadias.setId(rst.getInt(1));	
					}
					
				} 
					
					
				
			}
		} catch (SQLException e) {
			throw new RuntimeException("Tiere" + e.getMessage(), e);
		}
	}
	
	public List<Estadias> mostrar(){
		List<Estadias> estadias = new ArrayList<Estadias>();
		try {
			
			String sql = "SELECT fecha_entrada, lugar_asignado, marca, modelo, dominio, titular, telefono,dias, valor FROM estadias";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.execute();
				
				transformarResultado(estadias,  pstm);
			}
			return estadias;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<Estadias> buscarDominio(String dominio){
		List<Estadias> estadias = new ArrayList<Estadias>();
		try {
			
			String sql = "SELECT fecha_entrada, lugar_asignado, marca, modelo, dominio, titular, telefono,dias, valor FROM estadias WHERE dominio = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.setString(1, dominio);
				pstm.execute();
				
				transformarResultado(estadias,  pstm);
			}
			return estadias;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public void actualizar(LocalDate dataE, Integer lugarAsignado, Object marca, String modelo, String dominio,
			String titular, String telefono, Integer dias, String valor) {
		try(PreparedStatement pstm = con.prepareStatement("UPDATE estadias SET "
				+ "fecha_entrada=?, lugar_asignado=?, marca=?, modelo=?, dominio=?, titular=?, telefono=?, dias=?, valor=? WHERE dominio = ?")) {
			
			pstm.setObject(1,dataE);
			pstm.setInt(2,lugarAsignado);
			pstm.setObject(3, marca);
			pstm.setString(4, modelo);
			pstm.setString(5, dominio);
			pstm.setString(6, titular);
			pstm.setString(7, telefono);
			pstm.setInt(8, dias);
			pstm.setString(9, valor);
			
			pstm.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminar(String dominio) {
		try (PreparedStatement stm = con.prepareStatement("DELETE FROM estadias WHERE dominio = ?")){
			stm.setString(1, dominio);
			stm.execute();
		}catch(SQLException e) {
			throw new RuntimeException("animal" + e.getMessage(), e);
		}
	}
	
	
	private void transformarResultado(List<Estadias> estadias, PreparedStatement pstm) throws SQLException {
		
		try(ResultSet rst = pstm.getResultSet()){  //pstm.executeQuery()
			while(rst.next()) {
				LocalDate fechaE = rst.getDate("fecha_entrada").toLocalDate();
				Integer lugarAsignado = rst.getInt("lugar_asignado");
				Object marca = rst.getObject("marca");
				String  modelo = rst.getString("modelo");
				String  dominio = rst.getString("dominio");
				String  titular = rst.getString("titular");
				String  telefono = rst.getString("telefono");
				Integer dias = rst.getInt("dias");
				String  valor = rst.getString("valor");
				
				Estadias producto = new Estadias(fechaE, lugarAsignado, marca, modelo, dominio, titular, telefono, dias, valor);
				estadias.add(producto);
				
			}
		}
		
	}
}
