#!/usr/bin/env bash
set -e

project_name=fb-webhook

echo "Starting ${project_name} JVM"

exec java \
  -XX:-OmitStackTraceInFastThrow \
  -Xmx1g \
  -server \
  -jar /app/target/${project_name}-0.0.1-SNAPSHOT.jar \
  "$@"
