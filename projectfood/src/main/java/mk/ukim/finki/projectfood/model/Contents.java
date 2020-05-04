package mk.ukim.finki.projectfood.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contents")
public class Contents {

    @Id
    private Integer id;
    @Column(name = "source_id")
    private Integer sourceId;
    @Column(name = "source_type")
    private String sourceType;
    private Integer food_id;
    private String orig_food_id;
    private String orig_food_common_name;
    private String orig_food_scientific_name;
    private String orig_food_part;
    private String orig_source_id;
    private String orig_source_name;
    private Double orig_content;
    private Double orig_min;
    private Double orig_max;
    private String orig_unit;
    private String orig_citation;
    private String citation;
    private String citation_type;
    private Integer creator_id;
    private Integer updater_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String orig_method;
    private String orig_unit_expression;
    private Double standard_content;
}
