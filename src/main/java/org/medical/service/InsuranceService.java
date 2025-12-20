package org.medical.service;

// Target Interface (EXPECTED INTERFACE) for Adapter Pattern
public interface InsuranceService {

    boolean verifyCoverage(Integer patientId);
    double calculateCoPay(Integer patientId, double amount);

}

