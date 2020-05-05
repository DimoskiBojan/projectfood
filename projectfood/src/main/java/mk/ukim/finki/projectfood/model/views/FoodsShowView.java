package mk.ukim.finki.projectfood.model.views;

import lombok.Data;
import mk.ukim.finki.projectfood.model.FoodComponent;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

import java.util.List;

@Entity
@Subselect("select * from foods_show")
@Immutable
@Data
@Table(name = "foods_show")
public class FoodsShowView {
    @Id
    @Column(name="food_id")
    private Integer id;

    private String name;

    private String name_scientific;

    private String description;

    private String picture_file_name;

    private String category;

    private String subcategory;

    @Column(name = "sameas")
    private String sameAs;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodComponent> foodComponents;
}
