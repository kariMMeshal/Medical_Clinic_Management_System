package org.medical.controller;

import org.medical.model.Appointment;
import org.medical.model.Doctor;
import org.medical.repositories.AppointmentRepository;
import org.medical.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Singleton Controller responsible for:
 * - Creating Repository
 * - Creating Service
 * - Delegating calls
 */
public class AppointmentController {

    private static volatile AppointmentController instance;
    private final AppointmentService appointmentService;

    private AppointmentController() {
        // Internal wiring
        AppointmentRepository appointmentRepository = AppointmentRepository.getInstance();
        this.appointmentService = AppointmentService.getInstance(appointmentRepository);
    }

    public static AppointmentController getInstance() {
        if (instance == null) {
            synchronized (AppointmentController.class) {
                if (instance == null) {
                    instance = new AppointmentController();
                }
            }
        }
        return instance;
    }

    // ---------- Delegation Methods ----------

    public Appointment scheduleAppointment(String patientName, Doctor doctor, LocalDateTime time) {
        return appointmentService.scheduleAppointment(patientName, doctor, time);
    }

    public boolean cancelAppointment(Integer appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    public Appointment getAppointmentById(Integer id) {
        return appointmentService.getAppointmentById(id);
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorName) {
        return appointmentService.getAppointmentsByDoctor(doctorName);
    }

    public List<Appointment> getAppointmentsByPatient(String patientName) {
        return appointmentService.getAppointmentsByPatient(patientName);
    }
}
