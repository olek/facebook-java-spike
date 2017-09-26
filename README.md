# facebook-java-spike
Spike into using realtime callbacks/webhooks with facebook.

## Usage

Clean up target folder

    mvn clean

Compile and run it with maven

    mvn compile && mvn spring-boot:run

Build it as deployable jar which can be found in `target/fb-webhook-0.0.1-SNAPSHOT.jar`

    mvn package

Run that jar file

    ./entrypoint-dev.sh

Run that jar file, overriding configuration properties

    ./scripts/entrypoint-dev.sh --fb.webhook.foo=bar


## Using with Docker

Build docker image

    docker build -t lm/fb-webhook .

Run it with docker

    ./docker-boot-dev.sh

## Other useful command line commands for development

List files contained in built package

    jar tvf target/fb-webhook-0.0.1-SNAPSHOT.jar

Show content of one of the files from built package

    unzip -qc target/fb-webhook-0.0.1-SNAPSHOT.jar BOOT-INF/classes/application.properties

Gather all of the (available) sources for all of the dependencies, and search through them form some
class

    mvn dependency:unpack-dependencies -Dclassifier=sources
    find target/dependency -iname ManagedAsync*
