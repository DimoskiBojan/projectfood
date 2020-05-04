package mk.ukim.finki.projectfood.model;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "compounds")
public class Compounds {

    @Id
    private Integer id;
    private Integer legacy_id;
    private String type;
    private String public_id;
    private String name;
    private Boolean export; //boolean?
    private String state;
    private String annotation_quality;
    private String description;
    private String cas_number;
    private String melting_point;
    private String protein_formula;
    private String protein_weight; //?
    private String experimental_solubility;
    private String experimental_logp;
    private String hydrophobicity;
    private String isoelectric_point;
    private String metabolism;
    private String kegg_compound_id;
    private String pubchem_compound_id;
    private String pubchem_substance_id;
    private String chebi_id;
    private String het_id;
    private String uniprot_id;
    private String uniprot_name;
    private String genbank_id; //2
    private String wikipedia_id; //1
    private String synthesis_citations;//1
    private String general_citations;//1
    private String comments;//1
    private String protein_structure_file_name; //2
    private String protein_structure_content_type;
    private Integer protein_structure_file_size;
    private LocalDateTime protein_structure_updated_at;
    private String msds_file_name;//1
    private String msds_content_type;//1
    private Integer msds_file_size;//1
    private LocalDateTime msds_updated_at;//1
    private Integer creator_id;
    private Integer updater_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Integer phenolexplorer_id;
    private String dfc_id;//1
    private String hmdb_id;//1
    private String duke_id;//1
    private String drugbank_id;//1
    private Integer bigg_id;//1
    private String eafus_id;//1
    private String knapsack_id;//1
    private String boiling_point;//1
    private String boiling_point_reference;//1
    private String charge;
    private String charge_reference;//2
    private String density;//d 0.9
    private String density_reference;//DFC
    private String optical_rotation;//[a]22D -79.7 (EtOH)
    private String optical_rotation_reference;//DFC
    private String percent_composition;//C 52.32%; H 2.58%; O 45.10%
    private String percent_composition_reference;//DFC
    private String physical_description;//Oil
    private String physical_description_reference;//DFC
    private String refractive_index;//n19D<vs=1>  1.4779
    private String refractive_index_reference;//DFC
    private String uv_index;//[neutral] lmax 222 (log e 3.5) (MeOH)
    private String uv_index_reference;//DFC
    private String experimental_pka;//pKa2 11.06 (20å¡)
    private String experimental_pka_reference;//DFC
    private String experimental_solubility_reference;//YALKOWSKY,SH
    private String experimental_logp_reference;//HANSCH,C ET AL. (1995)
    private String hydrophobicity_reference;
    private String isoelectric_point_reference;
    private String melting_point_reference;//1
    private String moldb_alogps_logp;
    private String moldb_logp;
    private String moldb_alogps_logs;
    private String moldb_smiles;//formula
    private String moldb_pka;
    private String moldb_formula;//1
    private String moldb_average_mass;
    private String moldb_inchi;//1
    private String moldb_mono_mass;
    private String moldb_inchikey;//1
    private String moldb_alogps_solubility;//1
    private Integer moldb_id;
    private String moldb_iupac;//1
    private String structure_source;//MANULA
    private String duplicate_id;//1
    private String old_dfc_id;//1
    private String dfc_name;///1
    private String compound_source;//1
    private String flavornet_id;//1
    private String goodscent_id;//1
    private String superscent_id;
    private Integer phenolexplorer_metabolite_id;
    private String kingdom;//1
    private String superklass;//1
    private String klass;//1
    private String subklass;//1
    private String direct_parent;//1
    private String molecular_framework;//1
    private String chembl_id;//1
    private String chemspider_id;
    private String meta_cyc_id;//1
    private Boolean foodcomex;//boolean?
    private String phytohub_id;//1


}
