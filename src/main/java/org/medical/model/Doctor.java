package org.medical.model;

import org.medical.util.Enums.DoctorSpecialization;

public abstract class Doctor {
    private Integer doctorId;
    private String doctorName;
    private DoctorSpecialization doctorSpecialization;

    public Doctor(Integer doctorId, String doctorName, DoctorSpecialization doctorSpecialization) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
    }

    public Doctor() {
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public DoctorSpecialization getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(DoctorSpecialization doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public abstract String doctorCanHandle();

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSpecialization=" + doctorSpecialization.toString() +
                '}';
    }
}
