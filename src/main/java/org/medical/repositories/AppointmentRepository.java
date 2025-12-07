package org.medical.repositories;

import org.medical.model.Appointment;
import org.medical.util.singleton.DBManager;

import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {
    private final DBManager dbManager = DBManager.getInstance();
    private final List<Appointment> appointments = dbManager.getAppointments();


    public void save(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }


    public Appointment findById(int id) {
        return appointments.stream().filter(a -> a.getAppointmentId() == id).findFirst().orElse(null);
    }

    public void delete(Appointment appointment) {
        appointments.remove(appointment);
    }

}
