package mk.ukim.finki.projectfood.model.views;

import lombok.Data;
import mk.ukim.finki.projectfood.model.FoodComponent;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.util.List;

@Entity
@Subselect("select * from components_show")
@Immutable
@Data
@Table(name = "components_show")
public class ComponentsShowView {
    @Id
    @Column(name="component_id")
    private Integer id;

    private String foodb_id;

    private String name;
    private String description;
    private String category_name;
    private String cas_number;
    private String moldb_formula;
    private String percent_composition;
    private String moldb_iupac;
    private String moldb_inchi;
    private String moldb_inchikey;
    private String moldb_smiles;
    private String moldb_average_mass;
    private String moldb_mono_mass;
    private String kingdom;
    private String superklass;
    private String klass;
    private String subklass;
    private String direct_parent;
    private String molecular_framework;
    private String moldb_alogps_logp;
    private String moldb_logp;
    private String moldb_alogps_logs;
    private String moldb_pka;
    private String moldb_alogps_solubility;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodComponent> foodComponents;
}
