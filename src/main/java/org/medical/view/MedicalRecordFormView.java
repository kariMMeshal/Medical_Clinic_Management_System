package org.medical.view;

import org.medical.controller.MedicalRecordController;
import org.medical.model.MedicalRecord;
import org.medical.util.Enums.ReportType;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MedicalRecordFormView extends JFrame {

    private final MedicalRecordController medicalRecordController;

    private JTextField patientIdField;
    private JTextField recordIdField;
    private JTextArea contentArea;
    private JComboBox<ReportType> reportTypeCombo;

    public MedicalRecordFormView(JFrame parent) {
        this.medicalRecordController = MedicalRecordController.getInstance();

        setTitle("Medical Record Management");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);


        initUI();
        setVisible(true);
    }

    private void initUI() {

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // -------- Fields --------
        patientIdField = new JTextField();
        recordIdField = new JTextField();
        contentArea = new JTextArea(4, 20);
        reportTypeCombo = new JComboBox<>(ReportType.values());

        panel.add(new JLabel("Patient ID:"));
        panel.add(patientIdField);

        panel.add(new JLabel("Record ID:"));
        panel.add(recordIdField);

        panel.add(new JLabel("Report Type:"));
        panel.add(reportTypeCombo);

        panel.add(new JLabel("Content:"));
        panel.add(new JScrollPane(contentArea));

        // -------- Buttons --------
        JButton createBtn = new JButton("Create Record");
        JButton deleteBtn = new JButton("Delete Record");

        panel.add(createBtn);
        panel.add(deleteBtn);

        add(panel);

        // -------- Button Actions --------
        createBtn.addActionListener(e -> createRecord());
        deleteBtn.addActionListener(e -> deleteRecord());
    }

    // ---------------- CONTROLLER CALLS ----------------

    private void createRecord() {
        // Validate Patient ID
        String patientIdText = patientIdField.getText().trim();
        if (patientIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            patientIdField.requestFocusInWindow();
            return;
        }
        int patientId;
        try {
            patientId = Integer.parseInt(patientIdText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Patient ID must be a valid integer", "Validation Error", JOptionPane.ERROR_MESSAGE);
            patientIdField.requestFocusInWindow();
            patientIdField.selectAll();
            return;
        }

        // Validate Record ID
        String recordIdText = recordIdField.getText().trim();
        if (recordIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Record ID is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            recordIdField.requestFocusInWindow();
            return;
        }
        int recordId;
        try {
            recordId = Integer.parseInt(recordIdText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Record ID must be a valid integer", "Validation Error", JOptionPane.ERROR_MESSAGE);
            recordIdField.requestFocusInWindow();
            recordIdField.selectAll();
            return;
        }

        // Validate Content
        String content = contentArea.getText().trim();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Content is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            contentArea.requestFocusInWindow();
            return;
        }

        // Specialization is always selected from the combo box, so no additional validation needed

        try {
            ReportType type = (ReportType) reportTypeCombo.getSelectedItem();

            MedicalRecord record = medicalRecordController.createReport(
                    patientId,
                    recordId,
                    LocalDate.now(),
                    type,
                    content
            );

            if (record != null) {
                JOptionPane.showMessageDialog(this,
                        "Medical record created successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error creating record",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRecord() {
        // Validate Record ID
        String recordIdText = recordIdField.getText().trim();
        if (recordIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Record ID is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            recordIdField.requestFocusInWindow();
            return;
        }
        int recordId;
        try {
            recordId = Integer.parseInt(recordIdText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Record ID must be a valid integer", "Validation Error", JOptionPane.ERROR_MESSAGE);
            recordIdField.requestFocusInWindow();
            recordIdField.selectAll();
            return;
        }

        try {
            boolean deleted = medicalRecordController.deleteReport(recordId);

            JOptionPane.showMessageDialog(this,
                    deleted ? "Record deleted successfully" : "Record not found",
                    "Result",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error deleting record",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}