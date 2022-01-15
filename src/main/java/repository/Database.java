package repository;

import entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanjarbek Allayev, пт 16:42. 14.01.2022
 */
public class Database {

    public static List<Attachment> attachments = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();
    public static List<Client> clients = new ArrayList<>();
    public static List<Currency> currencies = new ArrayList<>();
    public static List<Measurement> measurements = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Supplier> suppliers = new ArrayList<>();
    public static List<Users> users = new ArrayList<>();
    public static List<Warehouse> warehouses = new ArrayList<>();
}
