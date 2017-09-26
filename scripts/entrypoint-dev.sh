#!/usr/bin/env bash
set -e

project_name=fb-webhook

echo "Starting ${project_name} JVM"

# TODO find out if there is way to auto-embed name and version from pom file in this script.

exec java \
  -XX:-OmitStackTraceInFastThrow \
  -Xmx1g \
  -server \
  -jar target/${project_name}-0.0.1-SNAPSHOT.jar \
  "$@"
