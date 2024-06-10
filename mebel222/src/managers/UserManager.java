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
import java.util.Scanner;
import tools.PassEncrypt;

/**
 *
 * @author rinhu
 */
public class UserManager {
    private final Scanner scanner;
    private final DatabaseManager databaseManager;
    
    public UserManager(Scanner scanner,DatabaseManager databaseManager) {
        this.scanner = scanner;
        this.databaseManager = databaseManager;
    }
    
    public User addUser(){
        Buyer buyer = new Buyer();
        System.out.println("Firstname:");
        buyer.setFirstName(scanner.nextLine());
    
        System.out.println("Lastname:");
        buyer.setLastName(scanner.nextLine());
    
        System.out.println("Phone number:");
        buyer.setPhone(scanner.nextLine());
    
        System.out.println("Balance:");
        double buyerBalance = scanner.nextDouble();
        buyer.setBalance(buyerBalance);
        
        User user = new User();
        System.out.print("Login: ");
        user.setLogin(scanner.nextLine());
        System.out.print("Password: ");
        PassEncrypt pe = new PassEncrypt();
        user.setPassword(pe.getEncryptPassword(scanner.nextLine().trim(),pe.getSalt()));
        
        user.setBuyer(buyer);
        System.out.println("New buyer added!");
        databaseManager.saveUser(user);
        return user;
        
    }
    public void printListUsers(){
        int n = 0;
        System.out.println("----- List of all Users -----");
        List<User> users = databaseManager.getUsers();
        for (int i = 0;i < users.size(); i++){
            System.out.println(users.get(i));
    }
}
    public void editUser(){
    printListUsers();
    
    
    List<User> users = databaseManager.getUsers();
        User user = new User();
        //for (int i = 0;i < users.size(); i++){
        //    System.out.println(users.get(i));
    //}
    if (user == null) {
        System.out.println("User not found.");
        return;
    }  
    System.out.println("----- Enter user number -----");
        long userNumber = Long.parseLong(scanner.nextLine());
        int n = 0;
        
        for(int j = 0;j < users.size(); j++){
            if(users.get(j).getId() == userNumber){
                user = users.get(j);
            }
        }
        System.out.println("Current login: " + user.getLogin());
        System.out.print("Enter new login (or press Enter to keep current): ");
        String newLogin = scanner.nextLine();
        if (!newLogin.isEmpty()) {
            user.setLogin(newLogin);
        }
        
        System.out.println("Current password: " + user.getPassword());
        System.out.print("Enter new login (or press Enter to keep current): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }
        databaseManager.saveUser(user);
        
        

}}
