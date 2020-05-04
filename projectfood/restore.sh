#!/bin/bash
zcat backup.gz | docker exec -i pfood_postgres psql -U postgres -d postgres
