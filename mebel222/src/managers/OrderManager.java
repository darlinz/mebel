/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Buyer;
import entity.Order;
import entity.Product;
import entity.User;
import java.util.Date;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import tools.InputProtection;

/**
 *
 * @author rinhu
 */
public class OrderManager {
    private final Scanner scanner;
    private final DatabaseManager databaseManager;
    
    public OrderManager(Scanner scanner, DatabaseManager databaseManager) {
        this.scanner = scanner;
        this.databaseManager = databaseManager;
    }
    
    
public Order makeOrder() {
    
    Order order = new Order();
    
    System.out.print("Enter the number of the client: ");
        List<User> users = databaseManager.getUsers();
        int userNumber = InputProtection.intInput(1, users.size());
        User user = new User();
        Buyer buyer = new Buyer();
        for (int i = 0;i < users.size(); i++){
            if(users.get(i).getId() == userNumber){
                user = users.get(i);
                buyer = user.getBuyer();
            }
            else{
                System.out.println("User not found. Try again.");
                return order;
            }
        }
        List<Product> products = databaseManager.getProducts();
        System.out.print("Enter the number of desired product: ");
        int productNumber = InputProtection.intInput(1, products.size());
        Product selectedProduct = databaseManager.getProduct((long)productNumber);
        
        order.setProduct(selectedProduct);
        order.setUser(user);
        
        System.out.print("Enter the number of units sold: ");
        int unitsSold = InputProtection.intInput(1, Integer.MAX_VALUE);
        order.setUnitsSold(unitsSold);
        
        order.setCreationDate(new GregorianCalendar().getTime());

        selectedProduct.setQuantity(selectedProduct.getQuantity() - unitsSold);
        user.getBuyer().setBalance(user.getBuyer().getBalance() - selectedProduct.getPrice()*unitsSold);
        user.getBuyer().setProductsBought(user.getBuyer().getProductsBought()+unitsSold);
        return order;
        
}
public void printListOrdersFiltered() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select filter option:");
        System.out.println("1. All time");
        System.out.println("2. Specific year");
        System.out.println("3. Specific month of a year");
        System.out.println("4. Specific day of a month of a year");
        System.out.print("Enter your choice: ");
        
       List <Order> orders = databaseManager.getListOrders();
        
        int choice = scanner.nextInt();
        
         switch (choice) {
            case 1:
                orders = databaseManager.getListOrders();
                printListOrders(orders);
                
                break;
}}    

    private void printListOrders(List<Order> orders) {
        
    }
}     
       
