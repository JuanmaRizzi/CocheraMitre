package factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import exception.CocheraException;

public class ConexionBase {
	private DataSource dataSource;

	public ConexionBase() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/Cocheras_Mitre_999_Diario?useTimeZone=true&TimeZone=UTC");
		comboPooledDataSource.setUser("root");
		/*
		 * TODO
		 */
		// comboPooledDataSource.setPassword("HueneiSQL123#");
		comboPooledDataSource.setPassword("root");
		/*
		 * TODO
		 */
		this.dataSource = comboPooledDataSource;
	}

	public Connection conectarBase() {
		try {
			return this.dataSource.getConnection();
		} catch(SQLException e) {
			throw new CocheraException(e);
		}
	}
}