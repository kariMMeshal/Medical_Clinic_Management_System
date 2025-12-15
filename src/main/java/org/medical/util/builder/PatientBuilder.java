package org.medical.util.builder;

import org.medical.model.MedicalRecord;
import org.medical.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientBuilder {
    private Integer patientId;
    private String patientName;
    private Integer patientAge;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    public PatientBuilder patientId(Integer patientId) {
        this.patientId = patientId;
        return this;
    }

    public PatientBuilder patientName(String patientName) {
        this.patientName = patientName;
        return this;
    }

    public PatientBuilder patientAge(Integer patientAge) {
        this.patientAge = patientAge;
        return this;
    }

    public PatientBuilder addMedicalRecord(MedicalRecord record) {
        this.medicalRecords.add(record);
        return this;
    }

    public PatientBuilder MedicalRecords(List<MedicalRecord> records) {
        this.medicalRecords = records;
        return this;
    }

    public Patient build() {
        return new Patient(patientId, patientName, patientAge, medicalRecords);
    }
}
