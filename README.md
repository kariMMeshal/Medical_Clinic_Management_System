# Medical Clinic Management System (Java)

A Java project simulating a medical clinic management system. The project is structured using **repositories**, **services**, **singleton**, and **factory** patterns for clean, maintainable code.

**Note:** No UI is included—interactions are handled programmatically via the `Main` class.

## Project Structure

```
src/main/java/org/medical
├── model/                  # Data models
│   ├── Appointment.java
│   ├── Doctor.java
│   ├── MedicalRecord.java
│   ├── Patient.java
│   └── ... (other specialized doctor and record classes)
├── repositories/           # Data access layer (CRUD operations)
│   ├── AppointmentRepository.java
│   ├── DoctorRepository.java
│   ├── MedicalRecordRepository.java
│   └── PatientRepository.java
├── service/                # Business logic layer
│   ├── AppointmentService.java
│   ├── DoctorService.java
│   ├── MedicalRecordService.java
│   └── PatientService.java
├── controller/             # Controller layer (delegates to services)
│   ├── AppointmentController.java
│   ├── DoctorController.java
│   ├── MedicalRecordController.java
│   └── PatientController.java
├── util/
│   ├── Enums/              # Enum types
│   │   ├── DoctorSpecialization.java
│   │   └── ReportType.java
│   ├── factory/            # Factory pattern for object creation
│   │   ├── DoctorFactory.java
│   │   └── MedicalReportFactory.java
│   └── singleton/          # Singleton utility classes
│       ├── AppointmentScheduler.java
│       └── DBManager.java
└── Main.java               # Entry point (client)

```

## Main Features

The project implements four main services, each interacting with a corresponding repository:

### 1. AppointmentService
- Schedule appointments with doctors.
- Cancel appointments.
- List all appointments.
- Uses **singleton** for repository and `AppointmentScheduler`.

### 2. DoctorService
- Add new doctors (General, Cardiologist, Neurologist, Lab, etc.).
- Update or remove doctors.
- List all doctors and filter by specialization.
- Uses **singleton** pattern for repository.
- Supports **factory** pattern via `DoctorFactory` for creating specialized doctors.

### 3. MedicalRecordService
- Create medical records for patients.
- Retrieve records by patient or record ID.
- Delete medical records.
- Uses **singleton** for repository.
- Uses **factory** pattern via `MedicalReportFactory` to generate different record types (`PatientHistoryRecord`, `PrescriptionRecord`, etc.).

### 4. PatientService
- Add, update, and delete patients.
- Retrieve patient information by ID.
- List all patients.
- Uses **singleton** for repository.

## Design Patterns

### 1. Singleton
- Ensures a single instance for repositories (`AppointmentRepository`, `DoctorRepository`, `MedicalRecordRepository`, `PatientRepository`) and services.
- Ensures consistent access to in-memory database via `DBManager`.

### 2. Factory
- Used to create specialized objects without exposing the creation logic:
    - `DoctorFactory` → creates different doctor types.
    - `MedicalReportFactory` → creates different medical record types (`PatientHistoryRecord`, `PrescriptionRecord`, etc.).

### 3. Builder
- Simplifies creation of complex objects with optional fields:
    - `PatientBuilder` → builds `Patient` objects incrementally, making patient creation flexible and readable.

### 4. Prototype
- Enables cloning of objects to create copies without re-initializing all fields:
    - `MedicalRecord` and its subclasses implement `Cloneable` → allows creating follow-up or template records easily while keeping originals unchanged.

### 5. MVC (Model-View-Controller)
- Organizes the application into three layers:
    - **Model**: Data and business logic (`Patient`, `Doctor`, `MedicalRecord`, `Appointment`).
    - **View**: Presentation/UI layer (e.g., future React or other front-end components).
    - **Controller**: Handles user input and communicates between Model and View.
- Ensures separation of concerns for maintainability and scalability.

### 6. Service Layer
- Encapsulates business logic and coordinates data access:
    - `PatientService`, `MedicalRecordService`, `DoctorService`, `AppointmentService`.
- Sits between controllers and repositories, providing a clean API for the application and supporting the MVC architecture.

## Running the Project

1. Clone the repository.
2. Import it into your Java IDE (e.g., IntelliJ IDEA).
3. Run the `Main.java` class.
4. All operations (CRUD for patients, doctors, appointments, and medical records) are executed programmatically.

### Example
```java
PatientRepository patientRepo = PatientRepository.getInstance();
PatientService patientService = PatientService.getInstance(patientRepo);

// Add patient
patientService.addPatient(new Patient(1, "Alice", 30, null));

// List all patients
for (Patient p : patientService.findAllPatients()) {
    System.out.println(p);
}
```

## Notes
- No GUI or web interface is provided yet.
- All data is stored in memory (runtime) using `DBManager`.
- Fully demonstrates clean architecture, service-repository pattern, and design patterns like Singleton and Factory.
- Easily extendable for adding new services, records, or doctor types.
