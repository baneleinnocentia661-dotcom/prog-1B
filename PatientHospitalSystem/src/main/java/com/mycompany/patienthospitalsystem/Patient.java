/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patienthospitalsystem;

/**
 *
 * @author banel
 */
public class Patient {

    private String id, firstName, lastName, gender, condition;
    private int age;
    private PatientCategory category;

    public Patient(String id, String fn, String ln, int age, String g, String c, PatientCategory cat){
        this.id=id; firstName=fn; lastName=ln; this.age=age; gender=g; condition=c; category=cat;
    }
    public String getPatientID(){ return id; }
    public PatientCategory getCategory(){ return category; }
    public void displayDetails(){
        System.out.println("ID: "+id+" | Name: "+firstName+" "+lastName+" | Age: "+age+" | Gender: "+gender);
        System.out.println("Condition: "+condition+" | Category: "+category);
        System.out.println("-------------------------------");
    }
}