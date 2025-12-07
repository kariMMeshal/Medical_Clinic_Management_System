package org.medical.util.singleton;

import org.medical.model.*;
import org.medical.util.Enums.ReportType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    // volatile → ensures visibility and prevents partially constructed objects.
    private static volatile DBManager instance;
    private final List<Patient> patients = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();
    private final List<MedicalRecord> records = new ArrayList<>();

    private DBManager() {
        feedDummyData();
    }

    //Double-checked locking with synchronized → ensures thread-safe lazy initialization with minimal performance overhead.
    public static DBManager getInstance() {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }


    // Patient dummy data for testing
    private void feedDummyData() {
        records.add(new PatientHistoryRecord(1, 1, LocalDate.of(2025, 11, 15), ReportType.PATIENT_HISTORY, "Record 1 Content "));
        records.add(new PatientHistoryRecord(1, 5, LocalDate.of(2025, 11, 15), ReportType.PATIENT_HISTORY, "Record 1 Content "));
        records.add(new PatientHistoryRecord(2, 2, LocalDate.of(2025, 11, 18), ReportType.PATIENT_HISTORY, "Record 2 Content"));
        records.add(new PatientHistoryRecord(3, 3, LocalDate.of(2025, 11, 22), ReportType.PATIENT_HISTORY, "Record 3 Content"));

        patients.add(new Patient(1, "Kareem", 20, "015", records));
        patients.add(new Patient(2, "Mohamed", 23, "010", records));
        patients.add(new Patient(3, "Moustafa", 23, "010", records));
    }

    // Getters for repositories to use Data Base Tables From the Singleton db instance
    public List<Patient> getPatients() {
        return patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<MedicalRecord> getRecords() {
        return records;
    }


}
