package classesandinterfaces.studentandteacher;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SchoolService {

    private final Map<Long, Student> students = new HashMap<>();
    private final Map<Long, Teacher> teachers = new HashMap<>();
    private final AtomicLong studentIdCounter = new AtomicLong();
    private final AtomicLong teacherIdCounter = new AtomicLong();

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");

    private void validate(String name, int age, String email) {
        if (name == null || name.length() < 3) throw new IllegalArgumentException("Name must be at least 3 characters.");
        if (age < 18) throw new IllegalArgumentException("Age must be at least 18.");
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) throw new IllegalArgumentException("Incorrect email format.");
    }

    public Student addStudent(String name, String lastname, int age, String email, String field) {
        validate(name, age, email);
        long newId = studentIdCounter.incrementAndGet();
        Student student = new Student(newId, name, lastname, age, email, field);
        students.put(newId, student);
        return student;
    }

    public void removeStudent(long id) {
        Student student = students.remove(id);
        if (student != null) {
            for (Teacher teacher : student.getTeachers()) {
                teacher.getStudents().remove(student);
            }
        }
    }

    public Teacher addTeacher(String name, String lastname, int age, String email, String field) {
        validate(name, age, email);
        long newId = teacherIdCounter.incrementAndGet();
        Teacher teacher = new Teacher(newId, name, lastname, age, email, field);
        teachers.put(newId, teacher);
        return teacher;
    }

    public void removeTeacher(long id) {
        Teacher teacher = teachers.remove(id);
        if (teacher != null) {
            for (Student student : teacher.getStudents()) {
                student.getTeachers().remove(teacher);
            }
        }
    }

    public void disconnectStudentOrTeacher(long studentId, long teacherId) {
        Student student = students.get(studentId);
        Teacher teacher = teachers.get(teacherId);
        teacher.getStudents().remove(student);
        student.getTeachers().remove(teacher);
    }

    public void assignTeacherToStudent(long studentId, long teacherId) {
        Student student = students.get(studentId);
        Teacher teacher = teachers.get(teacherId);
        if (student != null && teacher != null) {
            student.getTeachers().add(teacher);
            teacher.getStudents().add(student);
        }
    }

    public List<Student> getAllStudents(int page, int size, String sortBy) {
        Comparator<Student> comparator = switch (sortBy.toLowerCase()) {
            case "lastname" -> Comparator.comparing(Student::getLastname);
            case "age" -> Comparator.comparingInt(Student::getAge);
            default -> Comparator.comparing(Student::getName);
        };

        return students.values().stream()
                .sorted(comparator)
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    public List<Teacher> getAllTeachers(int page, int size, String sortBy) {
        Comparator<Teacher> comparator = switch (sortBy.toLowerCase()) {
            case "lastname" -> Comparator.comparing(Teacher::getLastname);
            case "wiek" -> Comparator.comparingInt(Teacher::getAge);
            default -> Comparator.comparing(Teacher::getName);
        };
        return teachers.values().stream()
                .sorted(comparator)
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Set<Student> getStudentsOfTeacher(long teacherId) {
        return Optional.ofNullable(teachers.get(teacherId))
                .map(Teacher::getStudents)
                .orElse(Collections.emptySet());
    }

    public Set<Teacher> getTeachersOfStudent(long studentId) {
        return Optional.ofNullable(students.get(studentId))
                .map(Student::getTeachers)
                .orElse(Collections.emptySet());
    }

    public List<Student> searchStudent(String partOfName) {
        String partLower = partOfName.toLowerCase();
        return students.values().stream()
                .filter(s -> s.getName().toLowerCase().contains(partLower) || s.getLastname().toLowerCase().contains(partLower))
                .collect(Collectors.toList());
    }
    public List<Teacher> searchTeacher(String partOfName) {
        String partLower = partOfName.toLowerCase();
        return teachers.values().stream()
                .filter(t -> t.getName().toLowerCase().contains(partLower) || t.getLastname().toLowerCase().contains(partLower))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SchoolService service = new SchoolService();

        Student jan = service.addStudent("Jan", "Kowalski", 20, "jan.k@test.com", "Informatyka");
        Student anna = service.addStudent("Anna", "Nowak", 21, "anna.n@test.com", "Socjologia");
        Student janusz = service.addStudent("Janusz", "Jakiś", 23, "jakis@test.com", "Socjologia");
        Student marek = service.addStudent("Marek", "Markowy", 28, "markowy@test.com", "Informatyka");
        Student filip = service.addStudent("Filip", "Fistaszek", 22, "fistaszek@test.com", "Informatyka");
        Student barbara = service.addStudent("Barbara", "Barek", 24, "barek@test.com", "Socjologia");

        Teacher mikolaj = service.addTeacher("Mikołaj", "Jakiś", 54, "m.jakis@edu.pl", "Informatyka");
        Teacher marcin = service.addTeacher("Marcin", "Wenus", 51, "wenus@edu.pl", "Socjologia");

        service.assignTeacherToStudent(jan.getId(), mikolaj.getId());
        service.assignTeacherToStudent(anna.getId(), mikolaj.getId());
        service.assignTeacherToStudent(janusz.getId(), mikolaj.getId());
        service.assignTeacherToStudent(marek.getId(), marcin.getId());
        service.assignTeacherToStudent(filip.getId(), marcin.getId());
        service.assignTeacherToStudent(barbara.getId(), marcin.getId());


        List<Student> sortedStudents = service.getAllStudents(0, 5, "age");
        sortedStudents.forEach(System.out::println);
        System.out.println("------------------");
        List<Teacher> sortedTeachers = service.getAllTeachers(0, 5, "lastname");
        sortedTeachers.forEach(System.out::println);
        System.out.println("------------------");
        List<Student> founded = service.searchStudent("Kowal");
        founded.forEach(System.out::println);
        System.out.println("------------------");
        List<Teacher> foundedTeachers = service.searchTeacher("Wen");
        foundedTeachers.forEach(System.out::println);
        System.out.println("------------------");
        Set<Student> studentsTeacher = service.getStudentsOfTeacher(marcin.getId());
        studentsTeacher.forEach(System.out::println);
        System.out.println("------------------");
        Set<Teacher> teachersStudent = service.getTeachersOfStudent(marek.getId());
        teachersStudent.forEach(System.out::println);
        System.out.println("------------------");
        service.removeStudent(filip.getId());
        service.getAllStudents(0, 5, "age").forEach(System.out::println);
        System.out.println("------------------");
        service.removeTeacher(mikolaj.getId());
        service.getAllTeachers(0, 5, "age").forEach(System.out::println);
        System.out.println("-----------------");
        service.disconnectStudentOrTeacher(marek.getId(), marcin.getId());
        service.getAllStudents(0, 5, "age").forEach(System.out::println);
        service.getAllTeachers(0, 5, "lastname").forEach(System.out::println);
    }

}
