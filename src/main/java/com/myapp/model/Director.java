package com.myapp.model;

public class Director extends Employee {
    public Director(int id, String firstName, String lastName, int age, String department) {
        super(id, firstName, lastName, age, department);
    }

    // Surcharge de la méthode assignManager
    @Override
    public void assignManager(Employee manager) {
        throw new UnsupportedOperationException("Un Directeur ne peut pas avoir de manager.");
    }
}