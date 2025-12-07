package org.medical.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private Integer patientId;
    private String patientName;
    private Integer patientAge;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    public Patient(Integer id, String patientName, Integer patientAge, List<MedicalRecord> medicalRecords) {
        this.patientId = id;
        this.patientName = patientName;
        this.patientAge = patientAge;
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
                ", medicalRecords=" + medicalRecords +
                '}';
    }
}
