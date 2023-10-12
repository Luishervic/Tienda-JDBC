package org.tienda.dao;

import java.sql.*;

public abstract class ConnectionDB {
    protected Connection connection;
    private final String USER = "root";
    private final String PASSWORD = "241099";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public void openConnectionDB () throws SQLException {
        try {
            String urlDataBase = "jdbc:mysql://localhost:3306/tienda";
            connection = DriverManager.getConnection(urlDataBase, USER, PASSWORD);
        } catch (Exception e) {
            throw e;
        }
    }

    public void closeConnectionDB() throws SQLException {
        try {
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }





}

