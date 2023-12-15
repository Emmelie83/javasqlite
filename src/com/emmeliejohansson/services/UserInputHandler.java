package com.emmeliejohansson.services;

import java.util.Scanner;

public class UserInputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input: You have to input a number.");
            }
        }
    }

    public static String readStringInput() {
        return scanner.nextLine();
    }
}

