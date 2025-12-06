package org.medical.model;

public class PrescriptionRecord extends MedicalRecord{
    @Override
    public String createSummary() {
        return "Summary For Prescription Record";
    }
}
