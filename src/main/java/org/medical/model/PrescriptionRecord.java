package org.medical.model;

import java.time.LocalDate;

public class PrescriptionRecord extends MedicalRecord {
    public PrescriptionRecord(Integer recordId, String notes, LocalDate createdAt, Integer patientId) {
        super(recordId, notes, createdAt, patientId);
    }

    public PrescriptionRecord() {
    }

    @Override
    public String createSummary() {
        return "Summary For Prescription Record";
    }
}
