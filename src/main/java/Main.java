import entity.Category;
import entity.Currency;
import entity.Response;
import lombok.SneakyThrows;
import repository.CategoryRepository;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Sanjarbek Allayev, пт 9:06. 14.01.2022
 */
public class Main {

    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    @SneakyThrows
    public static void main(String[] args) {
    runn();
    }

    public static void runn() {
        try {
            CategoryRepository.refreshCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("1.Category CRUD");
        System.out.println("2.Attachment CRUD");
        System.out.println("3.Client CRUD");
        System.out.println("4.Currency CRUD");
        System.out.println("5.Measurement CRUD");
        System.out.println("6.Product CRUD");
        System.out.println("7.Supplier CRUD");
        System.out.println("8.Users CRUD");
        System.out.println("9.Warehouse CRUD");
        System.out.println("0.Exit");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                categoryMenu();
            }
            case 2 -> {
            }
            case 3 -> {
            }
            case 4 -> {
            }
            case 5 -> {
            }
            case 6 -> {
            }
            case 7 -> {
            }
            case 8 -> {
            }
            case 9 -> {
            }
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    public static void categoryMenu() {
        System.out.println("1.Add category");
        System.out.println("2.Show category");
        System.out.println("3.Update category");
        System.out.println("4.Delete category");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter category name: ");
                String name = SCANNER_STR.next();
                try {
                    Response response = CategoryRepository.callFunctionAdd(name);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                CategoryRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter category name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter new category name: ");
                String newName = SCANNER_STR.next();

                try {
                    Response response = CategoryRepository.callFunctionUpdate(name, newName);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter category name: ");
                String name = SCANNER_STR.next();
                try {
                    System.out.println(CategoryRepository.callFunctionDelete(name));
runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }

    }
}
