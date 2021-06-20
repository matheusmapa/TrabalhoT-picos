

//essa parte do código serve para startar a conexão com o banco de dados, tal como para fecha-lá também

package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Samuel
 */
public class ConnectionFactory {

    public static Connection getConnection() {
        final String DB_NAME = "UNIVERSIDADE";
        final String USER = "root";
        final String PASS = "";

        final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;

        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static boolean closeConnection() {
        try {
            getConnection().close();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

}

