package org.medical.model;

import org.medical.util.Enums.DoctorSpecialization;

public class GeneralDoctor extends Doctor {
    public GeneralDoctor(Integer doctorId, String doctorName, DoctorSpecialization doctorSpecialization) {
        super(doctorId, doctorName, doctorSpecialization);
    }

    @Override
    public String doctorCanHandle() {
        return "General Operations";
    }
}
