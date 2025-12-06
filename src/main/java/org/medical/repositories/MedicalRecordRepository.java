package org.medical.repositories;

import org.medical.model.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordRepository {

    private final List<MedicalRecord> records = new ArrayList<>();


    public void save(MedicalRecord record) {
        records.add(record);
    }


    public List<MedicalRecord> findAll() {
        return new ArrayList<>(records);
    }


    public MedicalRecord findById(int id) {
        return records.stream().filter(r -> r.getRecordId() == id).findFirst().orElse(null);
    }


    public void delete(MedicalRecord record) {
        records.remove(record);
    }
}
