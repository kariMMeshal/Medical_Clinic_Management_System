package org.medical.controller;

import org.medical.model.Doctor;
import org.medical.repositories.DoctorRepository;
import org.medical.service.DoctorService;
import org.medical.util.Enums.DoctorSpecialization;

/**
 * Singleton Controller responsible for:
 * - Creating DoctorRepository
 * - Creating DoctorService
 * - Delegating doctor-related operations
 */
public class DoctorController {

    private static volatile DoctorController instance;
    private final DoctorService doctorService;

    private DoctorController() {
        // Internal wiring
        DoctorRepository doctorRepository = DoctorRepository.getInstance();
        this.doctorService = DoctorService.getInstance(doctorRepository);
    }

    public static DoctorController getInstance() {
        if (instance == null) {
            synchronized (DoctorController.class) {
                if (instance == null) {
                    instance = new DoctorController();
                }
            }
        }
        return instance;
    }

    // ---------- Delegation Methods ----------

    // ---------- Option 1: Accept raw values ----------
    public Doctor createDoctor(Integer doctorId,
                             String doctorName,
                             DoctorSpecialization specialization) {
        return doctorService.createDoctor(doctorId, doctorName, specialization);
    }

    // ---------- Option 2: Accept ready object ----------
    public boolean saveDoctor(Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    public Doctor getDoctorByName(String doctorName) {
        return doctorService.getDoctorsBySpecialization(doctorName);
    }

    public boolean deleteDoctorById(Integer doctorId) {
        return doctorService.deleteDoctorById(doctorId);
    }
}
