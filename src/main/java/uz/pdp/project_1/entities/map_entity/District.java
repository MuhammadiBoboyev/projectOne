package uz.pdp.project_1.entities.map_entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Region region;

    public District(String name, Region region) {
        this.name = name;
        this.region = region;
    }
}
