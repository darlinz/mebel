/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Product;
import entity.User;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author rinhu
 */

public class ProductManager {
    private final Scanner scanner;
    private final DatabaseManager databaseManager;
    
    public ProductManager(Scanner scanner, DatabaseManager databaseManager) {
        this.scanner = scanner;
        this.databaseManager = databaseManager;
}


    
public Product addProduct(){
    System.out.println("----- Add a Product -----");
    Product product = new Product();
    System.out.print("Enter product name: ");
    product.setName(scanner.nextLine());

    System.out.print("Enter price in euros: ");
    double productPrice = Double.parseDouble(scanner.nextLine());
    product.setPrice(productPrice);


    System.out.print("Enter manufacture year: ");
    short year = Short.parseShort(scanner.nextLine());
    product.setYear(year);
    
    System.out.print("Enter color: ");
    product.setColor(scanner.nextLine());
    
    System.out.print("Enter quantity: ");
    int productQuantity = Integer.parseInt(scanner.nextLine());
    product.setQuantity(productQuantity);
    
    databaseManager.saveProduct(product);
    return product;
}

public void printListProducts(){
    
    System.out.println("----- List of all Products -----");
    List<Product> products = databaseManager.getProducts();
    for (int i = 0;i < products.size(); i++){
        /*System.out.println(
            String.format("%n. %s. %s. %s. %n."
                ,products.get(i).getId()
                ,products.get(i).getName()
                ,products.get(i).getColor()
                ,products.get(i).getPrice()
                ,products.get(i).getYear()
            ));*/  
        System.out.println(products.get(i));
    };
}


public void editProduct(){
        System.out.println("----- List of all Products -----");
        
        List<Product> products = databaseManager.getProducts();
        Product product = new Product();
        for (int i = 0;i < products.size(); i++){
            System.out.println(products.get(i));
            
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }}
        System.out.println("----- Enter product number -----");
        long productNumber = Long.parseLong(scanner.nextLine());
        int n = 0;
        
        for(int j = 0;j < products.size(); j++){
            if(products.get(j).getId() == productNumber){
                product = products.get(j);
            }
            
        }
        System.out.println("Current name: " + product.getName());
        System.out.print("Enter new name (or press Enter to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            product.setName(newName);
        }
        
        System.out.println("Current price: " + product.getPrice());
        System.out.print("Enter new price (or press Enter to keep current): ");
        String newPriceString = scanner.nextLine();
        if (!newPriceString.isEmpty()) {
            product.setPrice(Long.parseLong(newPriceString));
        }
        
        System.out.println("Current quantity: " + product.getQuantity());
        System.out.print("Enter new quantity (or press Enter to keep current): ");
        String newQuantity = scanner.nextLine();
        if (!newQuantity.isEmpty()) {
            product.setQuantity(Integer.parseInt(newQuantity));
        }
        
        System.out.println("Current color: " + product.getColor());
        System.out.print("Enter new color (or press Enter to keep current): ");
        String newColor = scanner.nextLine();
        if (!newColor.isEmpty()) {
            product.setColor(newColor);
        }
        
        System.out.println("Current year: " + product.getYear());
        System.out.print("Enter new year (or press Enter to keep current): ");
        String newYearString = scanner.nextLine();
        if (!newYearString.isEmpty()) {
            product.setYear(Short.parseShort(newYearString));
        }
        databaseManager.saveProduct(product);
}
}
