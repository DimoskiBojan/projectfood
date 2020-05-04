package mk.ukim.finki.projectfood.model;

import lombok.Data;

import javax.persistence.*;

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

}