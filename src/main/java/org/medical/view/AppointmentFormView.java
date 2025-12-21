package org.medical.view;

import org.medical.controller.AppointmentController;
import org.medical.controller.DoctorController;
import org.medical.model.Doctor;
import org.medical.model.Appointment;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AppointmentFormView extends JFrame {

    public AppointmentFormView(JFrame parent) {
        setTitle("Appointment Form");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(parent);

        // Appointment ID input
        JTextField idField = new JTextField();

        // Patient name input
        JTextField patientNameField = new JTextField();

        // Doctor selection dropdown
        JComboBox<String> doctorCombo = new JComboBox<>();
        DoctorController.getInstance()
                .getAllDoctors()
                .forEach(d -> doctorCombo.addItem(d.getDoctorName()));

        JButton scheduleBtn = new JButton("Schedule");

        // Add components
        add(new JLabel("Appointment ID:"));
        add(idField);

        add(new JLabel("Patient Name:"));
        add(patientNameField);

        add(new JLabel("Doctor:"));
        add(doctorCombo);

        add(new JLabel()); // empty cell
        add(scheduleBtn);

        // Schedule button action
        scheduleBtn.addActionListener(e -> {

            String idText = idField.getText().trim();
            String patientName = patientNameField.getText().trim();
            String doctorName = (String) doctorCombo.getSelectedItem();

            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter appointment ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Integer id;
            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Appointment ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (patientName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter patient name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (doctorName == null) {
                JOptionPane.showMessageDialog(this, "No doctor selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Doctor doctor = DoctorController.getInstance().getDoctorByName(doctorName);

            if (doctor == null) {
                JOptionPane.showMessageDialog(this, "Selected doctor does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Appointment appointment = AppointmentController.getInstance()
                    .scheduleAppointment(id, patientName, doctor, LocalDateTime.now());

            if (appointment != null) {
                JOptionPane.showMessageDialog(this, "Appointment scheduled successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to schedule appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
