#!/bin/bash
docker exec -t pfood_postgres pg_dumpall -c -U postgres | gzip > ./docker-entrypoint-initdb.d/backup.gz
