package com.ss.utopia.dao;

import com.ss.utopia.domain.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO extends BaseDAO<UserRole> {

    public UserRoleDAO(Connection conn) {
        super(conn);
    }

    public void addUserRole (UserRole userRole) throws SQLException {
        int newId = saveWithPk("insert into user_role ( name ) values (?)", new Object[]{
                userRole.getName()
        });
        userRole.setId(newId);
    }

    public void updateUserRole (UserRole userRole) throws SQLException {
        save("update user_role set name = ? where id = ?",
                new Object[] {userRole.getName(),
                        userRole.getId()});
    }

    public void deleteUserRole(UserRole userRole) throws SQLException {
        save("delete from user_role where id = ?"
                , new Object[]{userRole.getId()});
    }

    public List<UserRole> readAllUserRoles() throws SQLException, ClassNotFoundException {
        return read("select * from user_role",
                null);
    }

    @Override
    public List<UserRole> extractData(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        List<UserRole> userRoles = new ArrayList<>();
        while (resultSet.next()) {
            UserRole userRole = new UserRole(resultSet.getInt("id"),
                    resultSet.getString("name"));
            userRoles.add(userRole);
        }
        return userRoles;
    }
}
