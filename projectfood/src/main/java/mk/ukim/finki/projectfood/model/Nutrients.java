package mk.ukim.finki.projectfood.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "nutrients")
public class Nutrients {

    @Id
    private Integer id;
    private Integer legacy_id;
    private String type;
    private String public_id;
    private String name;
    private Integer export;
    private String state;
    private String annotation_quality;
    private String description;
    private String wikipedia_id;
    private String comments;
    private String dfc_id;
    private String duke_id;
    private String eafus_id;
    private String dfc_name;
    private String compound_source;
    private String metabolism;
    private String synthesis_citations;
    private String general_citations;
    private Integer creator_id;
    private Integer updater_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
