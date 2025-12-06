package org.medical.repositories;

import org.medical.model.MedicalRecord;
import org.medical.model.Patient;
import org.medical.model.PatientHistoryRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientRepository {
    public List<Patient> patients = new ArrayList<>();

    public PatientRepository() {
        List<MedicalRecord> records = new ArrayList<>();
        records.add(new PatientHistoryRecord(1, "", LocalDate.of(2024, 2, 15), 1));
        records.add(new PatientHistoryRecord(2, "", LocalDate.of(2024, 2, 15), 1));
        records.add(new PatientHistoryRecord(3, "", LocalDate.of(2024, 2, 15), 1));

        patients.add(new Patient(1, "Kareem", 20, "015", records));
        patients.add(new Patient(2, "Mohamed", 23, "010", records));
        patients.add(new Patient(3, "Moustafa", 23, "010", records));
    }

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
