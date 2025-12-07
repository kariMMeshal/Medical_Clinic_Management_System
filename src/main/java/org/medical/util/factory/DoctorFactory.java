package org.medical.util.factory;

import org.medical.model.CardiologistDoctor;
import org.medical.model.Doctor;
import org.medical.model.GeneralDoctor;
import org.medical.model.NeurologistDoctor;
import org.medical.util.Enums.DoctorSpecialization;

public class DoctorFactory {
    public static Doctor createDoctor(DoctorSpecialization specialization, Integer doctorId,String doctorName ) {
        return switch (specialization) {
            case CARDIOLOGIST -> new CardiologistDoctor(doctorId, doctorName, specialization);
            case NEUROLOGIST -> new NeurologistDoctor(doctorId, doctorName, specialization);
            case GENERAL -> new GeneralDoctor(doctorId, doctorName, specialization);
        };

    }

}
