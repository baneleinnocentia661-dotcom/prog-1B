/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patienthospitalsystem;

/**
 *
 * @author banel
 */
public class Ward {
 
    private Bed[][] beds = new Bed[4][5];
    public Ward(){
        int num=1;
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                String id = String.format("B%02d", num++);
                beds[i][j] = new Bed(id);
            }
        }
    }
    public boolean allocateBed(String patientId){
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                if(!beds[i][j].isOccupied()){
                    beds[i][j].allocate(patientId);
                    System.out.println("Allocated "+beds[i][j].getBedId()+" to patient "+patientId);
                    return true;
                }
            }
        }
        System.out.println("No beds available!");
        return false;
    }
    public void releaseBed(String patientId){
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                if(beds[i][j].isOccupied() && beds[i][j].getPatientId().equals(patientId)){
                    System.out.println("Released "+beds[i][j].getBedId()+" from patient "+patientId);
                    beds[i][j].release();
                    return;
                }
            }
        }
        System.out.println("Patient has no bed");
    }
    public void displayLayout(){
        System.out.println("\n=== WARD LAYOUT (4x5) ===");
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                Bed b = beds[i][j];
                if(b.isOccupied()) System.out.print(b.getBedId()+"[X] ");
                else System.out.print(b.getBedId()+"[ ] ");
            }
            System.out.println();
        }
    }
    public void displayAvailable(){
        System.out.print("Available beds: ");
        for(int i=0;i<4;i++) for(int j=0;j<5;j++) if(!beds[i][j].isOccupied()) System.out.print(beds[i][j].getBedId()+" ");
        System.out.println();
    }
    public void displayOccupied(){
        System.out.println("Occupied beds:");
        for(int i=0;i<4;i++) for(int j=0;j<5;j++) if(beds[i][j].isOccupied()) System.out.println(beds[i][j].getBedId()+" -> Patient "+beds[i][j].getPatientId());
    }
    public int getTotalOccupied(){
        int count=0;
                for(int i=0;i<4;i++) for(int j=0;j<5;j++) if(beds[i][j]!=null && beds[i][j].isOccupied()) count++;
        return count;
    }
    public int getTotalBeds(){ return 20; }
    public double getOccupancyPercentage(){
        return (getTotalOccupied() * 100.0) / getTotalBeds();
    }
}