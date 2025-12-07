package org.medical.util.factory;

import org.medical.model.LabResultRecord;
import org.medical.model.MedicalRecord;
import org.medical.model.PatientHistoryRecord;
import org.medical.model.PrescriptionRecord;
import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public class MedicalReportFactory {
    public static MedicalRecord createReport(Integer patientId, Integer recordId, LocalDate createdAt, ReportType recordType, String content) {
        return switch (recordType) {
            case PATIENT_HISTORY -> new PatientHistoryRecord(patientId, recordId, createdAt, recordType, content);
            case PRESCRIPTION -> new PrescriptionRecord(patientId, recordId, createdAt, recordType, content);
            case LAB_RESULTS -> new LabResultRecord(patientId, recordId, createdAt, recordType, content);
            default -> throw new IllegalArgumentException("Unknown report type: " + recordType);
        };
    }

}
