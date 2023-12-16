package com.emmeliejohansson.services;

import java.util.Scanner;

public class UserInputHandler {
    private static Scanner scanner = new Scanner(System.in);


    public static int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You have to enter a number: ");
            }
        }
    }

    public static String readStringInput() {
        return scanner.nextLine();
    }
}

