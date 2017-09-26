#!/usr/bin/env bash

lein var-file

docker run -it --rm \
       --env-file config/application.properties \
       -p 8080:8080 \
       lm/fb-webhook
