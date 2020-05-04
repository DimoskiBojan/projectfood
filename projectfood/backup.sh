#!/bin/bash
docker exec -t pfood_postgres pg_dumpall -c -U postgres | gzip > backup.gz
