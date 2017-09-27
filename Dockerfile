# ================================================================================
# Build image.

FROM maven:ibmjava as builder

WORKDIR /app

COPY pom.xml docker/maven-settings.xml /app/

# overriding location for maven repository because current version of maven docker image declared /root/.m2 as VOLUME,
# making it in effect ephemeral, not allowing us to cache dependencies and forcing maven to 'download the world' each
# time we build.

# resolve/download/cache all the dependencies, first by the book, then the non-elegant way that actually works
RUN mvn dependency:go-offline --global-settings ./maven-settings.xml
RUN mvn package clean --fail-never --global-settings ./maven-settings.xml

COPY src/ /app/src

RUN mvn package --global-settings ./maven-settings.xml

# ================================================================================
# Runtime image.

FROM ibmjava:8-jre-alpine

WORKDIR /app

COPY docker/entrypoint.sh /app/
COPY --from=builder /app/target/*.jar .

ENTRYPOINT ["./entrypoint.sh"]
