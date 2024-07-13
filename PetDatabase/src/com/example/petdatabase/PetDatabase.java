/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.petdatabase;

import java.io.*;
import java.util.*;

public class PetDatabase {

    private List<Pet> pets;

    public PetDatabase() {
        pets = new ArrayList<>();
    }

    public List<Pet> loadPetData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    pets.add(new Pet(name, age));
                }
            }
        } catch (IOException e) {
            System.out.println("File " + fileName + " not found. Starting with an empty database.");
        }
        return pets;
    }

    public void savePetData(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Pet pet : pets) {
                bw.write(pet.getName() + " " + pet.getAge());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPet(String name, int age) {
        if (pets.size() >= 5) {
            System.out.println("Error: Database is full.");
            return;
        }
        if (age < 1 || age > 20) {
            System.out.println("Error: " + age + " is not a valid age.");
            return;
        }
        pets.add(new Pet(name, age));
    }

    public void displayPets() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            System.out.printf("| %2d | %-10s | %3d |%n", i, pet.getName(), pet.getAge());
        }
        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set.");
    }

    public void updatePet(int id, String newName, int newAge) {
        if (id < 0 || id >= pets.size()) {
            System.out.println("Error: ID " + id + " does not exist.");
        } else {
            Pet pet = pets.get(id);
            pet.setName(newName);
            pet.setAge(newAge);
        }
    }

    public void removePet(int id) {
        if (id < 0 || id >= pets.size()) {
            System.out.println("Error: ID " + id + " does not exist.");
        } else {
            Pet removedPet = pets.remove(id);
            System.out.println(removedPet.getName() + " " + removedPet.getAge() + " is removed.");
        }
    }

    public void searchByName(String name) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                System.out.println("Found: " + pet);
            }
        }
    }

    public void searchByAge(int age) {
        for (Pet pet : pets) {
            if (pet.getAge() == age) {
                System.out.println("Found: " + pet);
            }
        }
    }

    public static void main(String[] args) {
        PetDatabase database = new PetDatabase();
        database.loadPetData("pets.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add new pets");
            System.out.println("3) Remove a pet");
            System.out.println("4) Update a pet");
            System.out.println("5) Search by name");
            System.out.println("6) Search by age");
            System.out.println("7) Exit program");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    database.displayPets();
                    break;
                case 2:
                    System.out.print("add pet (name, age): ");
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        System.out.println("Error: " + input + " is not a valid input.");
                    } else {
                        try {
                            String name = parts[0];
                            int age = Integer.parseInt(parts[1]);
                            database.addPet(name, age);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: " + parts[1] + " is not a valid age.");
                        }
                    }
                    break;
                case 3:
                    database.displayPets();
                    System.out.print("Enter the pet ID to remove: ");
                    try {
                        int id = scanner.nextInt();
                        database.removePet(id);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: ID must be a number.");
                        scanner.next(); // Consume invalid input
                    }
                    break;
                case 4:
                    database.displayPets();
                    System.out.print("Enter the pet ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the new age: ");
                    int newAge = scanner.nextInt();
                    database.updatePet(id, newName, newAge);
                    break;
                case 5:
                    System.out.print("Enter the name to search: ");
                    String name = scanner.nextLine();
                    database.searchByName(name);
                    break;
                case 6:
                    System.out.print("Enter the age to search: ");
                    int age = scanner.nextInt();
                    database.searchByAge(age);
                    break;
                case 7:
                    database.savePetData("pets.txt");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

