import java.util.Objects;

public class Student {
    private String rollNo;
    private String name;
    private double marks;

    public Student(String rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public String toCSV() {
        // simple CSV: roll,name,marks
        return rollNo + "," + name + "," + marks;
    }

    public static Student fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] p = line.split(",", 3);
        if (p.length < 3) return null;
        try {
            return new Student(p[0].trim(), p[1].trim(), Double.parseDouble(p[2].trim()));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("Roll: %s | Name: %s | Marks: %.2f", rollNo, name, marks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return Objects.equals(rollNo, s.rollNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }
}
