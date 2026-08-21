/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.patienthospitalsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class PatientHospitalSystem {
   
    static ArrayList<Patient> patients = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static Ward ward = new Ward();

    public static void main(String[] args) {
        while(true){
            System.out.println("\n=== HOSPITAL SYSTEM ===");
            System.out.println("1.Add 2.View All 3.Search 4.Delete 5.Bed Management 6.Reports 7.Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();
            if(choice.equals("1")) add();
            else if(choice.equals("2")) view();
            else if(choice.equals("3")) search();
            else if(choice.equals("4")) delete();
            else if(choice.equals("5")) bedMenu();
            else if(choice.equals("6")) reportMenu();
            else if(choice.equals("7")) System.exit(0);
        }
    }
    static void add(){
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("First Name: "); String fn = sc.nextLine();
        System.out.print("Last Name: "); String ln = sc.nextLine();
        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
        System.out.print("Gender: "); String g = sc.nextLine();
        System.out.print("Condition: "); String c = sc.nextLine();
        System.out.print("Category 1-INPATIENT 2-OUTPATIENT 3-EMERGENCY: ");
        int cat = Integer.parseInt(sc.nextLine());
        PatientCategory pc = cat==1?PatientCategory.INPATIENT:cat==2?PatientCategory.OUTPATIENT:PatientCategory.EMERGENCY;
        patients.add(new Patient(id,fn,ln,age,g,c,pc));
        System.out.println("Patient Added!");
    }
    static void view(){ if(patients.isEmpty()) System.out.println("No patients"); else for(Patient p:patients) p.displayDetails(); }
    static void search(){ System.out.print("Enter ID: "); String id=sc.nextLine(); for(Patient p:patients) if(p.getPatientID().equals(id)){ p.displayDetails(); return; } System.out.println("Not found"); }
    static void delete(){ System.out.print("ID to delete: "); String id=sc.nextLine(); ward.releaseBed(id); patients.removeIf(p->p.getPatientID().equals(id)); System.out.println("Done"); }

    static void bedMenu(){
        while(true){
            System.out.println("\n--- BED MANAGEMENT ---");
            System.out.println("1.Allocate Bed 2.Release Bed 3.Display Ward Layout 4.Available Beds 5.Occupied Beds 6.Back");
            System.out.print("Choice: "); String c = sc.nextLine();
            if(c.equals("1")){
                System.out.print("Enter INPATIENT ID: "); String pid = sc.nextLine();
                Patient found = null; for(Patient p:patients) if(p.getPatientID().equals(pid)) found=p;
                if(found==null){ System.out.println("Patient not found"); continue; }
                if(found.getCategory()!=PatientCategory.INPATIENT){ System.out.println("Only Inpatients allowed! This is "+found.getCategory()); continue; }
                ward.allocateBed(pid);
            } else if(c.equals("2")){ System.out.print("Enter Patient ID to discharge: "); String pid=sc.nextLine(); ward.releaseBed(pid); }
            else if(c.equals("3")) ward.displayLayout();
            else if(c.equals("4")) ward.displayAvailable();
            else if(c.equals("5")) ward.displayOccupied();
            else if(c.equals("6")) break;
        }
    }
    static void reportMenu(){
        System.out.println("\n===== HOSPITAL REPORTS =====");
        System.out.println("1. All Registered Patients: "+patients.size());
        for(Patient p:patients) p.displayDetails();
        System.out.println("\n2. Available Beds:");
        ward.displayAvailable();
        System.out.println("\n3. Occupied Beds:");
        ward.displayOccupied();
        System.out.println("\n4. Total Registered Patients: "+patients.size());
        System.out.println("5. Total Occupied Beds: "+ward.getTotalOccupied()+" / "+ward.getTotalBeds());
        System.out.printf("6. Ward Occupancy Percentage: %.2f%%\n", ward.getOccupancyPercentage());
        System.out.println("==============================");
    }
}