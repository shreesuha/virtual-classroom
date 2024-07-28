import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Assignment {
    private String className;
    private String assignmentDetails;

    public Assignment(String className, String assignmentDetails) {
        this.className = className;
        this.assignmentDetails = assignmentDetails;
    }

    public String getClassName() {
        return className;
    }

    public String getAssignmentDetails() {
        return assignmentDetails;
    }

    public void display() {
        System.out.println("Class Name: " + className);
        System.out.println("Assignment Details: " + assignmentDetails);
    }
}

class Auth {
    public int validateClass(String id, String pass) {
        String username = "class";
        String password = "12345";
        if (username.equals(id) && password.equals(pass)) {
            return 1;
        }
        return 0;
    }

    public int validateStudent(String id, String pass) {
        String username = "student";
        String password = "12345";
        if (username.equals(id) && password.equals(pass)) {
            return 1;
        }
        return 0;
    }

    public int validateAssignment(String id, String pass) {
        String username = "assignment";
        String password = "12345";
        if (username.equals(id) && password.equals(pass)) {
            return 1;
        }
        return 0;
    }
}

class Classroom {
    private String className;

    public Classroom(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void display() {
        System.out.println("Classroom Name: " + className);
    }
}

class Student {
    private String studentId;
    private String className;

    public Student(String studentId, String className) {
        this.studentId = studentId;
        this.className = className;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getClassName() {
        return className;
    }

    public void display() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Class Name: " + className);
    }
}

public class ClassroomManagerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Classroom> classrooms = new HashMap<>();
        Map<String, List<Student>> studentsByClass = new HashMap<>();
        List<Assignment> assignments = new ArrayList<>();
        Auth auth = new Auth();
        String id;
        String pass;
        int check;

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Select User");
            System.out.println("2. Add Classroom");
            System.out.println("3. Add Student");
            System.out.println("4. Schedule Assignment");
            System.out.println("5. Submit Assignment");
            System.out.println("6. Display Classrooms");
            System.out.println("7. Display Students");
            System.out.println("8. Display Assignments");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    int userChoice = 0;
                    while (userChoice != 4) {
                        System.out.println("Select User:");
                        System.out.println("1. Classroom Management");
                        System.out.println("2. Student Management");
                        System.out.println("3. Assignment Management");
                        System.out.println("4. Back");
                        System.out.print("Enter your user choice: ");
                        userChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        switch (userChoice) {
                            case 1:
                                int classroomChoice = 0;
                                System.out.print("\nEnter Username: ");
                                id = scanner.next();
                                System.out.print("\nEnter Password: ");
                                pass = scanner.next();
                                check = auth.validateClass(id, pass);
                                if (check == 1) {
                                    // Classroom Management
                                    while (classroomChoice != 4) {
                                        System.out.println("Classroom Management Options:");
                                        System.out.println("1. Add Classrooms");
                                        System.out.println("2. List Classrooms");
                                        System.out.println("3. Remove Classrooms");
                                        System.out.println("4. Logout");
                                        System.out.print("Enter your choice: ");
                                        classroomChoice = scanner.nextInt();
                                        scanner.nextLine(); // Consume the newline character

                                        switch (classroomChoice) {
                                            case 1:
                                                // Add Classrooms
                                                System.out.print("Enter the class name: ");
                                                String newClassName = scanner.nextLine();
                                                classrooms.put(newClassName, new Classroom(newClassName));
                                                System.out.println("Classroom added.");
                                                break;
                                            case 2:
                                                // List Classrooms
                                                System.out.println("List of Classrooms:");
                                                for (String clsName : classrooms.keySet()) {
                                                    System.out.println(clsName);
                                                }
                                                break;
                                            case 3:
                                                // Remove Classrooms
                                                System.out.print("Enter the class name to remove: ");
                                                String classNameToRemove = scanner.nextLine();
                                                Classroom removedClassroom = classrooms.remove(classNameToRemove);
                                                if (removedClassroom != null) {
                                                    studentsByClass.remove(classNameToRemove);
                                                    System.out.println("Classroom removed.");
                                                } else {
                                                    System.out.println("Classroom not found.");
                                                }
                                                break;
                                            default:
                                                System.out.println("Invalid choice.");
                                        }
                                    }
                                } else {
                                    System.out.println("Invalid login.");
                                }
                                break;

                            case 2:
                                int studentChoice = 0;
                                System.out.print("\nEnter Username: ");
                                id = scanner.next();
                                System.out.print("\nEnter Password: ");
                                pass = scanner.next();
                                check = auth.validateStudent(id, pass);
                                if (check == 1) {
                                    while (studentChoice != 3) {
                                        // Student Management
                                        System.out.println("Student Management Options:");
                                        System.out.println("1. Enroll Students into Classrooms");
                                        System.out.println("2. List Students in Each Classroom");
                                        System.out.println("3. Logout");
                                        System.out.print("Enter your choice: ");
                                        studentChoice = scanner.nextInt();
                                        scanner.nextLine(); // Consume the newline character

                                        switch (studentChoice) {
                                            case 1:
                                                // Enroll Students into Classrooms
                                                System.out.print("Enter student ID: ");
                                                String studentIdToEnroll = scanner.nextLine();
                                                System.out.print("Enter class name: ");
                                                String classToEnroll = scanner.nextLine();
                                                Student studentToEnroll = new Student(studentIdToEnroll, classToEnroll);

                                                List<Student> enrolledStudents = studentsByClass.get(classToEnroll);
                                                if (enrolledStudents == null) {
                                                    enrolledStudents = new ArrayList<>();
                                                    studentsByClass.put(classToEnroll, enrolledStudents);
                                                }
                                                enrolledStudents.add(studentToEnroll);
                                                System.out.println("Student enrolled in the class.");
                                                break;
                                            case 2:
                                                // List Students in Each Classroom
                                                System.out.println("List of Students in Each Classroom:");
                                                for (Map.Entry<String, List<Student>> entry : studentsByClass.entrySet()) {
                                                    System.out.println("Classroom: " + entry.getKey());
                                                    List<Student> studentsInClass = entry.getValue();
                                                    for (Student s : studentsInClass) {
                                                        System.out.println("Student ID: " + s.getStudentId());
                                                    }
                                                }
                                                break;
                                            default:
                                                System.out.println("Invalid choice.");
                                        }
                                    }
                                } else {
                                    System.out.println("Invalid login.");
                                }
                                break;
                            case 3:
                                int assignmentUserChoice = 0;
                                System.out.print("\nEnter Username: ");
                                id = scanner.next();
                                System.out.print("\nEnter Password: ");
                                pass = scanner.next();
                                check = auth.validateAssignment(id, pass);
                                if (check == 1) {
                                    while (assignmentUserChoice != 3) {
                                        // Assignment Management
                                        System.out.println("Assignment Management Options:");
                                        System.out.println("1. Schedule Assignments for Classrooms");
                                        System.out.println("2. Allow Students to Submit Assignments");
                                        System.out.println("3. Logout");
                                        System.out.print("Enter your choice: ");
                                        assignmentUserChoice = scanner.nextInt();
                                        scanner.nextLine(); // Consume the newline character

                                        switch (assignmentUserChoice) {
                                            case 1:
                                                // Schedule Assignments for Classrooms
                                                System.out.print("Enter the class name: ");
                                                String assignmentClass = scanner.nextLine();
                                                System.out.print("Enter assignment details: ");
                                                String assignmentDetailsForSchedule = scanner.nextLine();
                                                Assignment assignmentForSchedule = new Assignment(assignmentClass, assignmentDetailsForSchedule);
                                                assignments.add(assignmentForSchedule);
                                                System.out.println("Assignment scheduled for the class.");
                                                break;
                                            case 2:
                                                // Allow Students to Submit Assignments
                                                System.out.print("Enter student ID: ");
                                                String studentIdForSubmit = scanner.nextLine();
                                                System.out.print("Enter class name: ");
                                                String classNameForSubmit = scanner.nextLine();
                                                System.out.print("Enter assignment details: ");
                                                String assignmentDetailsForSubmit = scanner.nextLine();

                                                boolean assignmentSubmitted = false;

                                                for (Assignment a : assignments) {
                                                    if (a.getClassName().equals(classNameForSubmit)
                                                            && a.getAssignmentDetails().equals(assignmentDetailsForSubmit)) {
                                                        System.out.println("Assignment marked as submitted.");
                                                        assignmentSubmitted = true;
                                                        break;
                                                    }
                                                }

                                                if (!assignmentSubmitted) {
                                                    System.out.println("Assignment not found.");
                                                }
                                                break;
                                            default:
                                                System.out.println("Invalid choice.");

                                        }
                                    }
                                } else {
                                    System.out.println("Invalid login.");
                                }
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter the class name: ");
                    String className = scanner.nextLine();
                    classrooms.put(className, new Classroom(className));
                    System.out.println("Classroom " + className + " has been created.");
                    break;
                case 3:
                    System.out.print("Enter the student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter the class name: ");
                    String studentClassName = scanner.nextLine();
                    Student student = new Student(studentId, studentClassName);

                    List<Student> students = studentsByClass.get(studentClassName);
                    if (students == null) {
                        students = new ArrayList<>();
                        studentsByClass.put(studentClassName, students);
                    }
                    students.add(student);

                    System.out.println("Student " + studentId + " has been enrolled in class " + studentClassName + ".");
                    break;
                case 4:
                    System.out.print("Enter the class name: ");
                    String assignmentClassName = scanner.nextLine();
                    System.out.print("Enter assignment details: ");
                    String assignmentDetails = scanner.nextLine();
                    Assignment assignment = new Assignment(assignmentClassName, assignmentDetails);
                    assignments.add(assignment);
                    System.out.println("Assignment for class " + assignmentClassName + " has been scheduled.");
                    break;
                case 5:
                    System.out.print("Enter student ID: ");
                    String studentIdForSubmission = scanner.nextLine();
                    System.out.print("Enter class name: ");
                    String classNameForSubmission = scanner.nextLine();
                    System.out.print("Enter assignment details: ");
                    String assignmentDetailsForSubmission = scanner.nextLine();

                    boolean assignmentSubmittedFlag = false;

                    for (Assignment a : assignments) {
                        if (a.getClassName().equals(classNameForSubmission)
                                && a.getAssignmentDetails().equals(assignmentDetailsForSubmission)) {
                            System.out.println("Assignment submitted by Student " + studentIdForSubmission + " in class " + classNameForSubmission + ".");
                            assignmentSubmittedFlag = true;
                            break;
                        }
                    }

                    if (!assignmentSubmittedFlag) {
                        System.out.println("Assignment not found.");
                    }
                    break;
                case 6:
                    // Display Classrooms
                    System.out.println("Classrooms:");
                    for (Classroom cls : classrooms.values()) {
                        cls.display();
                    }
                    break;
                case 7:
                    // Display Students
                    System.out.println("Students:");
                    for (List<Student> studentList : studentsByClass.values()) {
                        for (Student s : studentList) {
                            s.display();
                        }
                    }
                    break;
                case 8:
                    // Display Assignments
                    System.out.println("Assignments:");
                    for (Assignment a : assignments) {
                        a.display();
                    }
                    break;
                case 9:
                    // Exit the program
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
