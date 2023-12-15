package com.emmeliejohansson.services;

import com.emmeliejohansson.data.entities.Booking;
import com.emmeliejohansson.data.entities.Customer;
import com.emmeliejohansson.repositories.BookingRepository;

public class BookingService {

    private UserInputHandler userInputHandler = new UserInputHandler();
    private BookingRepository bookingRepository = new BookingRepository();

    public Booking getNewBooking() {
        System.out.println("Booking date (YYYY-MM-DD):");
        String bookingDate = userInputHandler.readStringInput();
        System.out.println("Pickup date (YYYY-MM-DD):");
        String pickupDate = userInputHandler.readStringInput();
        System.out.println("Return date (YYYY-MM-DD):");
        String returnDate = userInputHandler.readStringInput();
        System.out.println("Price per day:");
        int pricePerDay = userInputHandler.readIntInput();
        System.out.println("Car registration number:");
        String carRegNr = userInputHandler.readStringInput();
        System.out.println("Customer ID:");
        int customerId = userInputHandler.readIntInput();
        return new Booking(bookingDate, pickupDate, returnDate, pricePerDay, customerId, carRegNr);
    }

    public void createAndInsertBooking() {
        Booking booking = getNewBooking();
        bookingRepository.insertBooking(booking);
    }


    public void updateBooking() {
        System.out.println("Input booking number for the booking you want to update:");
        int bookingNr = userInputHandler.readIntInput();
        printUpdateBookingMenu();
        int selection = userInputHandler.readIntInput();
        switch (selection) {
            case 1:
                updatePickupDate(bookingNr);
                break;
            case 2:
                updateReturnDate(bookingNr);
                break;
            case 3:
                updatePricePerDay(bookingNr);
                break;
            case 4:
                updateCarRegNr(bookingNr);
                break;
            case 0:
                return;
        }
    }


    private void printUpdateBookingMenu() {
        System.out.println("\nWhat do you want to update?:\n");
        System.out.println("0  - Back\n" +
                "1  - Pickup date\n" +
                "2  - Return date\n" +
                "3  - Price per day\n" +
                "4  - Car\n");
    }

    private void updatePickupDate(int bookingId) {
        System.out.println("New pickup date:");
        String pickupDate = userInputHandler.readStringInput();
        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking != null) {
            booking.setPickupDate(pickupDate);
            bookingRepository.updateBooking(booking);
        }
    }

    public void updateReturnDate(int bookingId) {
        System.out.println("New first name:");
        String returnDate = userInputHandler.readStringInput();
        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking != null) {
            booking.setReturnDate(returnDate);
            bookingRepository.updateBooking(booking);
        }
    }


    private void updatePricePerDay(int bookingId) {
        System.out.println("New price per day:");
        int pricePerDay = userInputHandler.readIntInput();
        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking != null) {
            booking.setPricePerDay(pricePerDay);
            bookingRepository.updateBooking(booking);
        }
    }

    private void updateCarRegNr(int bookingId) {
        System.out.println("New car registration number:");
        String carRegNr = userInputHandler.readStringInput();
        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking != null) {
            booking.setCarRegNr(carRegNr);
            bookingRepository.updateBooking(booking);
        }
    }

    public void deleteBooking(){
        System.out.println("Insert booking ID for the booking you want to delete: ");
        int bookingId = userInputHandler.readIntInput();
        bookingRepository.deleteBooking(bookingId);
    }
}
