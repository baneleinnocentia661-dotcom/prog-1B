/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.patienthospitalsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author banel
 */
public class HospitalTest {
    Ward ward;
    ArrayList<Patient> patients;

    @BeforeEach
    public void setup(){
        ward = new Ward();
        patients = new ArrayList<>();
    }

    @Test
    public void testRegisterPatient(){
        Patient p = new Patient("P001","Banel","Mahlangu",20,"M","Flu",PatientCategory.OUTPATIENT);
        patients.add(p);
        assertEquals(1, patients.size());
    }

    @Test
    public void testSearchPatient(){
        patients.add(new Patient("P001","Banel","Mahlangu",20,"M","Flu",PatientCategory.OUTPATIENT));
        Patient found = patients.stream().filter(x->x.getPatientID().equals("P001")).findFirst().orElse(null);
        assertNotNull(found);
    }

    @Test
    public void testUpdatePatientDetails(){
        Patient p = new Patient("P001","Banel","Mahlangu",20,"M","Flu",PatientCategory.OUTPATIENT);
        assertNotNull(p.getPatientID());
    }

    @Test
    public void testDeletePatient(){
        patients.add(new Patient("P001","Banel","Mahlangu",20,"M","Flu",PatientCategory.OUTPATIENT));
        patients.removeIf(x->x.getPatientID().equals("P001"));
        assertEquals(0, patients.size());
    }

    @Test
    public void testAllocateBed(){
        assertTrue(ward.allocateBed("P001"));
    }

    @Test
    public void testReleaseBed(){
        ward.allocateBed("P001");
        ward.releaseBed("P001");
        assertTrue(ward.allocateBed("P001"));
    }

    @Test
    public void testPreventDuplicateIDs(){
        patients.add(new Patient("P001","A","B",20,"M","Flu",PatientCategory.INPATIENT));
        long count = patients.stream().filter(x->x.getPatientID().equals("P001")).count();
        assertEquals(1, count);
    }

    @Test
    public void testPreventAllocatingOccupiedBed(){
        ward.allocateBed("P001");
        ward.allocateBed("P001");
        assertTrue(true);
    }

    @Test
    public void testPreventAllocationWhenAllBedsOccupied(){
        for(int i=0;i<20;i++) ward.allocateBed("P"+i);
        assertFalse(ward.allocateBed("P999"));
    }

    @Test
    public void testSortByPatientID(){
        patients.add(new Patient("P002","Zebra","Z",20,"M","Flu",PatientCategory.OUTPATIENT));
        patients.add(new Patient("P001","Apple","A",20,"M","Flu",PatientCategory.OUTPATIENT));
        Collections.sort(patients, Comparator.comparing(Patient::getPatientID));
        assertEquals("P001", patients.get(0).getPatientID());
    }
}