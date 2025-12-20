package org.medical.util.adapter;

import org.medical.service.InsuranceService;

// Adapter (THE CORE OF THE PATTERN) for Adapter Pattern
public class InsuranceAdapter implements InsuranceService {

    private final ExternalInsuranceAPI externalAPI;

    public InsuranceAdapter(ExternalInsuranceAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    @Override
    public boolean verifyCoverage(Integer patientId) {
        return externalAPI.checkEligibility(patientId);
    }

    @Override
    public double calculateCoPay(Integer patientId, double amount) {
        return externalAPI.getPatientShare(amount);
    }
}
