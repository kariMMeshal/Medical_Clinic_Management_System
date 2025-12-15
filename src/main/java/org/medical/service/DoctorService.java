package org.medical.service;

import org.medical.model.Doctor;
import org.medical.repositories.DoctorRepository;
import org.medical.util.Enums.DoctorSpecialization;
import org.medical.util.factory.DoctorFactory;

public class DoctorService {

    private static volatile DoctorService instance;
    private final DoctorRepository doctorRepository;


    private DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // static method to get the singleton instance
    public static DoctorService getInstance(DoctorRepository doctorRepository) {
        if (instance == null) {
            synchronized (DoctorService.class) {
                if (instance == null) {
                    instance = new DoctorService(doctorRepository);
                }
            }
        }
        return instance;
    }

    public Doctor createDoctor(Integer doctorId, String doctorName, DoctorSpecialization specialization) {
        Doctor doctor = DoctorFactory.createDoctor(specialization, doctorId, doctorName);
        doctorRepository.save(doctor);
        return doctor;
    }

    public boolean saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    public Doctor getDoctorsBySpecialization(String doctorName) {
        return doctorRepository.findByName(doctorName);
    }

    public boolean deleteDoctorById(Integer doctorId) {
        return doctorRepository.deleteById(doctorId);
    }

}
