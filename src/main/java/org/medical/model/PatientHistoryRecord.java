package org.medical.model;

import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public class PatientHistoryRecord extends MedicalRecord {
    public PatientHistoryRecord(Integer recordId, ReportType reportType, LocalDate createdAt, Integer patientId) {
        super(recordId, reportType, createdAt, patientId);
    }


    @Override
    public String createSummary() {
        return "Summary For Patient History Record";
    }
}
