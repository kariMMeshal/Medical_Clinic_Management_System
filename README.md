# Medical Clinic Management System (Java)

## Project Documentation

### 1. Project Overview

The Medical Clinic Management System is a Java application that simulates the core operations of a medical clinic. It demonstrates clean architecture principles and uses repositories, services, controllers, and multiple design patterns to ensure maintainability, scalability, and clarity.

The system follows a layered architecture and was originally designed for programmatic interaction. It has been extended to support a graphical user interface (GUI) through a dedicated View layer, enabling user-friendly interaction while preserving the separation of concerns.


### 2. Project Structure

```
src/main/java/org/medical
├── model/                  # Data models
├── repositories/           # CRUD data access
├── service/                # Business logic
├── controller/             # Input handling & delegation
├── view/                   # GUI (Swing-based View Layer)
├── util/
│   ├── Enums/
│   ├── factory/
│   └── singleton/
└── Main.java               # Entry point
```

### 3. Class Descriptions

#### 3.1 Model Classes

- **Patient.java** – Represents a patient with attributes such as ID, name, age, and medical records.
- **Doctor.java** – Represents doctors with specialization, availability, and personal details.
- **Appointment.java** – Contains appointment details linking patients and doctors.
- **MedicalRecord.java** – Base class for patient medical records, including history, prescriptions, and test results.
- **Specialized subclasses** – Cardiologist, Neurologist, PrescriptionRecord, PatientHistoryRecord, and other specialized medical record or doctor types.

#### 3.2 Repository Classes

- **AppointmentRepository.java**
- **DoctorRepository.java**
- **MedicalRecordRepository.java**
- **PatientRepository.java**

These classes handle CRUD operations and data storage. All repositories are implemented using the Singleton pattern to ensure a single source of truth and consistent data access throughout the system.

#### 3.3 Service Classes

- **AppointmentService.java**  
  Handles scheduling, cancellation, and listing of appointments. Internally uses AppointmentScheduler to manage appointment rules and conflicts.

- **DoctorService.java**  
  Manages doctor creation, updates, deletion, and filtering by specialization. Uses the Factory pattern to create specialized doctor instances.

- **MedicalRecordService.java**  
  Creates, retrieves, deletes, and clones medical records. Uses Factory for record creation and Prototype for cloning records to support follow-ups.

- **PatientService.java**  
  Adds, updates, deletes, and lists patients. Interacts with a Singleton patient repository.

- **InsuranceService.java**  
  Handles insurance-related operations such as insurance validation, coverage checks, and claim eligibility. This service communicates with external insurance systems through adapters to unify different insurance provider interfaces.

#### 3.4 Controller Classes

Controllers act as intermediaries between the View layer and the Service layer.

- **AppointmentController.java**
- **DoctorController.java**
- **PatientController.java**
- **InsuranceController.java**

They receive input from Main or GUI views, validate it, and delegate requests to the appropriate service without containing business logic.

#### 3.5 View Layer (GUI)

<img width="1715" height="972" alt="image" src="https://github.com/user-attachments/assets/a24c35f3-60c4-4e89-940b-cc19144b45b4" />


**Responsibilities of the View Layer:**

- Display data to the user using tables, forms, and dropdown menus.
- Capture user input (add, delete, select, schedule actions).
- Forward user actions to controllers.
- Refresh displayed data after operations.

The View layer contains no business logic and communicates only with controllers, ensuring strict adherence to MVC principles.

#### 3.6 Utility Classes

**Enums**

- **DoctorSpecialization.java**
- **ReportType.java**

Provide strong-typed values and improve code readability.

**Factory**

- **DoctorFactory.java**
- **MedicalReportFactory.java**

Encapsulate object creation logic and hide instantiation details.

**Singleton**

- **AppointmentScheduler.java**
- **DBManager.java**

Ensure centralized scheduling and database access using a single instance.

### 4. Design Patterns & Justifications

| Pattern    | Where Used                          | Justification |
|------------|-------------------------------------|---------------|
| Singleton | Repositories, DBManager, AppointmentScheduler | Ensures one instance of critical components, maintains data consistency, and prevents duplicate repositories or managers. |
| Factory   | DoctorFactory, MedicalReportFactory | Encapsulates object creation logic and allows easy extension without modifying existing client code. |
| Builder   | PatientBuilder                      | Simplifies the creation of Patient objects with optional fields and improves readability and flexibility. |
| Prototype | MedicalRecord subclasses             | Supports cloning of medical records for follow-ups or templates without reinitializing all fields. |
| MVC       | Models, Views, Controllers          | Separates concerns: Models handle data, Views handle UI, Controllers delegate actions, enabling maintainability and scalability. |
| Service Layer | *Service.java classes              | Encapsulates business logic and decouples controllers from repositories. |
| Adapter   | Insurance integration                | Adapts external insurance systems with incompatible interfaces into a unified internal interface, enabling seamless integration without modifying core logic. |

### 5. Main Features

- **Appointment Management** – Schedule, cancel, and list appointments.
- **Doctor Management** – Add, update, remove, and filter doctors by specialization.
- **Medical Record Management** – Create, retrieve, clone, and delete medical records.
- **Patient Management** – Add, update, delete, and list patients.
- **Insurance Handling** – Validate insurance data and manage coverage using adapters.

### 6. Example Usage via Controller

```java
PatientController patientController = new PatientController();
DoctorController doctorController = new DoctorController();
AppointmentController appointmentController = new AppointmentController();

// Add patient
patientController.addPatient(1, "Alice", 30);

// Add doctor
doctorController.addDoctor("Dr. Smith", "Cardiologist");

// Schedule appointment
appointmentController.scheduleAppointment(1, 1, "2025-12-20 10:00");

// List all patients
for (Patient p : patientController.getAllPatients()) {
    System.out.println(p);
}
```

### 7. Benefits & Extensibility

- Centralized and consistent data handling using Singleton.
- Easy addition of new doctor types and medical records using Factory.
- Cloning medical records for follow-ups using Prototype.
- Flexible object construction using Builder.
- Clean separation of concerns using MVC and Service Layer.
- Seamless integration of external insurance systems using Adapter.
- Fully extendable for future enhancements such as database persistence, REST APIs, or advanced UI frameworks.
