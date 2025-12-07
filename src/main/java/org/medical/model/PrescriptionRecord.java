package org.medical.model;

import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public class PrescriptionRecord extends MedicalRecord {
    public PrescriptionRecord(Integer recordId, ReportType reportType, LocalDate createdAt, Integer patientId) {
        super(recordId, reportType, createdAt, patientId);
    }


    @Override
    public String createSummary() {
        return "Summary For Prescription Record";
    }
}
