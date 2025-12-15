package org.medical.service;

import org.medical.model.MedicalRecord;
import org.medical.repositories.MedicalRecordRepository;
import org.medical.util.Enums.ReportType;
import org.medical.util.factory.MedicalReportFactory;

import java.time.LocalDate;
import java.util.List;

public class MedicalRecordService {

    private static volatile MedicalRecordService instance;
    private final MedicalRecordRepository medicalRecordRepository;

    private MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public static MedicalRecordService getInstance(MedicalRecordRepository medicalRecordRepository) {
        if (instance == null) {
            synchronized (MedicalRecordService.class) {
                if (instance == null) {
                    instance = new MedicalRecordService(medicalRecordRepository);
                }
            }
        }
        return instance;
    }

    public List<MedicalRecord> findReportsByPatient(Integer patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    public List<MedicalRecord> findAllReports() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord createReport(Integer patientId, Integer recordId, LocalDate createdAt, ReportType recordType, String content) {
        MedicalRecord medicalRecord = MedicalReportFactory.createReport(patientId, recordId, createdAt, recordType, content);
        medicalRecordRepository.save(medicalRecord);
        return medicalRecord;
    }

    public boolean saveReport(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }


    public MedicalRecord findReportById(Integer recordId) {
        return medicalRecordRepository.findById(recordId);
    }


    public boolean deleteReport(Integer recordId) {
        return medicalRecordRepository.deleteById(recordId);
    }


}
