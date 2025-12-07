package org.medical.util.factory;

import org.medical.model.LabResultRecord;
import org.medical.model.MedicalRecord;
import org.medical.model.PatientHistoryRecord;
import org.medical.model.PrescriptionRecord;
import org.medical.util.Enums.ReportType;

public class MedicalReportFactory {
    public static MedicalRecord createReport(ReportType type) {
        return switch (type) {
            case PATIENT_HISTORY -> new PatientHistoryRecord();
            case PRESCRIPTION -> new PrescriptionRecord();
            case LAB_RESULTS -> new LabResultRecord();
            default -> throw new IllegalArgumentException("Unknown report type: " + type);

        };

    }

}
