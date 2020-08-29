#!/bin/bash
zcat /docker-entrypoint-initdb.d/backup.gz | psql -U postgres -d postgres
