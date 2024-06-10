/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author rinhu
 */
public class SaleManager {
    private final LocalDateTime targetDateTime;

    public SaleManager(int year, int month, int day, int hour, int minute) {
        // можно установить переменную через константу, но в этом случае используется конструктор 
        this.targetDateTime = LocalDateTime.of(year, month, day, hour, minute);
    }
    public void printCountdown() {
        Duration remainingTime = Duration.between(LocalDateTime.now(), targetDateTime);
        long seconds = remainingTime.getSeconds();

        if (seconds > 0) {
            long days = seconds / (24 * 60 * 60);
            long hours = (seconds % (24 * 60 * 60)) / (60 * 60);
            long minutes = (seconds % (60 * 60)) / 60;

            // Print on the same line
            System.out.println("\r80% off everything in: " + days + " days " + hours + " hours " + minutes + " minutes");
        } else {
            // Print a newline after the countdown is complete
            System.out.println("\rTime until target: 00:00:00:00");
        }
    }
}


