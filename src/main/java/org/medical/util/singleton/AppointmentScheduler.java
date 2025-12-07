package org.medical.util.singleton;

import org.medical.model.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentScheduler {

    private static volatile AppointmentScheduler instance;
    private final List<Appointment> appointments = new ArrayList<>();

    private AppointmentScheduler() {
        addDummyAppointments();
    }

    public static AppointmentScheduler getInstance() {
        if (instance == null) {
            synchronized (AppointmentScheduler.class) {
                if (instance == null) {
                    instance = new AppointmentScheduler();
                }
            }
        }
        return instance;
    }


    public boolean scheduleAppointment(Appointment appointment) {
        if (isDoctorAvailable(appointment.getAppointmentDoctor(), appointment.getAppointmentTime())) {
            appointments.add(appointment);
            return true;
        }
        return false;
    }


    public boolean isDoctorAvailable(String doctorName, LocalDateTime time) {
        return appointments.stream()
                .noneMatch(a -> a.getAppointmentDoctor().equals(doctorName)
                        && a.getAppointmentTime().equals(time));
    }


    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }


    public boolean cancelAppointment(Integer appointmentId) {
        return appointments.removeIf(a -> a.getAppointmentId().equals(appointmentId));
    }

    private void addDummyAppointments() {
        appointments.add(new Appointment(1, "Ahmed Mohamed", "DR.Ali", LocalDateTime.of(2025, 12, 10, 10, 0), "Scheduled"));
        appointments.add(new Appointment(2, "Khaled Ahmed", "DR.Ali", LocalDateTime.of(2025, 12, 10, 11, 0), "Scheduled"));
        appointments.add(new Appointment(3, "Kareem Hamdy", "DR.Fatma", LocalDateTime.of(2025, 12, 11, 9, 30), "Scheduled"));
        appointments.add(new Appointment(4, "Moustafa Gaper", "DR.Fatma", LocalDateTime.of(2025, 12, 11, 10, 30), "Scheduled"));
        appointments.add(new Appointment(5, "Ebrahim Yousry", "DR.Ali", LocalDateTime.of(2025, 12, 10, 10, 0), "Scheduled"));

    }

}
