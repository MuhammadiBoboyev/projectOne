package uz.pdp.project_1.entities.university_entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private University university;

    public Faculty(String name, University university) {
        this.name = name;
        this.university = university;
    }
}
