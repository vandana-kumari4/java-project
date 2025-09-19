import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem {
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    // ID counters
    private int nextPatientId = 1;
    private int nextDoctorId = 1;
    private int nextAppointmentId = 1;
    private int nextPrescriptionId = 1;

    public static void main(String[] args) {
        HospitalManagementSystem system = new HospitalManagementSystem();
        system.run();
    }

    public void run() {
        initializeSampleData();
        
        while (true) {
            System.out.println("\n=== HOSPITAL MANAGEMENT SYSTEM ===");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Prescription Management");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1: patientManagement(); break;
                case 2: doctorManagement(); break;
                case 3: appointmentManagement(); break;
                case 4: prescriptionManagement(); break;
                case 5: 
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default: 
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void initializeSampleData() {
        // Add sample doctors
        doctors.add(new Doctor(nextDoctorId++, "Dr. Smith", "Cardiology"));
        doctors.add(new Doctor(nextDoctorId++, "Dr. Johnson", "Neurology"));
        
        // Add sample patients
        patients.add(new Patient(nextPatientId++, "John Doe", 35, "Male", "123 Main St"));
        patients.add(new Patient(nextPatientId++, "Jane Smith", 28, "Female", "456 Oak Ave"));
    }

    // Helper method for integer input
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    // ========== PATIENT MANAGEMENT ==========
    private void patientManagement() {
        while (true) {
            System.out.println("\n=== PATIENT MANAGEMENT ===");
            System.out.println("1. Add New Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1: addPatient(); break;
                case 2: viewAllPatients(); break;
                case 3: searchPatient(); break;
                case 4: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addPatient() {
        System.out.println("\n--- ADD NEW PATIENT ---");
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter age: ");
        int age = getIntInput();
        
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        Patient patient = new Patient(nextPatientId++, name, age, gender, address);
        patients.add(patient);
        System.out.println("Patient added successfully! ID: " + patient.getId());
    }

    private void viewAllPatients() {
        System.out.println("\n--- ALL PATIENTS ---");
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        
        for (Patient patient : patients) {
            System.out.println(patient);
            System.out.println("---------------------");
        }
    }

    private void searchPatient() {
        System.out.print("\nEnter patient ID or name to search: ");
        String searchTerm = scanner.nextLine().toLowerCase();
        
        boolean found = false;
        for (Patient patient : patients) {
            if (String.valueOf(patient.getId()).equals(searchTerm) || 
                patient.getName().toLowerCase().contains(searchTerm)) {
                System.out.println("\n" + patient);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No matching patients found.");
        }
    }

    // ========== DOCTOR MANAGEMENT ==========
    private void doctorManagement() {
        while (true) {
            System.out.println("\n=== DOCTOR MANAGEMENT ===");
            System.out.println("1. Add New Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Search Doctor");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1: addDoctor(); break;
                case 2: viewAllDoctors(); break;
                case 3: searchDoctor(); break;
                case 4: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addDoctor() {
        System.out.println("\n--- ADD NEW DOCTOR ---");
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();
        
        Doctor doctor = new Doctor(nextDoctorId++, name, specialization);
        doctors.add(doctor);
        System.out.println("Doctor added successfully! ID: " + doctor.getId());
    }

    private void viewAllDoctors() {
        System.out.println("\n--- ALL DOCTORS ---");
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
            System.out.println("---------------------");
        }
    }

    private void searchDoctor() {
        System.out.print("\nEnter doctor ID or name to search: ");
        String searchTerm = scanner.nextLine().toLowerCase();
        
        boolean found = false;
        for (Doctor doctor : doctors) {
            if (String.valueOf(doctor.getId()).equals(searchTerm) || 
                doctor.getName().toLowerCase().contains(searchTerm)) {
                System.out.println("\n" + doctor);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No matching doctors found.");
        }
    }

    // ========== APPOINTMENT MANAGEMENT ==========
    private void appointmentManagement() {
        while (true) {
            System.out.println("\n=== APPOINTMENT MANAGEMENT ===");
            System.out.println("1. Schedule Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. View Patient Appointments");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1: scheduleAppointment(); break;
                case 2: viewAllAppointments(); break;
                case 3: viewPatientAppointments(); break;
                case 4: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void scheduleAppointment() {
        System.out.println("\n--- SCHEDULE APPOINTMENT ---");
        
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Cannot schedule appointment. Need at least 1 patient and 1 doctor.");
            return;
        }
        
        // Select Patient
        System.out.println("\nAvailable Patients:");
        viewAllPatients();
        System.out.print("Enter patient ID: ");
        int patientId = getIntInput();
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        
        // Select Doctor
        System.out.println("\nAvailable Doctors:");
        viewAllDoctors();
        System.out.print("Enter doctor ID: ");
        int doctorId = getIntInput();
        Doctor doctor = findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }
        
        // Get Appointment Time
        LocalDateTime appointmentTime = null;
        while (appointmentTime == null) {
            try {
                System.out.print("Enter date/time (YYYY-MM-DD HH:MM): ");
                String dateTimeStr = scanner.nextLine();
                appointmentTime = LocalDateTime.parse(dateTimeStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please use YYYY-MM-DD HH:MM format.");
            }
        }
        
        System.out.print("Enter reason for appointment: ");
        String reason = scanner.nextLine();
        
        Appointment appointment = new Appointment(
            nextAppointmentId++, 
            patient, 
            doctor, 
            appointmentTime, 
            reason
        );
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully! ID: " + appointment.getId());
    }

    private void viewAllAppointments() {
        System.out.println("\n--- ALL APPOINTMENTS ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
            System.out.println("---------------------");
        }
    }

    private void viewPatientAppointments() {
        System.out.print("\nEnter patient ID to view appointments: ");
        int patientId = getIntInput();
        
        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId() == patientId) {
                System.out.println("\n" + appointment);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No appointments found for this patient.");
        }
    }

    // ========== PRESCRIPTION MANAGEMENT ==========
    private void prescriptionManagement() {
        while (true) {
            System.out.println("\n=== PRESCRIPTION MANAGEMENT ===");
            System.out.println("1. Create Prescription");
            System.out.println("2. View Patient Prescriptions");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1: createPrescription(); break;
                case 2: viewPatientPrescriptions(); break;
                case 3: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createPrescription() {
        System.out.println("\n--- CREATE PRESCRIPTION ---");
        
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Cannot create prescription. Need at least 1 patient and 1 doctor.");
            return;
        }
        
        // Select Patient
        System.out.println("\nAvailable Patients:");
        viewAllPatients();
        System.out.print("Enter patient ID: ");
        int patientId = getIntInput();
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        
        // Select Doctor
        System.out.println("\nAvailable Doctors:");
        viewAllDoctors();
        System.out.print("Enter doctor ID: ");
        int doctorId = getIntInput();
        Doctor doctor = findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }
        
        System.out.print("Enter instructions: ");
        String instructions = scanner.nextLine();
        
        Prescription prescription = new Prescription(
            nextPrescriptionId++, 
            doctor, 
            patient, 
            LocalDateTime.now(), 
            instructions
        );
        
        // Add Medications
        while (true) {
            System.out.print("\nAdd medication? (Y/N): ");
            String choice = scanner.nextLine().toUpperCase();
            if (!choice.equals("Y")) break;
            
            System.out.print("Medication name: ");
            String name = scanner.nextLine();
            
            System.out.print("Dosage: ");
            String dosage = scanner.nextLine();
            
            System.out.print("Frequency: ");
            String frequency = scanner.nextLine();
            
            System.out.print("Duration (days): ");
            int duration = getIntInput();
            
            prescription.addMedication(new Medication(name, dosage, frequency, duration));
        }
        
        patient.addPrescription(prescription);
        System.out.println("\nPrescription created successfully! ID: " + prescription.getId());
    }

    private void viewPatientPrescriptions() {
        System.out.print("\nEnter patient ID to view prescriptions: ");
        int patientId = getIntInput();
        
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        
        System.out.println("\n--- PRESCRIPTIONS FOR " + patient.getName().toUpperCase() + " ---");
        if (patient.getPrescriptions().isEmpty()) {
            System.out.println("No prescriptions found.");
            return;
        }
        
        for (Prescription prescription : patient.getPrescriptions()) {
            System.out.println(prescription);
            System.out.println("---------------------");
        }
    }

    // ========== HELPER METHODS ==========
    private Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    private Doctor findDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }
}

class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private ArrayList<Prescription> prescriptions;

    public Patient(int id, String name, int age, String gender, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.prescriptions = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getAddress() { return address; }
    public ArrayList<Prescription> getPrescriptions() { return prescriptions; }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    @Override
    public String toString() {
        return String.format(
            "PATIENT ID: %d\nName: %s\nAge: %d\nGender: %s\nAddress: %s",
            id, name, age, gender, address
        );
    }
}

class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return String.format(
            "DOCTOR ID: %d\nName: %s\nSpecialization: %s",
            id, name, specialization
        );
    }
}

class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentTime;
    private String reason;
    private boolean completed;

    public Appointment(int id, Patient patient, Doctor doctor, 
                      LocalDateTime appointmentTime, String reason) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.completed = false;
    }

    public int getId() { return id; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public String getReason() { return reason; }
    public boolean isCompleted() { return completed; }
    
    public void markAsCompleted() { completed = true; }

    @Override
    public String toString() {
        return String.format(
            "APPOINTMENT ID: %d\nPatient: %s\nDoctor: %s\nTime: %s\nReason: %s\nStatus: %s",
            id, patient.getName(), doctor.getName(), 
            appointmentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            reason,
            completed ? "COMPLETED" : "PENDING"
        );
    }
}

class Prescription {
    private int id;
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime date;
    private ArrayList<Medication> medications;
    private String instructions;

    public Prescription(int id, Doctor doctor, Patient patient, 
                       LocalDateTime date, String instructions) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.instructions = instructions;
        this.medications = new ArrayList<>();
    }

    public int getId() { return id; }
    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
            "PRESCRIPTION ID: %d\nDate: %s\nDoctor: %s\nPatient: %s\n",
            id, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            doctor.getName(), patient.getName()
        ));
        
        if (!medications.isEmpty()) {
            sb.append("\nMEDICATIONS:\n");
            for (Medication med : medications) {
                sb.append("- ").append(med).append("\n");
            }
        }
        
        sb.append("\nInstructions: ").append(instructions);
        return sb.toString();
    }
}

class Medication {
    private String name;
    private String dosage;
    private String frequency;
    private int duration; // in days

    public Medication(String name, String dosage, String frequency, int duration) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format(
            "%s - %s - %s - Duration: %d days",
            name, dosage, frequency, duration
        );
    }
}