package org.medical.model;

import org.medical.util.Enums.DoctorSpecialization;

public class NeurologistDoctor extends Doctor {
    public NeurologistDoctor(Integer doctorId, String doctorName, DoctorSpecialization doctorSpecialization) {
        super(doctorId, doctorName, doctorSpecialization);
    }


    @Override
    public String doctorCanHandle() {
        return "Neurologist Operations";
    }
}
