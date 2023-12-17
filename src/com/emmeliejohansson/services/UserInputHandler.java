package com.emmeliejohansson.services;

import java.util.Scanner;

public class UserInputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public int readMenuInput(int maxMenuSelection) {
        int selection = readIntInput();
        while (selection < 0 || selection > maxMenuSelection) {
            System.out.println("You have to enter a number between 0 and " + maxMenuSelection);
        }
        return selection;
    }

    public int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You have to enter a number: ");
            }
        }
    }

    public String readStringInput() {
        return scanner.nextLine();
    }
}

