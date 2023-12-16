package com.emmeliejohansson.services;

import com.emmeliejohansson.data.entities.Customer;
import com.emmeliejohansson.data.enums.CustomerEnums;
import com.emmeliejohansson.repositories.CustomerRepository;
import java.util.ArrayList;

public class CustomerService {
    private UserInputHandler userInputHandler = new UserInputHandler();
    private CustomerRepository customerRepository = new CustomerRepository();

    //region create

    public void createAndInsertCustomer() {
        Customer customer = createCustomer();
        customerRepository.insertCustomer(customer);
    }
    private Customer createCustomer() {
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

    //endregion


    //region update

    public void updateCustomer() {
        System.out.println("Customer ID for the customer you want to update:");
        int customerId = userInputHandler.readIntInput();
        printUpdateCustomerMenu();
        int selection = userInputHandler.readIntInput();
        switch (selection) {
            case 0 -> {
                return;
            }
            case 1 -> updateFirstName(customerId);
            case 2 -> updateLastName(customerId);
            case 3 -> updateAddress(customerId);
            case 4 -> updateTelephone(customerId);
            case 5 -> updateEmail(customerId);
            default -> System.out.println("You have to enter a number between 1 and 5, or 0 to go back.");
        }
    }

    private void printUpdateCustomerMenu() {
        System.out.println("\nWhat do you want to update?:\n");
        System.out.println(
                "0  - Back\n" +
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
        } else System.out.println("No customer found.");
    }

    public void updateLastName(int customerId) {
        System.out.println("New last name:");
        String lastName = userInputHandler.readStringInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setLastName(lastName);
            customerRepository.updateCustomer(customer);
        } else System.out.println("No customer found.");
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
        } else System.out.println("No customer found.");
    }

    private void updateTelephone(int customerId) {
        System.out.println("New telephone Number:");
        String telephone = userInputHandler.readStringInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setTelephone(telephone);
            customerRepository.updateCustomer(customer);
        } else System.out.println("No customer found.");
    }

    private void updateEmail(int customerId) {
        System.out.println("New email address:");
        String email = userInputHandler.readStringInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setEmail(email);
            customerRepository.updateCustomer(customer);
        } else System.out.println("No customer found.");
    }

    public void markCustomerAsVip() {
        System.out.println("Customer ID:");
        int customerId = userInputHandler.readIntInput();
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            customer.setVip(CustomerEnums.isVip.label);
            customerRepository.updateCustomer(customer);
        } else System.out.println("No customer found.");
    }

    //endregion

    //region show

    public void showCustomerByTelephone(){
        System.out.println("Insert phone number for the customer you want to see: ");
        String phone = userInputHandler.readStringInput();
        showCustomers(customerRepository.getCustomersByTelephone(phone));
    }

    public void showVipCustomers(){
        String vip = CustomerEnums.isVip.label;
        showCustomers(customerRepository.getCustomersByVip(vip));
    }

    public void countVipCustomers() {
        int vipCount = customerRepository.getCustomersByVip(CustomerEnums.isVip.label).size();
        System.out.println("Number of VIP customers: " + vipCount);
    }

    public void showAllCustomers(){
        showCustomers(customerRepository.getAllCustomers());
    }

    private void showCustomers(ArrayList<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("No customer found.");
        }

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println(c.getCustomerId() + "\t" +
                    c.getFirstName() + "\t" +
                    c.getLastName() + "\t" +
                    c.getStreetAddress() + "\t" +
                    c.getPostalCode() + "\t" +
                    c.getCity() + "\t" +
                    c.getCountry() + "\t" +
                    c.getTelephone() + "\t" +
                    c.getEmail() + "\t" +
                    c.getVip()
            );
        }
    }

    //endregion

    public void deleteCustomer(){
        System.out.println("Insert customer number for the customer you want to delete: ");
        int customerId = userInputHandler.readIntInput();
        customerRepository.deleteCustomer(customerId);
    }
}
