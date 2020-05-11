DROP MATERIALIZED VIEW IF EXISTS public.components_show;

CREATE MATERIALIZED VIEW public.components_show
    TABLESPACE pg_default
AS
SELECT comp.id AS component_id,
    comps.public_id AS foodb_id,
    comp.name,
    comps.description,
    comp.category_name,
    comps.cas_number,
    comps.moldb_formula,
    comps.percent_composition,
    comps.moldb_iupac,
    comps.moldb_inchi,
    comps.moldb_inchikey,
    comps.moldb_smiles,
    comps.moldb_average_mass,
    comps.moldb_mono_mass,
    comps.kingdom,
    comps.superklass,
    comps.klass,
    comps.subklass,
    comps.direct_parent,
    comps.molecular_framework,
    comps.moldb_alogps_logp,
    comps.moldb_logp,
    comps.moldb_alogps_logs,
    comps.moldb_pka,
    comps.moldb_alogps_solubility,
    comp.sameas
   FROM component comp
     LEFT JOIN compounds comps ON comp.compound_id = comps.id
WITH DATA;