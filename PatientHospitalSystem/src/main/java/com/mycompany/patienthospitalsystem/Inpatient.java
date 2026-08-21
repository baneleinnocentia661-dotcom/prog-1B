/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patienthospitalsystem;

/**
 *
 * @author banel
 */
public class Inpatient extends Patient {
  
    private int wardNumber;
    private String bedNumber;

    public Inpatient(String id, String first, String last, int age, String gender, String condition, int ward, String bed) {
        super(id, first, last, age, gender, condition, PatientCategory.INPATIENT);
        this.wardNumber = ward;
        this.bedNumber = bed;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Ward Number: " + wardNumber);
        System.out.println("Bed Number: " + bedNumber);
    }
} 

