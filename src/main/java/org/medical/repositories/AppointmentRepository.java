package org.medical.repositories;

import org.medical.model.Appointment;
import org.medical.util.singleton.AppointmentScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppointmentRepository {

    private static AppointmentRepository instance;
    private final AppointmentScheduler appointmentScheduler = AppointmentScheduler.getInstance();

    private AppointmentRepository() {

    }

    public static AppointmentRepository getInstance() {
        if (instance == null) {
            synchronized (AppointmentRepository.class) {
                if (instance == null) {
                    instance = new AppointmentRepository();
                }
            }
        }
        return instance;
    }

    public boolean save(Appointment appointment) {
        return appointmentScheduler.scheduleAppointment(appointment);
    }


    public List<Appointment> findAll() {
        return appointmentScheduler.getAppointments();
    }


    public Appointment findById(Integer id) {
        return appointmentScheduler.getAppointments().stream()
                .filter(a -> Objects.equals(a.getAppointmentId(), id))
                .findFirst()
                .orElse(null);
    }


    public boolean delete(Integer appointmentId) {
        return appointmentScheduler.cancelAppointment(appointmentId);
    }

    // Optional: find all appointments for a specific doctor
    public List<Appointment> findByDoctor(String doctorName) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentScheduler.getAppointments()) {
            if (Objects.equals(a.getAppointmentDoctor(), doctorName)) {
                result.add(a);
            }
        }
        return result;
    }

    // Optional: find all appointments for a specific patient
    public List<Appointment> findByPatient(String patientName) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentScheduler.getAppointments()) {
            if (Objects.equals(a.getAppointmentPatient(), patientName)) {
                result.add(a);
            }
        }
        return result;
    }
}
