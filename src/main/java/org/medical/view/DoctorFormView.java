package org.medical.view;

import org.medical.controller.DoctorController;
import org.medical.util.Enums.DoctorSpecialization;

import javax.swing.*;
import java.awt.*;

public class DoctorFormView extends JFrame {

    public DoctorFormView(JFrame parent) {
        setTitle("Doctor Form");
        setSize(400, 250);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(parent);

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JComboBox<DoctorSpecialization> specializationBox =
                new JComboBox<>(DoctorSpecialization.values());

        JButton addBtn = new JButton("Add Doctor");

        add(new JLabel("Doctor ID"));
        add(idField);
        add(new JLabel("Doctor Name"));
        add(nameField);
        add(new JLabel("Specialization"));
        add(specializationBox);
        add(addBtn);
        add(new JLabel("")); // Filler for the empty grid cell

        addBtn.addActionListener(e -> {
            // Validate Doctor ID
            String idText = idField.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Doctor ID is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
                idField.requestFocusInWindow();
                return;
            }
            int id;
            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Doctor ID must be a valid integer", "Validation Error", JOptionPane.ERROR_MESSAGE);
                idField.requestFocusInWindow();
                idField.selectAll();
                return;
            }

            // Validate Doctor Name
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Doctor Name is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
                nameField.requestFocusInWindow();
                return;
            }

            // Specialization is always selected from the combo box, so no additional validation needed

            DoctorController.getInstance().createDoctor(id, name, (DoctorSpecialization) specializationBox.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Doctor Added Successfully");
            dispose();
        });

        setVisible(true);
    }
}