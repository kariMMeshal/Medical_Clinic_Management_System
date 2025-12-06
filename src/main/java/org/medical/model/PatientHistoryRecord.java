package org.medical.model;

import java.time.LocalDate;

public class PatientHistoryRecord extends MedicalRecord {
    public PatientHistoryRecord(Integer recordId, String notes, LocalDate createdAt, Integer patientId) {
        super(recordId, notes, createdAt, patientId);
    }

    public PatientHistoryRecord() {
    }

    @Override
    public String createSummary() {
        return "Summary For Patient History Record";
    }
}
