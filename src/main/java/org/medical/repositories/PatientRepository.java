package org.medical.repositories;

import org.medical.model.Patient;
import org.medical.util.singleton.DBManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientRepository {

    private final DBManager dbManager = DBManager.getInstance();
    private final List<Patient> patients = dbManager.getPatients();


    public void save(Patient patient) {
        patients.add(patient);
    }


    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }


    public Patient findById(Integer id) {
        return patients.stream().filter(p -> Objects.equals(p.getPatientId(), id)).findFirst().orElse(null);
    }


    public void delete(Patient patient) {
        patients.remove(patient);
    }


}
