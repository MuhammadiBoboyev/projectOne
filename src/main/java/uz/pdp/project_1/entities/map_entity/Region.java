package uz.pdp.project_1.entities.map_entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Country country;

    public Region(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
