package org.medical.repositories;

import org.medical.model.Doctor;
import org.medical.util.singleton.DBManager;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private final DBManager dbManager = DBManager.getInstance();
    private final List<Doctor> doctors = dbManager.getDoctors();



    public void save(Doctor doctor) {
        doctors.add(doctor);
    }


    public List<Doctor> findAll() {
        return new ArrayList<>(doctors);
    }


    public Doctor findById(int id) {
        return doctors.stream().filter(d -> d.getDoctorId() == id).findFirst().orElse(null);
    }


    public void delete(Doctor doctor) {
        doctors.remove(doctor);
    }

}
