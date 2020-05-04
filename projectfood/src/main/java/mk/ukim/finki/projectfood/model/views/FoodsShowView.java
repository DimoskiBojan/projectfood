package mk.ukim.finki.projectfood.model.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
