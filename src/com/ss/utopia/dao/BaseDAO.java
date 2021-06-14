package com.ss.utopia.dao;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public abstract class BaseDAO<T> {
    public static Connection conn = null;

    public BaseDAO(Connection conn) {
        BaseDAO.conn = conn;
    }

    public void save(String sql, Object[] vals) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        if (vals != null) {
            int count = 1;
            for (Object o : vals) {
                preparedStatement.setObject(count, o);
                count++;
            }
        }
        preparedStatement.executeUpdate();
    }

    public Integer saveWithPk(String sql, Object[] vals) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        if (vals != null) {
            int count = 1;
            for (Object o : vals) {
                preparedStatement.setObject(count, o);
                count++;
            }
        }
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return null;
    }

    public List<T> read(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        if (vals != null) {
            int count = 1;
            for (Object o : vals) {
                preparedStatement.setObject(count, o);
                count++;
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        return extractData(resultSet);
    }

    public abstract List<T> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException;
}
