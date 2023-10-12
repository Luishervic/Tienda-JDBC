package org.tienda.app;

import org.tienda.dao.ProductDAOImpl;
import org.tienda.model.Manufacturer;
import org.tienda.model.Product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final ProductDAOImpl productDAO = new ProductDAOImpl();
    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args){
        int option;
        do {
            printMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    listAllProductsMenu();
                    break;
                case 2:
                    listAllProductsAndPricesMenu();
                    break;
                case 3:
                    listBetweenRangesMenu();
                    break;
                case 4:
                    listAllLaptopsMenu();
                    break;
                case 5:
                    findChepestProductMenu();
                    break;
                case 6:
                    findProductByCodeMenu();
                    break;
                case 7:
                    insertProductMenu();
                    break;
                case 8:
                    insertManufacturerMenu();
                    break;
                case 9:
                    updateProductMenu();
                    break;
                case 10:
                    System.out.println("Exit.");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while(option != 10);
    }

    public static void printMenu() {
        System.out.println("1. List all the products.");
        System.out.println("2. List all the products with prices.");
        System.out.println("3. List all the products between ranges.");
        System.out.println("4. List all the laptops.");
        System.out.println("5. Find the cheapest product.");
        System.out.println("6. Find a product by code.");
        System.out.println("7. Insert a product.");
        System.out.println("8. Insert a manufacturer.");
        System.out.println("9. Update a product.");
        System.out.println("10. Exit.");
        System.out.println("Choose an option: ");
    }

    public static void listAllProductsMenu() {
        System.out.println("List all the products.");
        List<String> allProducts= productDAO.findAllNameProducts();

        for (String products: allProducts) {
            System.out.println(products);
        }
    }

    public static void listAllProductsAndPricesMenu() {
        System.out.println("List all the products with prices.");
        Map<String, Double> allProducts= productDAO.priceAndNameProducts();

        for (Map.Entry<String, Double> entry: allProducts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void listBetweenRangesMenu() {
        System.out.println("List all the products between ranges.");
        double lowerLimit = getUserInputDouble("Enter the lower limit: ");
        double upperLimit = getUserInputDouble("Enter the upper limit: ");
        List<Product> products = productDAO.listBetweenRanges(lowerLimit, upperLimit);
        printProducts(products);
    }

    public static void listAllLaptopsMenu() {
        System.out.println("List all the laptops.");
        List<Product> products = productDAO.listAllLaptops();
        printProducts(products);
    }

    public static void findChepestProductMenu() {
        System.out.println("Find the cheapest product.");
        Product product = productDAO.findChepestProduct();
        System.out.println(product);
    }

    public static void findProductByCodeMenu() {
        System.out.println("Find a product by code.");
        int code = getUserInputInt("Enter the code: ");
        Product product = productDAO.findProductByCode(code);
        System.out.println(product);
    }

    public static void insertProductMenu() {
        System.out.println("Insert a product.");
        Product product = fillProduct();
        productDAO.insertProduct(product);
        System.out.println("Product inserted successfully.");
    }

    public static void insertManufacturerMenu()  {
        Manufacturer manufacturer = fillManufacurer();
        productDAO.insertManufacturer(manufacturer);
        System.out.println("Manufacturer inserted successfully.");
    }

    public static void updateProductMenu() {
        System.out.println("Update a product.");
        Product product = fillProduct();
        productDAO.updateProduct(product);
        System.out.println("Product updated successfully.");
    }

    public static void printProducts(List<Product> products) {
        for (Product product: products) {
            System.out.println(product);
        }
    }

    public static Manufacturer fillManufacurer(){
        int codeManufacturer = getUserInputInt("Enter the code: ");
        String nameManufacturer = getUserInputString("Enter the name: ");
        return new Manufacturer(codeManufacturer, nameManufacturer);
    }

    public static Product fillProduct(){
        int codeProduct = getUserInputInt("Enter the code: ");
        String nameProduct = getUserInputString("Enter the name: ");
        double priceProduct = getUserInputDouble("Enter the price: ");
        int codeManufacturer = getUserInputInt("Enter the code of the manufacturer: ");
        return new Product(codeProduct, nameProduct, priceProduct, codeManufacturer);
    }

    public static int getUserInputInt(String message) {
        System.out.println(message);
        while (true) {
            try {
                int userInput = scanner.nextInt();
                if (userInput > 0 && userInput < 500_000) {
                    return userInput;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
    }

    public static double getUserInputDouble(String message) {
        System.out.println(message);
        while (true) {
            try {
                double userInput = scanner.nextDouble();
                if (userInput > 0 && userInput < 500_000) {
                    return userInput;
                } else {
                    System.out.println("Invalid input. Please enter a positive double.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an double value.");
                scanner.next();
            }
        }
    }

    public static String getUserInputString(String message) {
        System.out.println(message);
        while (true) {
            try {
                String userInput = scanner.next();
                if (userInput.length()>5 && userInput.length()<50) {
                    return userInput;
                } else {
                    System.out.println("Invalid input. Please enter a string with more than 5 characters.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.next();
            }
        }
    }
}
