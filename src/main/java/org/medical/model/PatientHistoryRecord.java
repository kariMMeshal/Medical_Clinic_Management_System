package org.medical.model;

import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public class PatientHistoryRecord extends MedicalRecord {

    public PatientHistoryRecord(Integer patientId, Integer recordId, LocalDate createdAt, ReportType recordType, String content) {
        super(patientId, recordId, createdAt, recordType, content);
    }

    @Override
    public String createSummary() {
        return "Summary For Patient History Record";
    }
}
