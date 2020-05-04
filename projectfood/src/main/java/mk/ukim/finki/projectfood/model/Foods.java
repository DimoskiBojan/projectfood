package mk.ukim.finki.projectfood.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "foods")
public class Foods {

    @Id
    private Integer id;

    private String name;
    private String name_scientific;
    private String description;
    private String itis_id;
    private String wikipedia_id;
    private String picture_file_name;
    private String picture_content_type;
    private Integer picture_file_size;
    private LocalDateTime picture_updated_at;
    private Integer legacy_id;
    private String food_group;
    private String food_subgroup;
    private String food_type;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Integer creator_id;
    private Integer updater_id;
    private Boolean export_to_afcdb;
    private String category;
    private Integer ncbi_taxonomy_id;
    private Boolean export_to_foodb;
    @Column(name = "sameas")
    private String sameAs;
}