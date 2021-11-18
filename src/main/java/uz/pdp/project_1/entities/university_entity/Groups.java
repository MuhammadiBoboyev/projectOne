package uz.pdp.project_1.entities.university_entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Faculty faculty;

    public Groups(String name, Faculty faculty) {
        this.name = name;
        this.faculty = faculty;
    }
}
