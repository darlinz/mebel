/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Buyer;
import entity.Product;
import entity.User;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author rinhu
 */
public class BuyerManager {
    private final Scanner scanner;
    private final DatabaseManager databaseManager;
    
    public BuyerManager(Scanner scanner, DatabaseManager databaseManager) {
        this.scanner = scanner;
        this.databaseManager = databaseManager;
    }
public Buyer addBuyer(){
    System.out.println("----------Add a Buyer----------");
    Buyer buyer = new Buyer();
    
    System.out.println("Firstname:");
    buyer.setFirstName(scanner.nextLine());
    
    System.out.println("Lastname:");
    buyer.setLastName(scanner.nextLine());
    
    System.out.println("Phone number:");
    buyer.setPhone(scanner.nextLine());
    
    System.out.println("Balance:");
    double buyerBalance = Double.parseDouble(scanner.nextLine());
    buyer.setBalance(buyerBalance);
    
    databaseManager.saveBuyer(buyer);
    return buyer;
}

public User addMoney() {
        System.out.println("----- Enter buyer number -----");
        long buyerNumber = Long.parseLong(scanner.nextLine());
        List<User> users = databaseManager.getUsers();
        User user = new User();
        for(int i = 0;i < users.size(); i++){
            if(users.get(i).getId() == buyerNumber){
                user = users.get(i);
            }
            
        }
        if(Objects.isNull(users)){
            System.out.println("User with such id is not found.");
        }
       
        System.out.println("----- Enter amount of money to add -----");
        double addedMoney = Double.parseDouble(scanner.nextLine());
        user.getBuyer().setBalance(user.getBuyer().getBalance() + addedMoney);


        System.out.println("Buyer balance updated!");
        databaseManager.saveUser(user);
        return user;
}}
