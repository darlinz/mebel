/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mebel222.runner;

import java.util.Scanner;
import managers.BuyerManager;
import managers.DatabaseManager;
import managers.OrderManager;
import managers.ProductManager;
import managers.SaleManager;
import managers.UserManager;
import tools.InputProtection;

/**
 *
 * @author rinhu
 */
public class Runner {
    boolean repeat = true;
    private final ProductManager productManager;
    private final Scanner scanner; 
    private final DatabaseManager databaseManager;
    private final UserManager userManager;
    private final BuyerManager buyerManager;
    private final SaleManager saleManager;
    private final OrderManager orderManager;
    public Runner(){
        this.scanner = new Scanner(System.in);
        this.databaseManager = new DatabaseManager();
        this.productManager = new ProductManager(scanner, databaseManager);
        this.buyerManager = new BuyerManager(scanner, databaseManager);
        this.userManager = new UserManager(scanner, databaseManager);
        this.saleManager = new SaleManager(2024, 6, 1, 0, 0);
        this.orderManager = new OrderManager(scanner, databaseManager);
    }
    public void run(){
        do{
        System.out.println("--- divan store ---");
        System.out.println("List taks:");
        System.out.println("0. Exit");
        System.out.println("1. Add new product");
        System.out.println("2. Print list products");
        System.out.println("3. Add new user");
        System.out.println("4. Print list users");
        System.out.println("5. Add money to the buyer");
        System.out.println("6. Edit product");
        System.out.println("7. Edit user");
        System.out.println("8. Show Sale Countdown");
        System.out.println("9. Sell a product");
        System.out.println("10. Store's all-time turnover");
        System.out.print("Enter task number: ");
        int task = InputProtection.intInput(0,14); 
        System.out.printf("You select task %d, for exit press \"0\", to continue press \"1\": ",task);
        int toContinue = InputProtection.intInput(0,1);
        if(toContinue == 0) continue;
          switch (task) {
                case 0:
                    repeat = false;
                    break;
                    
                case 1:
                    productManager.addProduct();
                    break;
                case 2:
                    productManager.printListProducts();
                    break;
                case 3:
                    userManager.addUser();
                    break;
                case 4:
                    userManager.printListUsers();
                    break;
                case 5:
                    buyerManager.addMoney();
                    break;
                case 6:
                    productManager.editProduct();
                    break;
                case 7:
                    userManager.editUser();
                    break;
                case 8:
                    saleManager.printCountdown();
                    break;
                case 9:
                    databaseManager.saveOrder(orderManager.makeOrder());
                    break;
                case 10:
                    orderManager.printListOrdersFiltered();
                    break;
                default: 
                    repeat = false;
                    break; 
                
            }
        }while(repeat);
    }
    
}
