package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexionBase {
	public DataSource dataSou;
	
	public ConexionBase() {
		ComboPooledDataSource comboPool = new ComboPooledDataSource();
		comboPool.setJdbcUrl("jdbc:mysql://localhost/Cocheras_Mitre_999_Diario?useTimeZone=true&TimeZone=UTC");
		comboPool.setUser("root");
		comboPool.setPassword("HueneiSQL123#");
		
		this.dataSou = comboPool;
	}
	
	public Connection conectarBase() {
		try {
			return this.dataSou.getConnection();
		} catch (SQLException e) {
			System.out.println("an error ocurred");
			throw new RuntimeException(e);
		}
	} 

}
