package classesandinterfaces.studentandteacher;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "teachers")
@EqualsAndHashCode(exclude = "teachers")
public class Student {
    private final Long id;
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String field;
    private Set<Teacher> teachers = new HashSet<>();

    public Student(final Long id, final String name, final String lastName, final int age, final String email, final String field) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.age = age;
        this.email = email;
        this.field = field;
    }
}
