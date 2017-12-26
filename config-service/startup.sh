#!/bin/sh

set -e

while ! nc -w 1 registry 8100 2>/dev/null
do
  echo -n .
  sleep 1
done

java -jar /app/config-service.jar
