/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.petdatabase;

import java.util.ArrayList;
import java.util.List;

public class PetDatabase {
    private List<Pet> pets;

    public PetDatabase() {
        this.pets = new ArrayList<>();
    }

    public void addPet(String name, int age) {
        pets.add(new Pet(name, age));
    }

    public void displayPets() {
        System.out.printf("+----------------------+\n");
        System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.printf("+----------------------+\n");
        for (int i = 0; i < pets.size(); i++) {
            System.out.printf("| %-3d | %s |\n", i, pets.get(i));
        }
        System.out.printf("+----------------------+\n");
        System.out.printf("%d rows in set.\n", pets.size());
    }

    public void searchByName(String name) {
        System.out.printf("+----------------------+\n");
        System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.printf("+----------------------+\n");
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getName().equalsIgnoreCase(name)) {
                System.out.printf("| %-3d | %s |\n", i, pets.get(i));
            }
        }
        System.out.printf("+----------------------+\n");
    }

    public void searchByAge(int age) {
        System.out.printf("+----------------------+\n");
        System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.printf("+----------------------+\n");
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getAge() == age) {
                System.out.printf("| %-3d | %s |\n", i, pets.get(i));
            }
        }
        System.out.printf("+----------------------+\n");
    }

    public void updatePet(int id, String newName, int newAge) {
        if (id >= 0 && id < pets.size()) {
            pets.get(id).setName(newName);
            pets.get(id).setAge(newAge);
        } else {
            System.out.println("Invalid ID.");
        }
    }

    public void removePet(int id) {
        if (id >= 0 && id < pets.size()) {
            pets.remove(id);
        } else {
            System.out.println("Invalid ID.");
        }
    }
}



