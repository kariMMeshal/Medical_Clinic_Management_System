package org.medical.util.factory;

import org.medical.model.LabResultRecord;
import org.medical.model.MedicalRecord;
import org.medical.model.PatientHistoryRecord;
import org.medical.model.PrescriptionRecord;
import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public class MedicalReportFactory {
    public static MedicalRecord createReport(Integer recordId, ReportType reportType, LocalDate createdAt, Integer patientId) {
        return switch (reportType) {
            case PATIENT_HISTORY -> new PatientHistoryRecord(recordId, reportType, createdAt, patientId);
            case PRESCRIPTION -> new PrescriptionRecord(recordId, reportType, createdAt, patientId);
            case LAB_RESULTS -> new LabResultRecord(recordId, reportType, createdAt, patientId);
            default -> throw new IllegalArgumentException("Unknown report type: " + reportType);
        };
    }

}
