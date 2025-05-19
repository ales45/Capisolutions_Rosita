
package co.edu.unbosque.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // URL JDBC Modificada:
    // Se añade ;INIT=RUNSCRIPT FROM 'classpath:/schema.sql'
    // 'classpath:/' le indica a H2 que busque schema.sql en el classpath.
    private static final String H2_JDBC_URL = "jdbc:h2:./resources/papeleria_db;MODE=MySQL;DATABASE_TO_UPPER=FALSE;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'file:./resources/query.sql'";
    
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        // Con JDBC 4.0+ (Java 6+), Class.forName() no es estrictamente necesario
        // para drivers que cumplen con el Service Provider Interface (SPI), como H2.
        // try {
        //     Class.forName("org.h2.Driver");
        // } catch (ClassNotFoundException e) {
        //     throw new SQLException("Driver H2 no encontrado.", e);
        // }
        return DriverManager.getConnection(H2_JDBC_URL, USER, PASSWORD);
    }
}