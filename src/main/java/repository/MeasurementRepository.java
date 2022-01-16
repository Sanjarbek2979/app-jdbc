package repository;


import entity.Currency;
import entity.Measurement;
import entity.Response;
import utils.DbConfig;

import java.sql.*;

public class MeasurementRepository {


    public static void refreshMeasurements() throws SQLException {
        Database.measurements.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from measurement");
        while (resultSet.next()) {
            Measurement measurement = new Measurement();
            measurement.setId(resultSet.getInt(1));
            measurement.setName(resultSet.getString(2));
            measurement.setActive(resultSet.getBoolean(3));
            Database.measurements.add(measurement);

        }

    }

    public static Response callFunctionAdd(String name) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_measurment (?,?,?)}");
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
        CallableStatement callableStatement = connection.prepareCall("{call update_measurement (?,?,?,?)}");
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
        CallableStatement callableStatement = connection.prepareCall("{call delete_measurement (?,?,?)}");
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
        for (Measurement measurement : Database.measurements) {
            System.out.println(i+") "+measurement);
            i++;
        }
    }
}
