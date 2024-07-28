import java.util.*;

class VirtualClassroomManager {
    private final Map<String, Classroom> classrooms;

    public VirtualClassroomManager() {
        this.classrooms = new HashMap<>();
    }

    // Add a new classroom
    public void addClassroom(String className) {
        if (classrooms.containsKey(className)) {
            System.out.println("Classroom " + className + " already exists.");
        } else {
            classrooms.put(className, new Classroom(className));
            System.out.println("Classroom " + className + " has been created.");
        }
    }

    // Add a student to a classroom
    public void addStudent(String studentId, String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            System.out.println("Classroom " + className + " does not exist.");
        } else {
            classroom.addStudent(new Student(studentId));
            System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
        }
    }

    // Schedule an assignment for a classroom
    public void scheduleAssignment(String className, String assignmentDetails) {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            System.out.println("Classroom " + className + " does not exist.");
        } else {
            classroom.scheduleAssignment(new Assignment(assignmentDetails));
            System.out.println("Assignment for " + className + " has been scheduled.");
        }
    }

    // Submit an assignment for a student in a classroom
    public void submitAssignment(String studentId, String className, String assignmentDetails) {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            System.out.println("Classroom " + className + " does not exist.");
        } else {
            if (classroom.submitAssignment(studentId, assignmentDetails)) {
                System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
            } else {
                System.out.println("Submission failed for Student " + studentId + " in " + className + ".");
            }
        }
    }

    // List all classrooms
    public void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms available.");
        } else {
            System.out.println("Available classrooms:");
            for (String className : classrooms.keySet()) {
                System.out.println("- " + className);
            }
        }
    }

    // List students in a classroom
    public void listStudentsInClassroom(String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            System.out.println("Classroom " + className + " does not exist.");
        } else {
            classroom.listStudents();
        }
    }

    // Remove a classroom
    public void removeClassroom(String className) {
        if (classrooms.remove(className) != null) {
            System.out.println("Classroom " + className + " has been removed.");
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }
}

class Classroom {
    private final String name;
    private final Map<String, Student> students;
    private final List<Assignment> assignments;

    public Classroom(String name) {
        this.name = name;
        this.students = new HashMap<>();
        this.assignments = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            students.put(student.getId(), student);
        }
    }

    public void scheduleAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public boolean submitAssignment(String studentId, String assignmentDetails) {
        Student student = students.get(studentId);
        if (student == null) {
            return false;
        }

        for (Assignment assignment : assignments) {
            if (assignment.getDetails().equals(assignmentDetails)) {
                return assignment.submit(student);
            }
        }
        return false;
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students enrolled in " + name + ".");
        } else {
            System.out.println("Students in " + name + ":");
            for (Student student : students.values()) {
                System.out.println("- " + student.getId());
            }
        }
    }
}

class Student {
    private final String id;

    public Student(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

class Assignment {
    private final String details;
    private final Set<String> submittedStudents;

    public Assignment(String details) {
        this.details = details;
        this.submittedStudents = new HashSet<>();
    }

    public String getDetails() {
        return details;
    }

    public boolean submit(Student student) {
        return submittedStudents.add(student.getId());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VirtualClassroomManager manager = new VirtualClassroomManager();

        System.out.println("Welcome to the Virtual Classroom Manager!");

        while (true) {
            System.out.println("\nEnter a command (add_classroom, add_student, schedule_assignment, submit_assignment, list_classrooms, list_students, remove_classroom, exit):");
            String command = scanner.nextLine();

            try {
                switch (command.split(" ")[0]) {
                    case "add_classroom":
                        manager.addClassroom(command.split(" ", 2)[1]);
                        break;
                    case "add_student":
                        String[] studentArgs = command.split(" ", 3);
                        manager.addStudent(studentArgs[1], studentArgs[2]);
                        break;
                    case "schedule_assignment":
                        String[] assignmentArgs = command.split(" ", 3);
                        manager.scheduleAssignment(assignmentArgs[1], assignmentArgs[2]);
                        break;
                    case "submit_assignment":
                        String[] submitArgs = command.split(" ", 4);
                        manager.submitAssignment(submitArgs[1], submitArgs[2], submitArgs[3]);
                        break;
                    case "list_classrooms":
                        manager.listClassrooms();
                        break;
                    case "list_students":
                        manager.listStudentsInClassroom(command.split(" ", 2)[1]);
                        break;
                    case "remove_classroom":
                        manager.removeClassroom(command.split(" ", 2)[1]);
                        break;
                    case "exit":
                        System.out.println("Exiting the Virtual Classroom Manager.");
                        return;
                    default:
                        System.out.println("Unknown command. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input format. Please try again.");
            }
        }
    }
}
