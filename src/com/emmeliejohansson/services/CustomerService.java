package com.emmeliejohansson.services;

import com.emmeliejohansson.data.entities.Customer;
import com.emmeliejohansson.repositories.CustomerRepository;

public class CustomerService {
    private UserInputHandler userInputHandler = new UserInputHandler();
    private CustomerRepository customerRepository = new CustomerRepository();

    public Customer getNewCustomer() {
        System.out.println("First Name:");
        String firstName = userInputHandler.readStringInput();
        System.out.println("Last Name:");
        String lastName = userInputHandler.readStringInput();;
        System.out.println("Street address:");
        String streetAddress = userInputHandler.readStringInput();;
        System.out.println("Postal code:");
        String postalCode = userInputHandler.readStringInput();
        System.out.println("City:");
        String city = userInputHandler.readStringInput();
        System.out.println("Country:");
        String country = userInputHandler.readStringInput();
        System.out.println("Telephone:");
        String telephone = userInputHandler.readStringInput();
        System.out.println("Email:");
        String email = userInputHandler.readStringInput();
        return new Customer(firstName, lastName, streetAddress, postalCode, city, country, telephone, email);

    }

    public void createAndInsertCustomer() {
        Customer customer = getNewCustomer();
        customerRepository.insertCustomer(customer);
    }


    public void updateCustomer() {
        System.out.println("Input customer number for the customer you want to update:");
        int customerId = userInputHandler.readIntInput();
        printUpdateCustomerMenu();
        int selection = userInputHandler.readIntInput();
        switch (selection) {
            case 1:
                updateFirstName(customerId);
                break;
            case 2:
                updateLastName(customerId);
                break;
            case 3:
                updateAddress(customerId);
                break;
            case 4:
                updateTelephone(customerId);
                break;
            case 5:
                updateEmail(customerId);
                break;
        }
    }

    private void printUpdateCustomerMenu() {
        System.out.println("\nWhat do you want to update?:\n");
        System.out.println("0  - Back\n" +
                "1  - First name\n" +
                "2  - Last name\n" +
                "3  - Address\n" +
                "4  - Telephone\n" +
                "5  - Email");
    }

    public void updateFirstName(int customerId) {
        System.out.println("New first name:");
        String firstName = userInputHandler.readStringInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setFirstName(firstName);
            customerRepository.updateCustomer(customer);
        }
    }

    public void updateLastName(int customerId) {
        System.out.println("New last name:");
        String lastName = userInputHandler.readStringInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setLastName(lastName);
            customerRepository.updateCustomer(customer);
        }
    }

    public void updateAddress(int customerId) {
        System.out.println("New street address:");
        String streetAddress = userInputHandler.readStringInput();
        System.out.println("New postal code:");
        String postalCode = userInputHandler.readStringInput();
        System.out.println("New city:");
        String city = userInputHandler.readStringInput();
        System.out.println("New country:");
        String country = userInputHandler.readStringInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setStreetAddress(streetAddress);
            customer.setPostalCode(postalCode);
            customer.setCity(city);
            customer.setCountry(country);
            customerRepository.updateCustomer(customer);
        }
    }

    private void updateTelephone(int customerId) {
        System.out.println("New telephone Number:");
        String telephone = userInputHandler.readStringInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setTelephone(telephone);
            customerRepository.updateCustomer(customer);
        }
    }

    private void updateEmail(int customerId) {
        System.out.println("New email address:");
        String email = userInputHandler.readStringInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setEmail(email);
            customerRepository.updateCustomer(customer);
        }
    }

    public void deleteCustomer(){
        System.out.println("Insert customer number for the customer you want to delete: ");
        int customerId = userInputHandler.readIntInput();
        customerRepository.deleteCustomer(customerId);
    }

    public void showCustomer(){
        System.out.println("Insert phone number for the customer you want to see: ");
        String phone = userInputHandler.readStringInput();
        customerRepository.showCustomer(phone);
    }

    public void markCustomerAsVip() {
        System.out.println("Insert customer number: ");
        int customerNr = userInputHandler.readIntInput();
        customerRepository.markCustomerAsVip(customerNr);
    }
}
