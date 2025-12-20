package org.medical.util.adapter;

// Adaptee (INCOMPATIBLE EXTERNAL CLASS) for Adapter Pattern
public class ExternalInsuranceAPI {

    public boolean checkEligibility(Integer patientId) {
        return patientId != null;
    }

    public double getPatientShare(double totalCost) {
        return totalCost * 0.25;
    }
}
