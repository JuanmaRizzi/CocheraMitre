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
	private Reserva reserva;
	
	public EstadiasDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Estadias estadias) {
		
		try {
			String sql = "INSERT INTO estadias (fecha_entrada, fecha_salida, marca, modelo, dominio, titular, telefono, lugar_asignado)"
					+"VALUES(?, ?, ?, ?, ?, ?)";
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setDate(1, Date.valueOf(reserva.getDataE()));
				pstm.setDate(2, Date.valueOf(reserva.getDataS()));
				pstm.setObject(3, estadias.getMarca());
				pstm.setString(4, estadias.getModelo());
				pstm.setString(5, estadias.getDominio());
				pstm.setString(6, estadias.getTitular());
				pstm.setString(7, estadias.getTelefono());
				pstm.setInt(8, estadias.getLugarAsignado());
				
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
