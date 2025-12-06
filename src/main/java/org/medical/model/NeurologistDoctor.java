package org.medical.model;

public class NeurologistDoctor extends Doctor {
    @Override
    public String doctorCanHandle() {
        return "Neurologist Operations";
    }
}
