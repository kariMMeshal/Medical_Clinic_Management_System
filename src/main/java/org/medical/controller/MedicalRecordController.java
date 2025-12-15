package org.medical.controller;

import org.medical.model.MedicalRecord;
import org.medical.repositories.MedicalRecordRepository;
import org.medical.service.MedicalRecordService;
import org.medical.util.Enums.ReportType;

import java.time.LocalDate;
import java.util.List;

/**
 * Singleton Controller responsible for:
 * - Creating MedicalRecordRepository
 * - Creating MedicalRecordService
 * - Delegating medical record operations
 */
public class MedicalRecordController {

    private static volatile MedicalRecordController instance;
    private final MedicalRecordService medicalRecordService;

    private MedicalRecordController() {
        // Internal wiring
        MedicalRecordRepository medicalRecordRepository = MedicalRecordRepository.getInstance();
        this.medicalRecordService = MedicalRecordService.getInstance(medicalRecordRepository);
    }

    public static MedicalRecordController getInstance() {
        if (instance == null) {
            synchronized (MedicalRecordController.class) {
                if (instance == null) {
                    instance = new MedicalRecordController();
                }
            }
        }
        return instance;
    }

    // ---------- Delegation Methods ----------

    public MedicalRecord createReport(Integer patientId,
                                      Integer recordId,
                                      LocalDate createdAt,
                                      ReportType recordType,
                                      String content) {
        return medicalRecordService.createReport(patientId, recordId, createdAt, recordType, content);
    }

    // ---------- Option 2: Accept ready object ----------
    public boolean saveReport(MedicalRecord record) {
        return medicalRecordService.saveReport(record);
    }

    public List<MedicalRecord> getAllReports() {
        return medicalRecordService.findAllReports();
    }

    public List<MedicalRecord> getReportsByPatientId(Integer patientId) {
        return medicalRecordService.findReportsByPatient(patientId);
    }

    public MedicalRecord getReportById(Integer recordId) {
        return medicalRecordService.findReportById(recordId);
    }

    public boolean deleteReport(Integer recordId) {
        return medicalRecordService.deleteReport(recordId);
    }
}
