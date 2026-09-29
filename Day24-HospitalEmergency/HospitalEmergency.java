import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Patient {

    private int patientId;
    private String name;
    private int age;
    private String condition;
    private int priority;
    private boolean treated;

    public Patient(int patientId, String name, int age,
                   String condition, int priority) {

        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.priority = priority;
        this.treated = false;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isTreated() {
        return treated;
    }

    public void treat() {
        treated = true;
    }

    public void display() {

        String status = treated
                ? "Treated"
                : "Waiting";

        System.out.println("\n------------------------------");
        System.out.println("🆔 Patient ID: " + patientId);
        System.out.println("👤 Name: " + name);
        System.out.println("🎂 Age: " + age);
        System.out.println("🩺 Condition: " + condition);
        System.out.println("🚨 Priority: " + priority);
        System.out.println("📋 Status: " + status);
        System.out.println("------------------------------");
    }
}

class Doctor {

    private int doctorId;
    private String name;
    private String specialization;
    private boolean available;

    public Doctor(int doctorId, String name,
                  String specialization) {

        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.available = true;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void display() {

        String status = available
                ? "Available"
                : "Busy";

        System.out.println(
            doctorId
            + " | Dr. "
            + name
            + " | "
            + specialization
            + " | "
            + status
        );
    }
}

public class HospitalEmergency {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static ArrayList<Patient> patients =
        new ArrayList<>();

    static ArrayList<Doctor> doctors =
        new ArrayList<>();

    static HashMap<Integer, Integer> assignments =
        new HashMap<>();

    static int nextPatientId = 1001;
    static double totalRevenue = 0;

    static void createDoctors() {

        doctors.add(
            new Doctor(
                1,
                "Amit",
                "Emergency"
            )
        );

        doctors.add(
            new Doctor(
                2,
                "Priya",
                "Cardiology"
            )
        );

        doctors.add(
            new Doctor(
                3,
                "Rahul",
                "Neurology"
            )
        );

        doctors.add(
            new Doctor(
                4,
                "Sneha",
                "General Medicine"
            )
        );
    }

    static void registerPatient() {

        sc.nextLine();

        System.out.print(
            "\nEnter patient name: "
        );

        String name = sc.nextLine();

        if (name.trim().isEmpty()) {

            System.out.println(
                "❌ Name cannot be empty!"
            );

            return;
        }

        System.out.print(
            "Enter age: "
        );

        int age = sc.nextInt();

        if (age <= 0 || age > 120) {

            System.out.println(
                "❌ Invalid age!"
            );

            return;
        }

        sc.nextLine();

        System.out.print(
            "Enter condition: "
        );

        String condition = sc.nextLine();

        System.out.println(
            "\nPriority Level:"
        );

        System.out.println(
            "1. 🔴 Critical"
        );

        System.out.println(
            "2. 🟠 Serious"
        );

        System.out.println(
            "3. 🟢 Normal"
        );

        System.out.print(
            "Choose priority: "
        );

        int priority = sc.nextInt();

        if (priority < 1 || priority > 3) {

            System.out.println(
                "❌ Invalid priority!"
            );

            return;
        }

        Patient patient =
            new Patient(
                nextPatientId++,
                name,
                age,
                condition,
                priority
            );

        patients.add(patient);

        System.out.println(
            "\n✅ Patient registered successfully!"
        );

        System.out.println(
            "🆔 Patient ID: "
            + patient.getPatientId()
        );
    }

    static void showPatients() {

        System.out.println("\n================================");
        System.out.println("        🏥 PATIENT LIST");
        System.out.println("================================");

        if (patients.isEmpty()) {

            System.out.println(
                "No patients registered."
            );

            return;
        }

        for (Patient patient : patients) {
            patient.display();
        }
    }

    static void showDoctors() {

        System.out.println("\n================================");
        System.out.println("        👨‍⚕️ DOCTORS");
        System.out.println("================================");

        for (Doctor doctor : doctors) {
            doctor.display();
        }
    }

    static Patient findPatient(int patientId) {

        for (Patient patient : patients) {

            if (patient.getPatientId() == patientId) {
                return patient;
            }
        }

        return null;
    }

    static Doctor findDoctor(int doctorId) {

        for (Doctor doctor : doctors) {

            if (doctor.getDoctorId() == doctorId) {
                return doctor;
            }
        }

        return null;
    }

    static void assignDoctor() {

        showPatients();

        System.out.print(
            "\nEnter patient ID: "
        );

        int patientId = sc.nextInt();

        Patient patient =
            findPatient(patientId);

        if (patient == null) {

            System.out.println(
                "❌ Patient not found!"
            );

            return;
        }

        if (patient.isTreated()) {

            System.out.println(
                "❌ Patient has already been treated."
            );

            return;
        }

        showDoctors();

        System.out.print(
            "\nEnter doctor ID: "
        );

        int doctorId = sc.nextInt();

        Doctor doctor =
            findDoctor(doctorId);

        if (doctor == null) {

            System.out.println(
                "❌ Doctor not found!"
            );

            return;
        }

        if (!doctor.isAvailable()) {

            System.out.println(
                "❌ Doctor is currently busy."
            );

            return;
        }

        doctor.setAvailable(false);

        assignments.put(
            patientId,
            doctorId
        );

        System.out.println(
            "\n✅ Dr. "
            + doctor.getName()
            + " assigned to "
            + patient.getName()
        );
    }

    static void treatPatient() {

        showPatients();

        System.out.print(
            "\nEnter patient ID: "
        );

        int patientId = sc.nextInt();

        Patient patient =
            findPatient(patientId);

        if (patient == null) {

            System.out.println(
                "❌ Patient not found!"
            );

            return;
        }

        if (patient.isTreated()) {

            System.out.println(
                "❌ Patient already treated."
            );

            return;
        }

        if (!assignments.containsKey(patientId)) {

            System.out.println(
                "❌ Assign a doctor first."
            );

            return;
        }

        int doctorId =
            assignments.get(patientId);

        Doctor doctor =
            findDoctor(doctorId);

        patient.treat();

        doctor.setAvailable(true);

        assignments.remove(patientId);

        int bill =
            500 + random.nextInt(1501);

        totalRevenue += bill;

        System.out.println(
            "\n✅ Treatment completed!"
        );

        System.out.println(
            "👨‍⚕️ Doctor: Dr. "
            + doctor.getName()
        );

        System.out.println(
            "💰 Treatment Bill: ₹"
            + bill
        );
    }

    static void emergencyEvent() {

        int event = random.nextInt(4);

        System.out.println(
            "\n🚨 EMERGENCY ALERT"
        );

        if (event == 0) {

            System.out.println(
                "🚑 Ambulance has arrived with a new patient!"
            );

        } else if (event == 1) {

            System.out.println(
                "❤️ Emergency cardiac case reported!"
            );

        } else if (event == 2) {

            System.out.println(
                "🧠 Neurological emergency reported!"
            );

        } else {

            System.out.println(
                "🏥 Emergency department is operating normally."
            );
        }
    }

    static void showReport() {

        int treated = 0;
        int waiting = 0;

        for (Patient patient : patients) {

            if (patient.isTreated()) {
                treated++;
            } else {
                waiting++;
            }
        }

        System.out.println("\n================================");
        System.out.println("       📊 HOSPITAL REPORT");
        System.out.println("================================");

        System.out.println(
            "👥 Total Patients: "
            + patients.size()
        );

        System.out.println(
            "✅ Treated: "
            + treated
        );

        System.out.println(
            "⏳ Waiting: "
            + waiting
        );

        System.out.println(
            "👨‍⚕️ Doctors: "
            + doctors.size()
        );

        System.out.println(
            "📋 Active Assignments: "
            + assignments.size()
        );

        System.out.printf(
            "💰 Total Revenue: ₹%.2f%n",
            totalRevenue
        );
    }

    public static void main(String[] args) {

        createDoctors();

        System.out.println("================================");
        System.out.println("    🏥 HOSPITAL EMERGENCY");
        System.out.println("          SIMULATOR");
        System.out.println("================================");

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("             MENU");
            System.out.println("================================");

            System.out.println("1. 📝 Register Patient");
            System.out.println("2. 👥 View Patients");
            System.out.println("3. 👨‍⚕️ View Doctors");
            System.out.println("4. 📋 Assign Doctor");
            System.out.println("5. 💊 Treat Patient");
            System.out.println("6. 🚨 Emergency Event");
            System.out.println("7. 📊 Hospital Report");
            System.out.println("8. 🚪 Exit");

            System.out.print("\nChoose option: ");

            int choice = sc.nextInt();

            if (choice == 1) {

                registerPatient();

            } else if (choice == 2) {

                showPatients();

            } else if (choice == 3) {

                showDoctors();

            } else if (choice == 4) {

                assignDoctor();

            } else if (choice == 5) {

                treatPatient();

            } else if (choice == 6) {

                emergencyEvent();

            } else if (choice == 7) {

                showReport();

            } else if (choice == 8) {

                running = false;

                System.out.println(
                    "\n🏥 Hospital simulator closed."
                );

                showReport();

            } else {

                System.out.println(
                    "\n❌ Invalid option!"
                );
            }
        }

        System.out.println(
            "\n👋 Thank you for using Hospital Emergency Simulator!"
        );

        sc.close();
    }
}