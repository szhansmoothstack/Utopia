package com.ss.utopia.jdbc;

import java.sql.*;
import java.util.Scanner;

public class ReadAllRoutes {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/utopia";
    private static final String username = "root";
    private static final String password = "Cltee9001!";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //register driver
        Class.forName(driver);

        //create connection
        Connection conn = DriverManager.getConnection(url, username, password);

        //statement obj
        Statement stmt = conn.createStatement();

        //Execute
        //ResultSet rs = stmt.executeQuery(sql);

    }
}
