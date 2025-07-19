package classesandinterfaces.studentandteacher;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "students") // WAŻNE: Wyklucz kolekcję z toString(), aby uniknąć pętli przy logowaniu
@EqualsAndHashCode(exclude = "students")
public class Teacher {
    private final Long id;
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String field;
    private Set<Student> students = new HashSet<>();

    public Teacher(final Long id, final String name, final String lastName, final int age, final String email, final String field) {
        this.id = id;
        this.name = name;
        this.lastname = lastName;
        this.age = age;
        this.email = email;
        this.field = field;
    }
}
