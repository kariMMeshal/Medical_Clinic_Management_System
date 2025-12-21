package org.medical.view;

import org.medical.controller.PatientController;
import org.medical.model.Patient;
import org.medical.util.builder.PatientBuilder;

import javax.swing.*;
import java.awt.*;

public class PatientFormView extends JFrame {

    public PatientFormView(JFrame parent) {
        setTitle("Patient Form");
        setSize(400, 200);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(parent);

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField age = new JTextField();

        JButton addBtn = new JButton("Add Patient");

        add(new JLabel("Patient ID"));
        add(id);
        add(new JLabel("Patient Name"));
        add(name);
        add(new JLabel("Age"));
        add(age);
        add(addBtn);
        add(new JLabel("")); // Filler for the empty grid cell

        addBtn.addActionListener(e -> {
            // Validate Patient ID
            String idText = id.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient ID is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
                id.requestFocusInWindow();
                return;
            }
            int patientId;
            try {
                patientId = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Patient ID must be a valid integer", "Validation Error", JOptionPane.ERROR_MESSAGE);
                id.requestFocusInWindow();
                id.selectAll();
                return;
            }

            // Validate Patient Name
            String patientName = name.getText().trim();
            if (patientName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient Name is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
                name.requestFocusInWindow();
                return;
            }

            // Validate Age
            String ageText = age.getText().trim();
            if (ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Age is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
                age.requestFocusInWindow();
                return;
            }
            int patientAge;
            try {
                patientAge = Integer.parseInt(ageText);
                if (patientAge < 0 || patientAge > 150) {
                    JOptionPane.showMessageDialog(this, "Age must be between 0 and 150", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    age.requestFocusInWindow();
                    age.selectAll();
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a valid integer", "Validation Error", JOptionPane.ERROR_MESSAGE);
                age.requestFocusInWindow();
                age.selectAll();
                return;
            }

            Patient patient = new PatientBuilder()
                    .patientId(patientId)
                    .patientName(patientName)
                    .patientAge(patientAge)
                    .build();

            PatientController.getInstance().addPatient(patient);
            JOptionPane.showMessageDialog(this, "Patient Added");
            dispose();
        });

        setVisible(true);
    }
}