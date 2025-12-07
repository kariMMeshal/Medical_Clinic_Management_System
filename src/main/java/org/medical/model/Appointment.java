package org.medical.model;

import java.time.LocalDateTime;

public class Appointment {
    private Integer appointmentId;
    private String appointmentPatient;
    private String appointmentDoctor;
    private LocalDateTime appointmentTime;
    private String appointmentStatus;

    //Constructors
    public Appointment(Integer appointmentId, String appointmentPatient, String appointmentDoctor, LocalDateTime appointmentTime, String appointmentStatus) {
        this.appointmentId = appointmentId;
        this.appointmentPatient = appointmentPatient;
        this.appointmentDoctor = appointmentDoctor;
        this.appointmentTime = appointmentTime;
        this.appointmentStatus = appointmentStatus;
    }

    public Appointment() {
    }

    // Getters & Setters
    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentPatient() {
        return appointmentPatient;
    }

    public void setAppointmentPatient(String appointmentPatient) {
        this.appointmentPatient = appointmentPatient;
    }

    public String getAppointmentDoctor() {
        return appointmentDoctor;
    }

    public void setAppointmentDoctor(String appointmentDoctor) {
        this.appointmentDoctor = appointmentDoctor;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", appointmentPatient='" + appointmentPatient + '\'' +
                ", appointmentDoctor='" + appointmentDoctor + '\'' +
                ", appointmentTime=" + appointmentTime +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                '}';
    }
}
