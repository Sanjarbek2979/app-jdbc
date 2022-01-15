import entity.Category;
import entity.Currency;
import entity.Response;
import lombok.SneakyThrows;
import repository.AttachmentRepository;
import repository.CategoryRepository;
import repository.ClientRepository;
import repository.CurrencyRepository;

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
        try {
            AttachmentRepository.refreshAttachments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ClientRepository.refreshClients();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            CurrencyRepository.refreshCurrencies();
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
                attachmentMenu();
            }
            case 3 -> {
                clientMenu();
            }
            case 4 -> {
                currencyMenu();
            }
//            case 5 -> {
////                measurementMenu();
//            }
//            case 6 -> {
////                productMenu();
//            }
//            case 7 -> {
////                supplierMenu();
//            }
//            case 8 -> {
////                usersMenu();
//            }
//            case 9 -> {
////                warehouseMenu();
//            }
            case 0 ->
                System.out.println("See you soon bro 😎 " +
                        "O`zizni ehtiyot qilishga majbursiz.");

            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    private static void currencyMenu() {
        System.out.println("1.Add currency");
        System.out.println("2.Show currency");
        System.out.println("3.Update currency");
        System.out.println("4.Delete currency");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter currency name: ");
                String name = SCANNER_STR.next();
                try {
                    Response response = CurrencyRepository.callFunctionAdd(name);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                CurrencyRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter currency name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter new currency name: ");
                String newName = SCANNER_STR.next();

                try {
                    Response response = CurrencyRepository.callFunctionUpdate(name, newName);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter currency name: ");
                String name = SCANNER_STR.next();
                try {
                    System.out.println(CurrencyRepository.callFunctionDelete(name));
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    public static void clientMenu() {
        System.out.println("1.Add client");
        System.out.println("2.Show client");
        System.out.println("3.Update client");
        System.out.println("4.Delete client");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter client name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter phone number: ");
                String phone = SCANNER_STR.next();
                try {
                    Response response = ClientRepository.callFunctionAdd(name,phone);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                ClientRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter client name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter new client name: ");
                String newName = SCANNER_STR.next();
                System.out.println("Enter client phone number: ");
                String phone = SCANNER_STR.next();

                try {
                    Response response = ClientRepository.callFunctionUpdate(name,newName,phone);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter client name: ");
                String name = SCANNER_STR.next();
                try {
                    System.out.println(ClientRepository.callFunctionDelete(name));
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    public static void attachmentMenu() {
        System.out.println("1.Add attachment");
        System.out.println("2.Show attachment");
        System.out.println("3.Update attachment");
        System.out.println("4.Delete attachment");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter attachment name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter attachment size: ");
                Double size = SCANNER_STR.nextDouble();
                System.out.println("Enter attachment content type(For example: .jpg or .png): ");
                String contentType = SCANNER_STR.next();
                try {
                    Response response = AttachmentRepository.callFunctionAdd(name,size,contentType);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                AttachmentRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter attachment name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter new attachment name: ");
                String newName = SCANNER_STR.next();
                System.out.println("Enter attachment size: ");
                Double size = SCANNER_STR.nextDouble();
                System.out.println("Enter attachment content type(For example: .jpg or .png): ");
                String contentType = SCANNER_STR.next();

                try {
                    Response response = AttachmentRepository.callFunctionUpdate(name, newName,size,contentType);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter attachment name: ");
                String name = SCANNER_STR.next();
                try {
                    System.out.println(AttachmentRepository.callFunctionDelete(name));
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
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
