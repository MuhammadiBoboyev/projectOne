package uz.pdp.project_1.entities.map_entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Continent continent;

    public Country(String name, Continent continent) {
        this.name = name;
        this.continent = continent;
    }
}
