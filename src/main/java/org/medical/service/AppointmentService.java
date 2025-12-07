package org.medical.service;

import org.medical.model.Appointment;
import org.medical.model.Doctor;
import org.medical.repositories.AppointmentRepository;
import org.medical.util.singleton.DBManager;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {
    private static volatile AppointmentService instance;
    private final AppointmentRepository appointmentRepo;

    private AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepo = appointmentRepository;
    }

    public static AppointmentService getInstance(AppointmentRepository appointmentRepository) {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new AppointmentService(appointmentRepository);
                }
            }
        }
        return instance;
    }

    public boolean scheduleAppointment(String patientName, Doctor doctor, LocalDateTime time) {
        Appointment appointment = new Appointment(null, patientName, doctor.getDoctorName(), time, "Scheduled");
        return appointmentRepo.save(appointment);
    }


    public boolean cancelAppointment(Integer appointmentId) {
       return appointmentRepo.delete(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment getAppointmentById(Integer id) {
        return appointmentRepo.findById(id);
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorName) {
        return appointmentRepo.findByDoctor(doctorName);
    }

    public List<Appointment> getAppointmentsByPatient(String patientName) {
        return appointmentRepo.findByPatient(patientName);
    }
}
