package repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoBanco {
	Connection getConnection() throws SQLException;
}
