package org.medical.model;

import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public class PrescriptionRecord extends MedicalRecord {
    public PrescriptionRecord(Integer patientId, Integer recordId, LocalDate createdAt, ReportType recordType, String content) {
        super(patientId, recordId, createdAt, recordType, content);
    }

    @Override
    public String createSummary() {
        return "Summary For Prescription Record";
    }

    @Override
    public MedicalRecord clone() {
        return new PrescriptionRecord(
                getPatientId(),
                getRecordId(),
                getCreatedAt(),
                getRecordType(),
                getContent()
        );
    }
}
