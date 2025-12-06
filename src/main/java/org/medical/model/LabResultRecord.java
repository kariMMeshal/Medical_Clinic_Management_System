package org.medical.model;

import java.time.LocalDate;

public class LabResultRecord extends MedicalRecord {
    public LabResultRecord(Integer recordId, String notes, LocalDate createdAt, Integer patientId) {
        super(recordId, notes, createdAt, patientId);
    }

    public LabResultRecord() {
    }

    @Override
    public String createSummary() {
        return " Summary For Lab Results Record ";
    }
}
