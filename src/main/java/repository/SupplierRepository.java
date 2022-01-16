package repository;


import entity.Measurement;
import entity.Response;
import entity.Supplier;
import utils.DbConfig;

import java.sql.*;

public class SupplierRepository {


    public static void refreshSuppliers() throws SQLException {
        Database.suppliers.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from supplier");
        while (resultSet.next()) {
            Supplier supplier = new Supplier();
            supplier.setId(resultSet.getInt(1));
            supplier.setName(resultSet.getString(2));
            supplier.setActive(resultSet.getBoolean(3));
            Database.suppliers.add(supplier);

        }

    }

    public static Response callFunctionAdd(String name) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_supplier (?,?,?)}");
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
        CallableStatement callableStatement = connection.prepareCall("{call update_supplier (?,?,?,?)}");
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
        CallableStatement callableStatement = connection.prepareCall("{call delete_supplier (?,?,?)}");
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
        for (Supplier supplier : Database.suppliers) {
            System.out.println(i+") "+supplier);
            i++;
        }
    }
}
