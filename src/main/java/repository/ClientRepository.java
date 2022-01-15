package repository;


import entity.Category;
import entity.Client;
import entity.Response;
import entity.Users;
import utils.DbConfig;

import java.sql.*;

public class ClientRepository {


    public static void refreshClients() throws SQLException {
        Database.clients.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from client");
        while (resultSet.next()) {
            Client client = new Client();
            client.setId(resultSet.getInt(1));
            client.setName(resultSet.getString(2));
            client.setActive(resultSet.getBoolean(3));
            client.setPhone(resultSet.getString(4));
            Database.clients.add(client);
        }
    }

    public static Response callFunctionAdd(String name,String phone) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_client (?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, phone);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.registerOutParameter(4, Types.VARCHAR);

callableStatement.execute();
            response.setSuccess(callableStatement.getBoolean(3));
            response.setMessage(callableStatement.getString(4));

        System.out.println(response);
        return response;

    }
    public static Response callFunctionUpdate(String name, String newName,String phone) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call update_client (?,?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, newName);
        callableStatement.setString(3, phone);
        callableStatement.registerOutParameter(4, Types.BOOLEAN);
        callableStatement.registerOutParameter(5, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(4));
        response.setMessage(callableStatement.getString(5));

        System.out.println(response);
        return response;

    }

    public static Response callFunctionDelete(String name) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call delete_client (?,?,?)}");
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
        for (Client client : Database.clients) {
            System.out.println(i+") "+client);
            i++;
        }
    }
}
