package org.medical.model;

public class LabResultRecord extends MedicalRecord {
    @Override
    public String createSummary() {
        return " Summary For Lab Results Record ";
    }
}
