package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sanjarbek Allayev, пт 8:54. 14.01.2022
 */
public class DbConfig {

    public static final String dbUser = "postgres"; //user
    public static final String dbPassword = "root123"; //parol
    static String url; //murojaat yo'li
    static String host = "localhost"; //kun.uz //192.168.22.11

    static String dbName = "warehouse_copy";
    static String port = "5432"; //postgres  //oracle //345 //mysql// 123

    public static Connection ulanish()  {
        Connection connection= null;
        url="jdbc:postgresql://" + host+":"+port+"/"+dbName;
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection(url,dbUser,dbPassword);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    return connection;  // failed or success  faqat boolean qiymat qaytaradi
    }


}