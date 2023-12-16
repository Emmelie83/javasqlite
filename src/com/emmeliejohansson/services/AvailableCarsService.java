package com.emmeliejohansson.services;

import com.emmeliejohansson.data.entities.AvailableCar;
import com.emmeliejohansson.repositories.AvailableCarsRepository;

import java.util.ArrayList;

public class AvailableCarsService {

    private UserInputHandler userInputHandler = new UserInputHandler();
    private AvailableCarsRepository availableCarsRepository = new AvailableCarsRepository();

    public void showAllAvailableCars() {
        System.out.println("Insert category name: ");
        String categoryName = userInputHandler.readStringInput();
        System.out.println("Insert start date: ");
        String startDate = userInputHandler.readStringInput();
        System.out.println("Insert end date: ");
        String endDate = userInputHandler.readStringInput();
        showAvailableCars(availableCarsRepository.getAvailableCars(categoryName, startDate, endDate));
    }

    private void showAvailableCars(ArrayList<AvailableCar> availableCars) {
        if (availableCars.isEmpty()) {
            System.out.println("No available car found.");
        }
        for (int i = 0; i < availableCars.size(); i++) {
            AvailableCar aC = availableCars.get(i);
            System.out.println(aC.getRegNr() + "\t" +
                    aC.getBrand() + "\t" +
                    aC.getColor() + "\t" +
                    aC.getCategoryName() + "\t" +
                    aC.getPricePerDay()
            );
        }
    }
}
