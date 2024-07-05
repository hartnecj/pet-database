/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.petdatabase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDatabase database = new PetDatabase();

        while (true) {
            System.out.println("Pet Database Program.");
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Remove an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit program");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                database.displayPets();
            } else if (choice == 2) {
                while (true) {
                    System.out.print("add pet (name, age): ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("done")) {
                        break;
                    }
                    String[] parts = input.split(" ");
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    database.addPet(name, age);
                }
            } else if (choice == 3) {
                database.displayPets();
                System.out.print("Enter the pet ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new name and new age: ");
                String newName = scanner.next();
                int newAge = scanner.nextInt();
                database.updatePet(id, newName, newAge);
            } else if (choice == 4) {
                database.displayPets();
                System.out.print("Enter the pet ID to remove: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
                database.removePet(id);
            } else if (choice == 5) {
                System.out.print("Enter a name to search: ");
                String name = scanner.nextLine();
                database.searchByName(name);
            } else if (choice == 6) {
                System.out.print("Enter age to search: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // consume newline
                database.searchByAge(age);
            } else if (choice == 7) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}



