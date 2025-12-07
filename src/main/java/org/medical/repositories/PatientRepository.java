package org.medical.repositories;

import org.medical.model.Patient;
import org.medical.util.singleton.DBManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientRepository {
    private static volatile PatientRepository instance;
    private final DBManager dbManager = DBManager.getInstance();
    private final List<Patient> patients = dbManager.getPatients();

    private PatientRepository() {
    }

    public static PatientRepository getInstance() {
        if (instance == null) {
            synchronized (PatientRepository.class) {
                if (instance == null) {
                    instance = new PatientRepository();
                }
            }
        }
        return instance;
    }

    public boolean save(Patient patient) {
        return patients.add(patient);
    }

    public boolean update(Patient patient) {
        for (int i = 0; i < patients.size(); i++) {
            if (Objects.equals(patients.get(i).getPatientId(), patient.getPatientId())) {
                patients.set(i, patient);
                return true;
            }
        }
        return false;
    }


    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }


    public Patient findById(Integer id) {
        return patients.stream().filter(p -> Objects.equals(p.getPatientId(), id)).findFirst().orElse(null);
    }


    public boolean deleteById(Integer id) {
        return patients.removeIf(p -> Objects.equals(p.getPatientId(), id));
    }


}
