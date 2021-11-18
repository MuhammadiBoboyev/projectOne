package uz.pdp.project_1.entities.university_entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Teacher {
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

    @ManyToMany
    private List<Subject> subjects;

    public Teacher(String firstName, String lastName, Date birthDate, boolean gender, List<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.subjects = subjects;
    }
}
