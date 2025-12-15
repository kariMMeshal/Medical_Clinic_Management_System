package org.medical.controller;

import org.medical.model.Patient;
import org.medical.repositories.PatientRepository;
import org.medical.service.PatientService;

import java.util.List;

/**
 * Singleton Controller responsible for:
 * - Creating PatientRepository
 * - Creating PatientService
 * - Delegating patient operations
 */
public class PatientController {

    private static volatile PatientController instance;
    private final PatientService patientService;

    private PatientController() {
        // Internal wiring
        PatientRepository patientRepository = PatientRepository.getInstance();
        this.patientService = PatientService.getInstance(patientRepository);
    }

    public static PatientController getInstance() {
        if (instance == null) {
            synchronized (PatientController.class) {
                if (instance == null) {
                    instance = new PatientController();
                }
            }
        }
        return instance;
    }

    // ---------- Delegation Methods ----------

    public boolean addPatient(Patient patient) {
        return patientService.addPatient(patient);
    }

    public boolean updatePatient(Patient patient) {
        return patientService.updatePatient(patient);
    }

    public boolean deletePatientById(Integer id) {
        return patientService.deletePatientById(id);
    }

    public Patient getPatientById(Integer id) {
        return patientService.findPatientById(id);
    }

    public List<Patient> getAllPatients() {
        return patientService.findAllPatients();
    }
}
