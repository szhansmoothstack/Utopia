package com.ss.utopia.dao;

import com.ss.utopia.domain.User;
import com.ss.utopia.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO<User>{
    public UserDAO(Connection conn) {
        super(conn);
    }

    public void addUser(User user) throws SQLException {
        int newId = saveWithPk("insert into user ( role_id , given_name , " +
                "family_name , username , email , password , phone ) values (?,?,?,?,?,?,?)", new Object[]{
                user.getRoleId(), user.getGivenName(), user.getFamilyName(),
                user.getUsername(), user.getEmail(), user.getPassword(), user.getPhone()
        });
        user.setId(newId);
    }

    public void updateUser(User user) throws SQLException {
        save("update user set role_id = ?, given_name = ?, family_name = ?, " +
                        "username = ?, email = ?, password = ?, phone = ? where id = ?",
                new Object[]{user.getRoleId(), user.getGivenName(), user.getFamilyName(),
                        user.getUsername(), user.getEmail(), user.getPassword(),
                        user.getPhone(), user.getId()});
    }

    public void deleteUser(int id) throws SQLException {
        save("delete from user where id = ?"
                , new Object[]{id});
    }

    public List<User> readAllUsers() throws SQLException, ClassNotFoundException {
        return read("select * from user",
                null);
    }

    public List<User> readAllTravelers() throws SQLException, ClassNotFoundException {
        return read("select * from user where role_id = 3",
                null);
    }

    public List<User> readAllEmployees() throws SQLException, ClassNotFoundException {
        return read("select * from user where role_id = 2",
                null);
    }

    public List<User> readAllUsername(String username) throws SQLException, ClassNotFoundException {
        return read("select * from user where username = ?",
                new Object[]{username});
    }
    @Override
    public List<User> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User(resultSet.getInt("id"),
                    resultSet.getInt("role_id"), resultSet.getString("given_name"),
                    resultSet.getString("family_name"), resultSet.getString("username"),
                    resultSet.getString("email"), resultSet.getString("password"),
                    resultSet.getString("phone"));
            users.add(user);
        }
        return users;
    }
}
