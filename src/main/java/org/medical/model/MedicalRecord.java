package org.medical.model;

import java.time.LocalDate;

public abstract class MedicalRecord {
    private Integer recordId;
    private Integer patientId;
    private LocalDate createdAt;
    private String notes;

    public MedicalRecord(Integer recordId, String notes, LocalDate createdAt, Integer patientId) {
        this.recordId = recordId;
        this.notes = notes;
        this.createdAt = createdAt;
        this.patientId = patientId;
    }

    public MedicalRecord() {
    }

    public abstract String createSummary();

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId=" + recordId +
                ", patientId=" + patientId +
                ", createdAt=" + createdAt +
                ", notes='" + notes + '\'' +
                '}';
    }
}
