package org.medical.model;

import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public class LabResultRecord extends MedicalRecord {
    public LabResultRecord(Integer recordId, ReportType reportType, LocalDate createdAt, Integer patientId) {
        super(recordId, reportType, createdAt, patientId);
    }

    @Override
    public String createSummary() {
        return " Summary For Lab Results Record ";
    }
}
