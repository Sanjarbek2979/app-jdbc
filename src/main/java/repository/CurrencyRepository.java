package repository;


import entity.Category;
import entity.Currency;
import entity.Response;
import utils.DbConfig;

import java.sql.*;

public class CurrencyRepository {


    public static void refreshCurrencies() throws SQLException {
        Database.currencies.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select *from currency");
        while (resultSet.next()) {
            Currency currency = new Currency();
            currency.setId(resultSet.getInt(1));
            currency.setName(resultSet.getString(2));
            currency.setActive(resultSet.getBoolean(3));
            Database.currencies.add(currency);

        }

    }

    public static Response callFunctionAdd(String name) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_currency (?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.BOOLEAN);
        callableStatement.registerOutParameter(3, Types.VARCHAR);

callableStatement.execute();
            response.setSuccess(callableStatement.getBoolean(2));
            response.setMessage(callableStatement.getString(3));

        System.out.println(response);
        return response;

    }
    public static Response callFunctionUpdate(String name, String newName) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call update_currency (?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, newName);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.registerOutParameter(4, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(3));
        response.setMessage(callableStatement.getString(4));

        System.out.println(response);
        return response;

    }

    public static Response callFunctionDelete(String name) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call delete_currency (?,?,?)}");
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
        for (Currency currency : Database.currencies) {
            System.out.println(i+") "+currency);
            i++;
        }
    }
}
