package org.medical.model;

public class GeneralDoctor extends Doctor {
    @Override
    public String doctorCanHandle() {
        return "General Operations";
    }
}
