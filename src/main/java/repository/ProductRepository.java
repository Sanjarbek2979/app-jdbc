package repository;


import entity.Product;
import entity.Response;
import entity.Warehouse;
import utils.DbConfig;

import java.sql.*;

public class ProductRepository {


    public static void refreshProducts() throws SQLException {
        Database.products.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product");
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setActive(resultSet.getBoolean(3));
            product.setCategoryId(resultSet.getInt(4));
            product.setMeasurementId(resultSet.getInt(5));
            product.setAttachmentId(resultSet.getInt(6));
            Database.products.add(product);

        }

    }

    public static Response callFunctionAdd(String name,int categoryId,int measurementId,int attachmentId) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_product (?,?,?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setInt(2, categoryId);
        callableStatement.setInt(3, measurementId);
        callableStatement.setInt(4, attachmentId);
        callableStatement.registerOutParameter(5, Types.BOOLEAN);
        callableStatement.registerOutParameter(6, Types.VARCHAR);

callableStatement.execute();
            response.setSuccess(callableStatement.getBoolean(5));
            response.setMessage(callableStatement.getString(6));

        System.out.println(response);
        return response;

    }
    public static Response callFunctionUpdate(String name, String newName,int categoryId,int measurementId,int attachmentId) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call update_product (?,?,?,?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, newName);
        callableStatement.setInt(3, categoryId);
        callableStatement.setInt(4, measurementId);
        callableStatement.setInt(5, attachmentId);
        callableStatement.registerOutParameter(6, Types.BOOLEAN);
        callableStatement.registerOutParameter(7, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(6));
        response.setMessage(callableStatement.getString(7));

        System.out.println(response);
        return response;

    }

    public static Response callFunctionDelete(String name) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call delete_product (?,?,?)}");
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
        for (Product product : Database.products) {
            System.out.println(i+") "+product);
            i++;
        }
    }
}
