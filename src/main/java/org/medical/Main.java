package org.medical;

import org.medical.repositories.AppointmentRepository;
import org.medical.repositories.DoctorRepository;
import org.medical.repositories.MedicalRecordRepository;
import org.medical.repositories.PatientRepository;
import org.medical.service.AppointmentService;
import org.medical.service.DoctorService;
import org.medical.service.MedicalRecordService;
import org.medical.service.PatientService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AppointmentRepository appointmentRepository = AppointmentRepository.getInstance();
        AppointmentService appointmentService = AppointmentService.getInstance(appointmentRepository);

        DoctorRepository doctorRepository = DoctorRepository.getInstance();
        DoctorService doctorService = DoctorService.getInstance(doctorRepository);

        MedicalRecordRepository medicalRecordRepository = MedicalRecordRepository.getInstance();
        MedicalRecordService medicalRecordService = MedicalRecordService.getInstance(medicalRecordRepository);

        PatientRepository patientRepository = PatientRepository.getInstance();
        PatientService patientService = PatientService.getInstance(patientRepository);


    }
}