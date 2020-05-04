package mk.ukim.finki.projectfood.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "food")
public class Food {

    @Id
    private Integer id;

    private Integer food_id;

    private String name;
    @Column(name = "sameas")
    private String sameAs;
    private String policy;
    private String category;

    @ManyToOne
    @JoinColumn(name = "foodb_id")
    private Foods foodb_id;

}
