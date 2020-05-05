package mk.ukim.finki.projectfood.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "food")
public class Food {

    @Id
    private Integer id;

    private String name;
    @Column(name = "sameas")
    private String sameAs;
    private String policy;
    private String category;

    @ManyToOne
    @JoinColumn(name = "foodb_id")
    private Foods foodb_id;

    /*@OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodComponent> foodComponents;*/
}
