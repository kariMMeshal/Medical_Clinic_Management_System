package org.medical.model;

import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public class LabResultRecord extends MedicalRecord {
    public LabResultRecord(Integer patientId, Integer recordId, LocalDate createdAt, ReportType recordType, String content) {
        super(patientId, recordId, createdAt, recordType, content);
    }

    @Override
    public String createSummary() {
        return " Summary For Lab Results Record ";
    }

    @Override
    public MedicalRecord clone() {
        return new LabResultRecord(
                getPatientId(),
                getRecordId(),
                getCreatedAt(),
                getRecordType(),
                getContent()
        );
    }
}
