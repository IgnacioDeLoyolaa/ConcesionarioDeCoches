package controlador;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    private Connection conexion;

    public conexion() throws SQLException {

        conexion = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.56.102:1521/XE", "ilr","ilr");

    }

    public Connection getConnection() {
        return conexion;
    }

}
