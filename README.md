# URL Shortener Application
It is a microservice application for converting URL to a short one.

## Installation
* start postgres database from `infrastructure/docker-compose`
* run `docker-compose -f postgres-sql.yml up`

### Schema
```bash
DROP SCHEMA IF EXISTS us_user CASCADE;

CREATE SCHEMA us_user;

DROP SCHEMA IF EXISTS url_shortener CASCADE;

CREATE SCHEMA url_shortener;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```

### Script 
```bash
DROP MATERIALIZED VIEW IF EXISTS us_user.url_shortener_user_m_view;

CREATE MATERIALIZED VIEW us_user.url_shortener_user_m_view
TABLESPACE pg_default
AS
 SELECT id,
    username,
    "password",
    "role",
    is_enabled  
   FROM us_user.users
WITH DATA;

refresh materialized VIEW us_user.url_shortener_user_m_view;

DROP function IF EXISTS us_user.refresh_url_shortener_user_m_view;

CREATE OR replace function us_user.refresh_url_shortener_user_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW us_user.url_shortener_user_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_url_shortener_user_m_view ON us_user.users;

CREATE trigger refresh_url_shortener_user_m_view
after INSERT OR UPDATE OR DELETE OR truncate
ON us_user.users FOR each statement
EXECUTE PROCEDURE us_user.refresh_url_shortener_user_m_view();
```

## Run
Both of services have `container` module that can start applications in this module.


## Swagger
Dashboard swagger for user service is : http://localhost:8686/swagger-ui/index.html

Dashboard swagger for url shortener service is : http://localhost:8687/swagger-ui/index.html
