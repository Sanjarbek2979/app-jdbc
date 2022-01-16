import entity.Response;
import lombok.SneakyThrows;
import repository.*;
import service.Services;

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
        try {
            MeasurementRepository.refreshMeasurements();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            SupplierRepository.refreshSuppliers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            WarehouseRepository.refreshWarehouses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ProductRepository.refreshProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            UsersRepository.refreshUsers();
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
            case 5 -> {
                measurementMenu();
            }
            case 6 -> {
                productMenu();
            }
            case 7 -> {
                supplierMenu();
            }
            case 8 -> {
                usersMenu();
            }
            case 9 -> {
                warehouseMenu();
            }
            case 0 -> System.out.println("See you soon bro 😎 " +
                    "O`zizni ehtiyot qilishga majbursiz.");

            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    private static void usersMenu() {
        System.out.println("1.Add user");
        System.out.println("2.Show user");
        System.out.println("3.Update user");
        System.out.println("4.Delete user");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter user's email: ");
                String email = SCANNER_STR.next();

                System.out.println("Enter user's password: ");
                String password = SCANNER_STR.next();

                System.out.println("Enter user's phone number: ");
                String phone = SCANNER_STR.next();

                System.out.println("Enter user's fullName: ");
                String fullName = SCANNER_STR.next();

                try {
                    Response response = UsersRepository.callFunctionAdd(email, password, fullName, phone);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                UsersRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter user's fullname: ");
                String fullname = new Scanner(System.in).nextLine();
                System.out.println("Enter user's new  fullname: ");
                String newFullname = SCANNER_STR.next();
                System.out.println("Enter user's new email: ");
                String email = SCANNER_STR.next();
                System.out.println("Enter user's new  password: ");
                String password = SCANNER_STR.next();

                try {
                    Response response = UsersRepository.callFunctionUpdate(fullname, newFullname, email, password);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter user's fullname: ");
                String fullName = new Scanner(System.in).nextLine();
                try {
                    System.out.println(UsersRepository.callFunctionDelete(fullName));
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    private static void productMenu() {
        System.out.println("1.Add product");
        System.out.println("2.Show product");
        System.out.println("3.Update product");
        System.out.println("4.Delete product");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter product name: ");
                String name = SCANNER_STR.next();
                Services.showAnyList(Database.categories);
                System.out.println("Enter category id: ");
                Integer categoryId = SCANNER_STR.nextInt();
                Services.showAnyList(Database.measurements);
                System.out.println("Enter measurement id: ");
                Integer measurementId = SCANNER_STR.nextInt();
                Services.showAnyList(Database.attachments);
                System.out.println("Enter attachment id: ");
                Integer attachmentId = SCANNER_STR.nextInt();

                try {
                    Response response = ProductRepository.callFunctionAdd(name, categoryId, measurementId, attachmentId);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                ProductRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter product name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter new product name: ");
                String newName = SCANNER_STR.next();
                Services.showAnyList(Database.categories);
                System.out.println("Enter category id: ");
                int categoryId = SCANNER_STR.nextInt();
                Services.showAnyList(Database.measurements);
                System.out.println("Enter measurement id: ");
                int measurementId = SCANNER_STR.nextInt();
                Services.showAnyList(Database.attachments);
                System.out.println("Enter attachment id: ");
                int attachmentId = SCANNER_STR.nextInt();

                try {
                    Response response = ProductRepository.callFunctionUpdate(name, newName, categoryId, measurementId, attachmentId);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter product name: ");
                String name = SCANNER_STR.next();
                try {
                    System.out.println(ProductRepository.callFunctionDelete(name));
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    private static void warehouseMenu() {
        System.out.println("1.Add warehouse");
        System.out.println("2.Show warehouse");
        System.out.println("3.Update warehouse");
        System.out.println("4.Delete warehouse");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter warehouse name: ");
                String name = SCANNER_STR.next();
                try {
                    Response response = WarehouseRepository.callFunctionAdd(name);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                WarehouseRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter warehouse name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter new warehouse name: ");
                String newName = SCANNER_STR.next();

                try {
                    Response response = WarehouseRepository.callFunctionUpdate(name, newName);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter warehouse name: ");
                String name = SCANNER_STR.next();
                try {
                    System.out.println(WarehouseRepository.callFunctionDelete(name));
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    private static void supplierMenu() {
        System.out.println("1.Add supplier");
        System.out.println("2.Show supplier");
        System.out.println("3.Update supplier");
        System.out.println("4.Delete supplier");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter supplier name: ");
                String name = SCANNER_STR.next();
                try {
                    Response response = SupplierRepository.callFunctionAdd(name);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                SupplierRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter supplier name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter new supplier name: ");
                String newName = SCANNER_STR.next();

                try {
                    Response response = SupplierRepository.callFunctionUpdate(name, newName);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter supplier name: ");
                String name = SCANNER_STR.next();
                try {
                    System.out.println(SupplierRepository.callFunctionDelete(name));
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas aka🤨🤨🤨");
        }
    }

    private static void measurementMenu() {
        System.out.println("1.Add measurement");
        System.out.println("2.Show measurement");
        System.out.println("3.Update measurement");
        System.out.println("4.Delete measurement");
        System.out.println("0.Back");
        System.out.print("Select: ");
        int n = SCANNER_NUM.nextInt();
        switch (n) {
            case 1 -> {
                System.out.println("Enter measurement name: ");
                String name = SCANNER_STR.next();
                try {
                    Response response = MeasurementRepository.callFunctionAdd(name);
                    System.out.println(response);
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                MeasurementRepository.callFunctionSelect();
                runn();
            }
            case 3 -> {
                System.out.println("Enter measurement name: ");
                String name = SCANNER_STR.next();
                System.out.println("Enter new measurement name: ");
                String newName = SCANNER_STR.next();

                try {
                    Response response = MeasurementRepository.callFunctionUpdate(name, newName);
                    System.out.println(response);
                    runn();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Enter measurement name: ");
                String name = SCANNER_STR.next();
                try {
                    System.out.println(MeasurementRepository.callFunctionDelete(name));
                    runn();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 0 -> runn();
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
                    Response response = ClientRepository.callFunctionAdd(name, phone);
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
                    Response response = ClientRepository.callFunctionUpdate(name, newName, phone);
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
                    Response response = AttachmentRepository.callFunctionAdd(name, size, contentType);
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
                    Response response = AttachmentRepository.callFunctionUpdate(name, newName, size, contentType);
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
