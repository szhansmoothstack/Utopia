package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/utopia";
    private final String username = "root";
    private final String password = "Cltee9001!";

    public Connection getConnection () throws ClassNotFoundException, SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(Boolean.FALSE);
        return conn;
    }
}
