DROP MATERIALIZED VIEW IF EXISTS public.foods_show;

CREATE MATERIALIZED VIEW public.foods_show
    TABLESPACE pg_default
AS
SELECT f.id AS food_id,
    fds.id AS foodb_id,
    f.name,
    fds.name_scientific,
    fds.description,
    fds.picture_file_name,
    f.category,
    fds.food_subgroup AS subcategory,
	f.sameas AS sameas
   FROM food f
     LEFT JOIN foods fds ON f.foodb_id = fds.id
WITH DATA;