import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dataPath = "data/students.csv";
        StudentManager manager = new StudentManager(dataPath);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Search Student (by Roll)");
            System.out.println("4. Update Student");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Roll No: ");
                    String roll = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Marks: ");
                    double marks;
                    try {
                        marks = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid marks. Aborting add.");
                        break;
                    }
                    if (manager.addStudent(new Student(roll, name, marks)))
                        System.out.println("Student added.");
                    else
                        System.out.println("Roll already exists. Use update instead.");
                    break;

                case "2":
                    System.out.print("Enter Roll to delete: ");
                    if (manager.deleteStudent(sc.nextLine().trim()))
                        System.out.println("Deleted.");
                    else
                        System.out.println("Not found.");
                    break;

                case "3":
                    System.out.print("Enter Roll to search: ");
                    Student found = manager.findByRoll(sc.nextLine().trim());
                    if (found != null) System.out.println(found);
                    else System.out.println("Not found.");
                    break;

                case "4":
                    System.out.print("Enter Roll to update: ");
                    String r = sc.nextLine().trim();
                    Student s = manager.findByRoll(r);
                    if (s == null) {
                        System.out.println("Not found.");
                        break;
                    }
                    System.out.println("Leave field blank to keep current value.");
                    System.out.println("Current: " + s);
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    System.out.print("New marks: ");
                    String marksStr = sc.nextLine();
                    Double newMarks = null;
                    if (!marksStr.trim().isEmpty()) {
                        try {
                            newMarks = Double.parseDouble(marksStr.trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid marks input. Update aborted.");
                            break;
                        }
                    }
                    if (manager.updateStudent(r, newName.isBlank() ? null : newName, newMarks))
                        System.out.println("Updated.");
                    else
                        System.out.println("Update failed.");
                    break;

                case "5":
                    List<Student> list = manager.listAll();
                    if (list.isEmpty()) System.out.println("No students.");
                    else list.forEach(System.out::println);
                    break;

                case "6":
                    System.out.println("Bye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}


