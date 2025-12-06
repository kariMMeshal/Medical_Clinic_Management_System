package org.medical.model;

public class PatientHistoryRecord extends MedicalRecord {
    @Override
    public String createSummary() {
        return "Summary For Patient History Record";
    }
}
