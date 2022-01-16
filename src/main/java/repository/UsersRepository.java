package repository;


import entity.Product;
import entity.Response;
import entity.Users;
import utils.DbConfig;

import java.sql.*;

public class UsersRepository {


    public static void refreshUsers() throws SQLException {
        Database.users.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            Users user = new Users();
            user.setId(resultSet.getInt(1));
            user.setEmail(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setFullName(resultSet.getString(4));
            user.setPhone(resultSet.getString(5));
            user.setActive(resultSet.getBoolean(6));
            Database.users.add(user);

        }

    }

    public static Response callFunctionAdd(String email,String password,String fullName,String phoneNumber) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_user (?,?,?,?,?,?)}");
        callableStatement.setString(1, email);
        callableStatement.setString(2, password);
        callableStatement.setString(3, fullName);
        callableStatement.setString(4, phoneNumber);
        callableStatement.registerOutParameter(5, Types.BOOLEAN);
        callableStatement.registerOutParameter(6, Types.VARCHAR);

callableStatement.execute();
            response.setSuccess(callableStatement.getBoolean(5));
            response.setMessage(callableStatement.getString(6));

        System.out.println(response);
        return response;

    }
    public static Response callFunctionUpdate(String name, String newName,String email,String password) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call update_user (?,?,?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, newName);
        callableStatement.setString(3, email);
        callableStatement.setString(4, password);
        callableStatement.registerOutParameter(5, Types.BOOLEAN);
        callableStatement.registerOutParameter(6, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(5));
        response.setMessage(callableStatement.getString(6));

        System.out.println(response);
        return response;

    }

    public static Response callFunctionDelete(String name) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call delete_user (?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.BOOLEAN);
        callableStatement.registerOutParameter(3, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(2));
        response.setMessage(callableStatement.getString(3));

        System.out.println(response);
        return response;
    }

    public static void callFunctionSelect() {
        int i=1;
        for (Users user : Database.users) {
            System.out.println(i+") "+user);
            i++;
        }
    }
}
