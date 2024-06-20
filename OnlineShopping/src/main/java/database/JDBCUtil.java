package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    private static final String DEFAULTSERVER = "localhost";

    private static final int DEFAULTPORT = 3306;

    private static final String DEFAULTUSERNAME = "root";

    private static final String DEFAULTPASSWORD = "Tuong@2004";

    public static Connection getConnection(String database) {
        Connection connection = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = String.format("jdbc:mysql://%s:%d/%s", DEFAULTSERVER, DEFAULTPORT, database);

            connection = DriverManager.getConnection(url, DEFAULTUSERNAME, DEFAULTPASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static Connection getConnection(String server, int port, String database, String...properties) {

        Connection connection = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = String.format("jdbc:mysql://%s:%d/%s", server, port, database);
            String userName = properties[0];
            String password = properties[1];

            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {

        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void printInf(Connection connection) {

        try {

            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getDatabaseProductName());
            System.out.println(metaData.getDatabaseProductVersion());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
