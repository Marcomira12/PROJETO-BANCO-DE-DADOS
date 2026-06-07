package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostegresSQL implements ConexaoBanco{
	private static final String URL= "jdbc:postgresql://localhost:5432/EmpresaVarejo";
	private static final String USER= "postgres";
	private static final String PASSWORD= "";
	
	@Override
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
}
