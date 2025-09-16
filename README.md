# Student Management System (Java)

A simple console-based Java application to manage student records (CRUD) with CSV persistence.

## Features
- Add student (roll, name, marks)
- Delete student by roll
- Search student by roll
- Update student details
- List all students

## Run
1. Ensure JDK is installed.
2. `javac *.java`
3. `java Main`

## Data storage
Records are stored in `data/students.csv` in CSV format: `roll,name,marks`

## Notes
- Simple CSV parsing: name must not contain commas.
- For improvements: GUI, persistence via SQLite, or REST API with Spring Boot.

