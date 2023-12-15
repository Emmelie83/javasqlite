package com.emmeliejohansson.services;

import com.emmeliejohansson.repositories.AvailableCarsRepository;

public class AvailableCarsService {

    private UserInputHandler userInputHandler = new UserInputHandler();
    private AvailableCarsRepository availableCarsRepository = new AvailableCarsRepository();

    public void showAvailableCars() {
        System.out.println("Insert category name: ");
        String categoryName = userInputHandler.readStringInput();
        System.out.println("Insert start date: ");
        String startDate = userInputHandler.readStringInput();
        System.out.println("Insert end date: ");
        String endDate = userInputHandler.readStringInput();
       availableCarsRepository.showAvailableCars(categoryName, startDate, endDate);
    }

}
