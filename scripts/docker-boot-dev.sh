#!/usr/bin/env bash

docker run -it --rm \
       --env-file config/application.properties \
       -p 8080:8080 \
       lm/fb-webhook
