package org.medical;

import org.medical.controller.AppointmentController;
import org.medical.controller.DoctorController;
import org.medical.controller.MedicalRecordController;
import org.medical.controller.PatientController;
import org.medical.model.Appointment;
import org.medical.model.Doctor;
import org.medical.model.MedicalRecord;
import org.medical.model.Patient;
import org.medical.service.InsuranceService;
import org.medical.util.Enums.DoctorSpecialization;
import org.medical.util.Enums.ReportType;
import org.medical.util.adapter.ExternalInsuranceAPI;
import org.medical.util.adapter.InsuranceAdapter;
import org.medical.util.builder.PatientBuilder;
import org.medical.util.factory.DoctorFactory;
import org.medical.util.factory.MedicalReportFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // using Singleton Instances For all Controllers to use them
        AppointmentController appointmentController = AppointmentController.getInstance();
        DoctorController doctorController = DoctorController.getInstance();
        MedicalRecordController medicalRecordController = MedicalRecordController.getInstance();
        PatientController patientController = PatientController.getInstance();

        // Doctor Factory & Controller Usage
        Doctor doctor = DoctorFactory.createDoctor(DoctorSpecialization.GENERAL, 12, "Dr.Ziyad");
        if (doctorController.saveDoctor(doctor)) {
            System.out.println(doctorController.getDoctorByName("Dr.Ziyad").toString());
        }
        // Appointment Controller Scheduler Usage
        Appointment appointment = appointmentController.scheduleAppointment("Kareem", doctor, LocalDateTime.of(2025, 12, 10, 8, 30));
        System.out.println(appointmentController.getAppointmentsByPatient("Kareem").toString());

        // MedicalRecord Factory & MedicalRecord Clone(Prototype Pattern) &  MedicalRecordController Usage
        MedicalRecord medicalRecord = MedicalReportFactory.createReport(4321, 1234, LocalDate.of(2025, 12, 5), ReportType.PATIENT_HISTORY, "Content Of the Report");
        if (medicalRecordController.saveReport(medicalRecord)) {
            System.out.println(medicalRecordController.getReportById(1234).toString());
        }
        MedicalRecord cloned = medicalRecord.clone();
        System.out.println(cloned);

        // PatientBuilder & PatientController Usage
        Patient patient = new PatientBuilder().patientName("Hamza").patientId(4321).patientAge(25).MedicalRecords(medicalRecordController.getReportsByPatientId(4321)).build();
        if (patientController.addPatient(patient)) {
            System.out.println(patientController.getPatientById(4321));
        }

        // Usage of Adapter Pattern
        ExternalInsuranceAPI externalInsuranceAPI = new ExternalInsuranceAPI();
        InsuranceService insuranceService = new InsuranceAdapter(externalInsuranceAPI);
        boolean covered = insuranceService.verifyCoverage(1234);
        double copay = insuranceService.calculateCoPay(1234, 500.0);
        System.out.println(covered);
        System.out.println(copay);

    }
}