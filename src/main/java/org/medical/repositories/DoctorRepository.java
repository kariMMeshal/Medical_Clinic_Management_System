package org.medical.repositories;

import org.medical.model.Doctor;
import org.medical.util.singleton.DBManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoctorRepository {
    private static volatile DoctorRepository instance;
    private final DBManager dbManager = DBManager.getInstance();
    private final List<Doctor> doctors = dbManager.getDoctors();

    private DoctorRepository() {
    }

    public static DoctorRepository getInstance() {
        if (instance == null) {
            synchronized (DoctorRepository.class) {
                if (instance == null) {
                    instance = new DoctorRepository();
                }
            }
        }
        return instance;
    }

    public void save(Doctor doctor) {
        doctors.add(doctor);
    }


    public List<Doctor> findAll() {
        return new ArrayList<>(doctors);
    }


    public Doctor findByName(String name) {
        return doctors.stream().filter(d -> d.getDoctorName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }


    public boolean deleteById(Integer id) {
        return doctors.removeIf(d -> Objects.equals(d.getDoctorId(), id));
    }

}
