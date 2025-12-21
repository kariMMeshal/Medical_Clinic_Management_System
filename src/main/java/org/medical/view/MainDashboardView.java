package org.medical.view;

import org.medical.controller.*;
import org.medical.model.*;
import org.medical.util.Enums.DoctorSpecialization;
import org.medical.util.Enums.ReportType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MainDashboardView extends JFrame {

    private static final Color PRIMARY_COLOR = new Color(0, 123, 255);
    private static final Color SELECTED_COLOR = new Color(0, 86, 179);
    private static final Color SIDEBAR_BG = new Color(52, 58, 64);
    private static final Color NORMAL_HOVER = new Color(73, 80, 87);
    private static final Color LIGHT_BG = new Color(248, 249, 250);
    private static final Color TABLE_ALT = new Color(248, 249, 250);

    private final DoctorController doctorController = DoctorController.getInstance();
    private final PatientController patientController = PatientController.getInstance();
    private final AppointmentController appointmentController = AppointmentController.getInstance();
    private final MedicalRecordController medicalRecordController = MedicalRecordController.getInstance();

    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JButton addButton, deleteButton;
    private JComboBox<String> filterComboBox;

    private JButton doctorBtn, appointmentBtn, patientBtn, recordBtn;
    private String currentCategory = "";
    private JFrame mainFrame;

    public MainDashboardView() {
        this.mainFrame = this;

        setTitle("Medical Clinic Management System");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(LIGHT_BG);

        initHeader();

        JPanel center = new JPanel(new BorderLayout(15, 0));
        center.setBackground(LIGHT_BG);
        initSidebar(center);
        center.add(initTablePanel(), BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        selectCategory("Doctor");
        setVisible(true);
    }

    /* ================= HEADER ================= */

    private void initHeader() {
        JLabel title = new JLabel("Medical Clinic Dashboard", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setBorder(new EmptyBorder(30, 20, 20, 20));
        add(title, BorderLayout.NORTH);
    }

    /* ================= SIDEBAR ================= */

    private void initSidebar(JPanel container) {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setBorder(new EmptyBorder(40, 20, 40, 20));

        doctorBtn = createSidebarButton("Doctors");
        appointmentBtn = createSidebarButton("Appointments");
        patientBtn = createSidebarButton("Patients");
        recordBtn = createSidebarButton("Medical Records");

        sidebar.add(doctorBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
        sidebar.add(appointmentBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
        sidebar.add(patientBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
        sidebar.add(recordBtn);

        doctorBtn.addActionListener(e -> selectCategory("Doctor"));
        appointmentBtn.addActionListener(e -> selectCategory("Appointment"));
        patientBtn.addActionListener(e -> selectCategory("Patient"));
        recordBtn.addActionListener(e -> selectCategory("Medical Record"));

        container.add(sidebar, BorderLayout.WEST);
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(SIDEBAR_BG);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMargin(new Insets(18, 15, 18, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btn.getBackground().equals(SELECTED_COLOR))
                    btn.setBackground(NORMAL_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btn.getBackground().equals(NORMAL_HOVER))
                    btn.setBackground(SIDEBAR_BG);
            }
        });

        return btn;
    }

    /* ================= TABLE ================= */

    private JPanel initTablePanel() {
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        dataTable.setRowHeight(35);
        dataTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        dataTable.setSelectionBackground(PRIMARY_COLOR);
        dataTable.setSelectionForeground(Color.WHITE);

        dataTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                c.setBackground(isSelected ? PRIMARY_COLOR : (row % 2 == 0 ? Color.WHITE : TABLE_ALT));
                c.setForeground(isSelected ? Color.WHITE : Color.BLACK);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(dataTable);

        addButton = new JButton("Add New");
        deleteButton = new JButton("Delete");

        addButton.setBackground(PRIMARY_COLOR);
        addButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);

        filterComboBox = new JComboBox<>();
        filterComboBox.addActionListener(e -> applyFilter());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.setBackground(LIGHT_BG);
        top.add(filterComboBox);
        top.add(addButton);
        top.add(deleteButton);

        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBackground(LIGHT_BG);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(top, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /* ================= CATEGORY ================= */

    private void selectCategory(String category) {
        currentCategory = category;

        doctorBtn.setBackground(SIDEBAR_BG);
        appointmentBtn.setBackground(SIDEBAR_BG);
        patientBtn.setBackground(SIDEBAR_BG);
        recordBtn.setBackground(SIDEBAR_BG);

        switch (category) {
            case "Doctor" -> {
                doctorBtn.setBackground(SELECTED_COLOR);
                showDoctors();
            }
            case "Patient" -> {
                patientBtn.setBackground(SELECTED_COLOR);
                showPatients();
            }
            case "Appointment" -> {
                appointmentBtn.setBackground(SELECTED_COLOR);
                showAppointments();
            }
            case "Medical Record" -> {
                recordBtn.setBackground(SELECTED_COLOR);
                showMedicalRecords();
            }
        }
    }

    /* ================= ADD / DELETE ================= */

    private void setupAddDeleteButtons(
            Supplier<JFrame> openForm,
            Consumer<Integer> deleteById,
            boolean enableDelete
    ) {
        for (var l : addButton.getActionListeners()) addButton.removeActionListener(l);
        for (var l : deleteButton.getActionListeners()) deleteButton.removeActionListener(l);

        addButton.setVisible(true);
        deleteButton.setVisible(enableDelete);

        addButton.addActionListener(e -> {
            JFrame form = openForm.get();
            form.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    selectCategory(currentCategory); // ✅ refresh AFTER close
                }
            });
        });

        if (enableDelete) {
            deleteButton.addActionListener(e -> {
                int row = dataTable.getSelectedRow();
                if (row >= 0) {
                    Integer id = (Integer) dataTable.getValueAt(row, 0);
                    deleteById.accept(id);
                    selectCategory(currentCategory);
                }
            });
        }
    }

    /* ================= VIEWS ================= */

    private void showDoctors() {
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Name", "Specialization"});
        tableModel.setRowCount(0);

        for (Doctor d : doctorController.getAllDoctors())
            tableModel.addRow(new Object[]{d.getDoctorId(), d.getDoctorName(), d.getDoctorSpecialization()});

        filterComboBox.removeAllItems();
        filterComboBox.addItem("All");
        for (DoctorSpecialization s : DoctorSpecialization.values())
            filterComboBox.addItem(s.toString());

        filterComboBox.setVisible(true);

        setupAddDeleteButtons(
                () -> new DoctorFormView(mainFrame),
                doctorController::deleteDoctorById,
                true
        );
    }

    private void showPatients() {
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Name", "Age"});
        tableModel.setRowCount(0);

        for (Patient p : patientController.getAllPatients())
            tableModel.addRow(new Object[]{p.getPatientId(), p.getPatientName(), p.getPatientAge()});

        filterComboBox.setVisible(false);

        setupAddDeleteButtons(
                () -> new PatientFormView(mainFrame),
                patientController::deletePatientById,
                true
        );
    }

    private void showAppointments() {
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Patient", "Doctor", "Time"});
        tableModel.setRowCount(0);

        for (Appointment a : appointmentController.getAllAppointments())
            tableModel.addRow(new Object[]{
                    a.getAppointmentId(),
                    a.getAppointmentPatient(),
                    a.getAppointmentDoctor(),
                    a.getAppointmentTime()
            });

        filterComboBox.setVisible(false);

        setupAddDeleteButtons(
                () -> new AppointmentFormView(mainFrame),
                appointmentController::cancelAppointment,
                true
        );
    }

    private void showMedicalRecords() {
        tableModel.setColumnIdentifiers(new Object[]{"Record ID", "Patient ID", "Type", "Content"});
        tableModel.setRowCount(0);

        for (MedicalRecord r : medicalRecordController.getAllReports())
            tableModel.addRow(new Object[]{
                    r.getRecordId(),
                    r.getPatientId(),
                    r.getRecordType(),
                    r.getContent()
            });

        filterComboBox.removeAllItems();
        filterComboBox.addItem("All");
        for (ReportType t : ReportType.values())
            filterComboBox.addItem(t.toString());

        filterComboBox.setVisible(true);

        setupAddDeleteButtons(
                () -> new MedicalRecordFormView(mainFrame),
                medicalRecordController::deleteReport,
                true
        );
    }

    private void applyFilter() {

        tableModel.setRowCount(0);

        if (currentCategory.equals("Doctor")) {
            String selected = (String) filterComboBox.getSelectedItem();

            for (Doctor d : doctorController.getAllDoctors()) {
                if ("All".equals(selected) ||
                        d.getDoctorSpecialization().toString().equals(selected)) {

                    tableModel.addRow(new Object[]{
                            d.getDoctorId(),
                            d.getDoctorName(),
                            d.getDoctorSpecialization()
                    });
                }
            }

        } else if (currentCategory.equals("Medical Record")) {
            String selected = (String) filterComboBox.getSelectedItem();

            for (MedicalRecord r : medicalRecordController.getAllReports()) {
                if ("All".equals(selected) ||
                        r.getRecordType().toString().equals(selected)) {

                    tableModel.addRow(new Object[]{
                            r.getRecordId(),
                            r.getPatientId(),
                            r.getRecordType(),
                            r.getContent()
                    });
                }
            }
        }
    }

}
