package com.insurance;

import com.insurance.dao.CustomerDAO;
import com.insurance.dao.PolicyTypeDAO;
import com.insurance.dao.ClaimDAO;
import com.insurance.model.Customer;
import com.insurance.model.PolicyType;
import com.insurance.model.Claim;
import com.insurance.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final PolicyTypeDAO policyTypeDAO = new PolicyTypeDAO();
    private static final ClaimDAO claimDAO = new ClaimDAO();

    public static void main(String[] args) {
        System.out.println("=== Insurance Management System ===");
        
        // test database connection first
        if (!DBConnection.testConnection()) {
            System.err.println("Database connection failed. Please check your configuration.");
            return;
        }
        
        System.out.println("Database connection successful!");
        
        // main program loop
        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        createCustomer();
                        break;
                    case 2:
                        viewAllCustomers();
                        break;
                    case 3:
                        findCustomerById();
                        break;
                    case 4:
                        createPolicyType();
                        break;
                    case 5:
                        viewAllPolicyTypes();
                        break;
                    case 6:
                        createClaim();
                        break;
                    case 7:
                        viewClaimsByStatus();
                        break;
                    case 8:
                        updateClaimStatus();
                        break;
                    case 0:
                        System.out.println("Thank you for using Insurance Management System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private static void printMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Find Customer by ID");
        System.out.println("4. Create Policy Type");
        System.out.println("5. View All Policy Types");
        System.out.println("6. Create Claim");
        System.out.println("7. View Claims by Status"); 
        System.out.println("8. Update Claim Status");
        System.out.println("0. Exit");
        System.out.println("==================");
    }
    
    private static void createCustomer() throws SQLException {
        System.out.println("\n=== Create New Customer ===");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        Date dob = Date.valueOf(scanner.nextLine());
        
        System.out.print("Contact Number: ");
        long contact = Long.parseLong(scanner.nextLine());
        
        System.out.print("Address: ");
        String address = scanner.nextLine();
        
        System.out.print("City: ");
        String city = scanner.nextLine();
        
        System.out.print("State: ");
        String state = scanner.nextLine();
        
        System.out.print("Postal Code: ");
        String postalcode = scanner.nextLine();
        
        System.out.print("Country: ");
        String country = scanner.nextLine();
        
        System.out.print("Gender (M/F/Other): ");
        String gender = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("National ID: ");
        String nationalid = scanner.nextLine();
        
        System.out.print("Background: ");
        String background = scanner.nextLine();
        
        Customer customer = new Customer(name, dob, contact, address, city, state, 
                                       postalcode, country, gender, email, nationalid, background);
        
        long customerId = customerDAO.createCustomer(customer);
        System.out.println("Customer created successfully with ID: " + customerId);
    }
    
    private static void viewAllCustomers() throws SQLException {
        System.out.println("\n=== All Customers ===");
        List<Customer> customers = customerDAO.getAllCustomers();
        
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
    
    private static void findCustomerById() throws SQLException {
        System.out.println("\n=== Find Customer by ID ===");
        long customerId = getLongInput("Enter Customer ID: ");
        
        Customer customer = customerDAO.findCustomerById(customerId);
        if (customer != null) {
            System.out.println("Customer found: " + customer);
        } else {
            System.out.println("Customer not found with ID: " + customerId);
        }
    }
    
    private static void createPolicyType() throws SQLException {
        System.out.println("\n=== Create New Policy Type ===");
        
        System.out.print("Policy Name: ");
        String policyname = scanner.nextLine();
        
        System.out.print("Description: ");
        String description = scanner.nextLine();
        
        long riskscore = getLongInput("Risk Score: ");
        
        PolicyType policyType = new PolicyType(policyname, description, riskscore);
        long policyTypeId = policyTypeDAO.createPolicyType(policyType);
        System.out.println("Policy Type created successfully with ID: " + policyTypeId);
    }
    
    private static void viewAllPolicyTypes() throws SQLException {
        System.out.println("\n=== All Policy Types ===");
        List<PolicyType> policyTypes = policyTypeDAO.getAllPolicyTypes();
        
        if (policyTypes.isEmpty()) {
            System.out.println("No policy types found.");
        } else {
            for (PolicyType policyType : policyTypes) {
                System.out.println(policyType);
            }
        }
    }
    
    private static void createClaim() throws SQLException {
        System.out.println("\n=== Create New Claim ===");
        
        long customerpolicyid = getLongInput("Customer Policy ID: ");
        
        System.out.print("Incident Date (YYYY-MM-DD): ");
        Date incidentdate = Date.valueOf(scanner.nextLine());
        
        System.out.print("Apply Date (YYYY-MM-DD): ");
        Date applydate = Date.valueOf(scanner.nextLine());
        
        long claimtype = getLongInput("Claim Type ID: ");
        
        System.out.print("Amount Claimed: ");
        BigDecimal amountclaimed = new BigDecimal(scanner.nextLine());
        
        System.out.print("Status: ");
        String status = scanner.nextLine();
        
        System.out.print("Claim Note: ");
        String claimnote = scanner.nextLine();
        
        Claim claim = new Claim(customerpolicyid, incidentdate, applydate, claimtype, 
                               amountclaimed, status, claimnote);
        
        long claimId = claimDAO.createClaim(claim);
        System.out.println("Claim created successfully with ID: " + claimId);
    }
    
    private static void viewClaimsByStatus() throws SQLException {
        System.out.println("\n=== View Claims by Status ===");
        System.out.print("Enter Status: ");
        String status = scanner.nextLine();
        
        List<Claim> claims = claimDAO.findClaimsByStatus(status);
        
        if (claims.isEmpty()) {
            System.out.println("No claims found with status: " + status);
        } else {
            for (Claim claim : claims) {
                System.out.println(claim);
            }
        }
    }
    
    private static void updateClaimStatus() throws SQLException {
        System.out.println("\n=== Update Claim Status ===");
        long claimId = getLongInput("Enter Claim ID: ");
        
        System.out.print("Enter New Status: ");
        String newStatus = scanner.nextLine();
        
        boolean updated = claimDAO.updateClaimStatus(claimId, newStatus);
        if (updated) {
            System.out.println("Claim status updated successfully!");
        } else {
            System.out.println("Failed to update claim status. Claim ID may not exist.");
        }
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
    
    private static long getLongInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextLong()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        long value = scanner.nextLong();
        scanner.nextLine(); // consume newline
        return value;
    }
}
