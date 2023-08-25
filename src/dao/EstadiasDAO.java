package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
