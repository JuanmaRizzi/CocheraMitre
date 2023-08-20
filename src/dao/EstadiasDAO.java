package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Estadias;
import modelo.Reserva;

public class EstadiasDAO {

	private Connection con;
	private Reserva reserva;
	
	public EstadiasDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Estadias estadias) {
		
		try {
			String sql = "INSERT INTO estadias (fecha_entrada, fecha_salida, marca, modelo, dominio, titular, telefono, lugar_asignado)"
					+"VALUES(?, ?, ?, ?, ?, ?)";
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.set (7, reserva.getDataE());
				pstm.setObject(8, reserva.getDataS());
				pstm.setObject(1, estadias.getMarca());
				pstm.setString(2, estadias.getModelo());
				pstm.setString(3, estadias.getDominio());
				pstm.setString(4, estadias.getTitular());
				pstm.setString(5, estadias.getTelefono());
				pstm.setInt(6, estadias.getLugarAsignado());
				
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
}
