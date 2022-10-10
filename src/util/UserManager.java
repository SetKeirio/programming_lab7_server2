package util;

import messages.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    private DatabaseManager dbManager;


    public UserManager(DatabaseManager dbManager){
        this.dbManager = dbManager;
    }

    public User getUserById(int userId) throws SQLException {
        PreparedStatement selectUser = null;
        User user = null;
        try {
            selectUser = dbManager.getStatement(Data.USER_BY_ID, false);
            selectUser.setInt(1, userId);
            ResultSet resultSet = selectUser.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString(Data.USERS_USERNAME), resultSet.getString(Data.USERS_PASSWORD));
            } else{
                throw new SQLException();
            }
            dbManager.closeStatement(selectUser);
        } catch (SQLException e) {
            Console.printerr("Не получилось найти пользователя по id!");
            dbManager.closeStatement(selectUser);
            throw new SQLException(e);
        }
        return user;
    }

    public int getUserId(User user) throws SQLException {
        PreparedStatement selectUser = null;
        int answer = -1;
        try {
            selectUser = dbManager.getStatement(Data.USER_BY_LOGIN, false);
            selectUser.setString(1, user.getUsername());
            ResultSet resultSet = selectUser.executeQuery();
            if (resultSet.next()) {
                answer = resultSet.getInt(Data.USERS_ID);
            } else{
                throw new SQLException();
            }
            dbManager.closeStatement(selectUser);
        } catch (SQLException e) {
            Console.printerr("Не получилось найти пользователя по такому логину!");
            dbManager.closeStatement(selectUser);
            throw new SQLException(e);
        }
        return answer;
    }

    public byte addUser(User user) throws SQLException{
        PreparedStatement addUser = null;
        try{
            if (getUserId(user) <= 0){
                return 1;
            }
            addUser = dbManager.getStatement(Data.ADD_USER, false);
            addUser.setString(1, user.getUsername());
            addUser.setString(2, user.getPassword());
            if (addUser.executeUpdate() == 0){
                throw new SQLException();
            }
            Console.println("Пользователь добавлен, username=" + user.getUsername() + ", password=" + user.getPassword());
            dbManager.closeStatement(addUser);
            return 0;
        } catch (SQLException e) {
            Console.printerr("Не удалось добавить пользователя!");
            dbManager.closeStatement(addUser);
            throw new SQLException(e);
        }
    }

    public boolean loginAndPasswordCheck(User user) throws SQLException{
        PreparedStatement loginUser = null;
        try{
            loginUser = dbManager.getStatement(Data.USER_BY_DATA, false);
            loginUser.setString(1, user.getUsername());
            loginUser.setString(2, user.getPassword());
            ResultSet resultSet = loginUser.executeQuery();
            dbManager.closeStatement(loginUser);
            return (resultSet.next());

        }
        catch (SQLException e) {
            Console.printerr("Не удалось найти пользователя с таким логином и паролем!");
            dbManager.closeStatement(loginUser);
            throw new SQLException(e);
        }
    }
}
