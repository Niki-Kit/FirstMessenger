package programm;

import java.sql.*;

public class ConnectToSQL {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootPassword";
    private static  Connection connection;
    public static PreparedStatement preparedStatement = null;
    public void connect () throws SQLException {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }

    public static Connection getConnection() {
        return connection;
    }
}
