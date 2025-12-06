package org.medical.model;

public class CardiologistDoctor extends Doctor {


    @Override
    public String doctorCanHandle() {
        return "Cardiologist Operations";
    }
}
