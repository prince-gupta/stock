#!/bin/sh

set -e

while ! nc -w 1 config 8102 2>/dev/null
do
  echo -n .
  sleep 1
done

while ! nc -w 1 mysql-db 3306 2>/dev/null
do
  echo -n .
  sleep 1
done

java -jar /app/db-service.jar
