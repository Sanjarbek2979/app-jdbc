package repository;


import entity.Response;
import entity.Supplier;
import entity.Warehouse;
import utils.DbConfig;

import java.sql.*;

public class WarehouseRepository {


    public static void refreshWarehouses() throws SQLException {
        Database.warehouses.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from warehouse");
        while (resultSet.next()) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(resultSet.getInt(1));
            warehouse.setName(resultSet.getString(2));
            warehouse.setActive(resultSet.getBoolean(3));
            Database.warehouses.add(warehouse);

        }

    }

    public static Response callFunctionAdd(String name) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_warehouse (?,?,?)}");
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
        CallableStatement callableStatement = connection.prepareCall("{call update_warehouse (?,?,?,?)}");
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
        CallableStatement callableStatement = connection.prepareCall("{call delete_warehouse (?,?,?)}");
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
        for (Warehouse warehouse : Database.warehouses) {
            System.out.println(i+") "+warehouse);
            i++;
        }
    }
}
