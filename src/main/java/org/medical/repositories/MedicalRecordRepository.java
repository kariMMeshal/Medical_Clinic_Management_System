package org.medical.repositories;

import org.medical.model.MedicalRecord;
import org.medical.util.singleton.DBManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedicalRecordRepository {

    private static volatile MedicalRecordRepository instance;
    private final DBManager dbManager = DBManager.getInstance();
    private final List<MedicalRecord> records = dbManager.getRecords();

    private MedicalRecordRepository() {
    }

    public static MedicalRecordRepository getInstance() {
        if (instance == null) {
            synchronized (MedicalRecordRepository.class) {
                if (instance == null) {
                    instance = new MedicalRecordRepository();
                }
            }
        }
        return instance;
    }


    public boolean save(MedicalRecord record) {
        return records.add(record);
    }


    public List<MedicalRecord> findAll() {
        return new ArrayList<>(records);
    }

    // Find records by patient ID
    public List<MedicalRecord> findByPatientId(Integer patientId) {
        List<MedicalRecord> result = new ArrayList<>();
        for (MedicalRecord record : records) {
            if (Objects.equals(record.getPatientId(), patientId)) {
                result.add(record);
            }
        }
        return result;
    }


    public MedicalRecord findById(int id) {
        return records.stream().filter(r -> r.getRecordId() == id).findFirst().orElse(null);
    }


    public boolean deleteById(Integer id) {
        return records.removeIf(r -> Objects.equals(r.getRecordId(), id));
    }
}
