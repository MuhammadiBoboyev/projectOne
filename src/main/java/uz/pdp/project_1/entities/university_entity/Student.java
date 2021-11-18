package uz.pdp.project_1.entities.university_entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private boolean gender;

    @ManyToOne
    private Groups groups;

    @ManyToMany
    private List<Subject> subjects;

    public Student(String firstName, String lastName, Date birthDate, boolean gender, Groups groups, List<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.groups = groups;
        this.subjects = subjects;
    }
}
