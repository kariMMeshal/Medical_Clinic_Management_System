package org.medical.model;

import org.medical.util.Enums.DoctorSpecialization;

public class CardiologistDoctor extends Doctor {


    public CardiologistDoctor(Integer doctorId, String doctorName, DoctorSpecialization doctorSpecialization) {
        super(doctorId, doctorName, doctorSpecialization);
    }


    @Override
    public String doctorCanHandle() {
        return "Cardiologist Operations";
    }
}
