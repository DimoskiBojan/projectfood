package mk.ukim.finki.projectfood.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "component")
public class Component {
    @Id
    private Integer id;
    private String name;
    private Integer category_id;
    private String category_name;

    @OneToOne
    @JoinColumn(name = "compound_id")
    private Compounds compound;

    @Column(name = "sameas")
    private String sameAs;

    /*@OneToMany(mappedBy = "component", cascade = CascadeType.ALL)
    private List<FoodComponent> foodComponents;*/

}