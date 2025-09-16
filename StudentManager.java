import java.nio.file.*;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Path dataFile;

    public StudentManager(String filePath) {
        this.dataFile = Paths.get(filePath);
        load();
    }

    private void ensureDataFile() {
        try {
            if (Files.notExists(dataFile.getParent()))
                Files.createDirectories(dataFile.getParent());
            if (Files.notExists(dataFile))
                Files.createFile(dataFile);
        } catch (IOException e) {
            System.err.println("Unable to create data file: " + e.getMessage());
        }
    }

    public void load() {
        ensureDataFile();
        try {
            List<String> lines = Files.readAllLines(dataFile);
            students = lines.stream()
                    .map(Student::fromCSV)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
        }
    }

    private void save() {
        try {
            List<String> lines = students.stream()
                    .map(Student::toCSV)
                    .collect(Collectors.toList());
            Files.write(dataFile, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public boolean addStudent(Student s) {
        if (findByRoll(s.getRollNo()) != null) return false;
        students.add(s);
        save();
        return true;
    }

    public boolean deleteStudent(String rollNo) {
        Student s = findByRoll(rollNo);
        if (s == null) return false;
        students.remove(s);
        save();
        return true;
    }

    public boolean updateStudent(String rollNo, String newName, Double newMarks) {
        Student s = findByRoll(rollNo);
        if (s == null) return false;
        if (newName != null && !newName.trim().isEmpty()) s.setName(newName);
        if (newMarks != null) s.setMarks(newMarks);
        save();
        return true;
    }

    public Student findByRoll(String rollNo) {
        for (Student s : students) {
            if (s.getRollNo().equalsIgnoreCase(rollNo)) return s;
        }
        return null;
    }

    public List<Student> listAll() {
        return new ArrayList<>(students);
    }
}
