FROM postgres:9.6

MAINTAINER m.burda583@gmail.com

ARG postgresUser
COPY dockerDatabase/tableBackup /home/tableBackup
COPY load_db.sh /docker-entrypoint-initdb.d/.


