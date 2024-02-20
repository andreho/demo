CREATE SCHEMA IF NOT EXISTS db;
CREATE SCHEMA IF NOT EXISTS ext;

GRANT USAGE ON SCHEMA db TO "${db-username}";
GRANT USAGE ON SCHEMA ext TO "${db-username}";

SET search_path TO db,ext,public;
ALTER ROLE "${db-username}" SET search_path = db,ext,public;