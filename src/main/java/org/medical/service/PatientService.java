package org.medical.service;

import org.medical.model.Patient;
import org.medical.repositories.PatientRepository;

import java.util.List;

public class PatientService {

    private static volatile PatientService instance;
    private final PatientRepository patientRepository;

    private PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static PatientService getInstance(PatientRepository patientRepository) {
        if (instance == null) {
            synchronized (PatientService.class) {
                if (instance == null) {
                    instance = new PatientService(patientRepository);
                }
            }
        }
        return instance;
    }


    public boolean addPatient(Patient patient) {
        return patientRepository.save(patient);
    }


    public boolean updatePatient(Patient patient) {
        return patientRepository.update(patient);
    }


    public boolean deletePatientById(Integer id) {
        return patientRepository.deleteById(id);
    }


    public Patient findPatientById(Integer id) {
        return patientRepository.findById(id);
    }


    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }
}
