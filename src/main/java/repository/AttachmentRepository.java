package repository;


import entity.Attachment;
import entity.Response;
import utils.DbConfig;

import java.sql.*;

public class AttachmentRepository {


    public static void refreshAttachments() throws SQLException {
        Database.attachments.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select *from attachment");
        while (resultSet.next()) {
            Attachment attachment = new Attachment();
            attachment.setId(resultSet.getInt(1));
            attachment.setName(resultSet.getString(2));
            attachment.setSize(resultSet.getDouble(3));
            attachment.setContentType(resultSet.getString(4));
            Database.attachments.add(attachment);

        }

    }

    public static Response callFunctionAdd(String name,Double size,String contentType) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_attachment (?,?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setDouble(2, size);
        callableStatement.setString(3, contentType);
        callableStatement.registerOutParameter(4, Types.BOOLEAN);
        callableStatement.registerOutParameter(5, Types.VARCHAR);

callableStatement.execute();
            response.setSuccess(callableStatement.getBoolean(4));
            response.setMessage(callableStatement.getString(5));

        System.out.println(response);
        return response;

    }
    public static Response callFunctionUpdate(String name, String newName,Double size,String contentType) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call update_attachment (?,?,?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, newName);
        callableStatement.setDouble(3, size);
        callableStatement.setString(4, contentType);
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
        CallableStatement callableStatement = connection.prepareCall("{call delete_attachment (?,?,?)}");
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
        for (Attachment attachment : Database.attachments) {
            System.out.println(i+") "+attachment);
            i++;
        }
    }
}
