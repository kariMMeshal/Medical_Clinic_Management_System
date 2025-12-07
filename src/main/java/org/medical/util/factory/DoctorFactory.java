package org.medical.util.factory;

import org.medical.model.CardiologistDoctor;
import org.medical.model.Doctor;
import org.medical.model.GeneralDoctor;
import org.medical.model.NeurologistDoctor;
import org.medical.util.Enums.DoctorSpecialization;

public class DoctorFactory {
    public static Doctor createDoctor(DoctorSpecialization specialization) {
        return switch (specialization) {
            case CARDIOLOGIST -> new CardiologistDoctor();
            case NEUROLOGIST -> new NeurologistDoctor();
            case GENERAL -> new GeneralDoctor();
        };

    }

}
