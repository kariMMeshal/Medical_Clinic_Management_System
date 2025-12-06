package org.medical.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private Integer patientId;
    private String patientName;
    private Integer patientAge;
    private String patientPhone;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    public Patient(Integer id, String patientName, Integer patientAge, String patientPhone, List<MedicalRecord> medicalRecords) {
        this.patientId = id;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientPhone = patientPhone;
        this.medicalRecords = medicalRecords;
    }

    public Patient() {
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", patientAge=" + patientAge +
                ", patientPhone='" + patientPhone + '\'' +
                ", medicalRecords=" + medicalRecords +
                '}';
    }
}
