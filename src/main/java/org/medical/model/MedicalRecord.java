package org.medical.model;

import org.medical.util.Enums.ReportType;

import java.time.LocalDate;

public abstract class MedicalRecord {
    private Integer recordId;
    private Integer patientId;
    private LocalDate createdAt;
    private ReportType recordType;
    private String content;

    public MedicalRecord(Integer patientId, Integer recordId, LocalDate createdAt, ReportType recordType, String content) {
        this.patientId = patientId;
        this.recordId = recordId;
        this.createdAt = createdAt;
        this.recordType = recordType;
        this.content = content;
    }

    public MedicalRecord() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public ReportType getRecordType() {
        return recordType;
    }

    public void setRecordType(ReportType recordType) {
        this.recordType = recordType;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId=" + recordId +
                ", patientId=" + patientId +
                ", createdAt=" + createdAt +
                ", recordType=" + recordType +
                ", content='" + content + '\'' +
                '}';
    }
}
