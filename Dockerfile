FROM debian:jessie

ENV LANG C.UTF-8

# ==================== Java

##########
# Packages

RUN apt-get --assume-yes update && apt-get --assume-yes install \
  # Makes /etc/ssl/certs/java/cacerts available for the java8 install uses less stringent cacerts
  default-jre-headless \
  # to fetch jce_policy
  curl \
  # to unzip jce policy
  unzip && \
  rm -rf /var/lib/apt/lists/*

########

# Install Oracle Java 8. See https://github.com/William-Yeh/docker-java8
# add webupd8 repository
RUN \
    echo "===> add webupd8 repository..."  && \
    echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list  && \
    echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list  && \
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886  && \
    apt-get update  && \
    \
    \
    echo "===> install Java"  && \
    echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections  && \
    echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections  && \
    DEBIAN_FRONTEND=noninteractive  apt-get --assume-yes install oracle-java8-installer oracle-java8-set-default  && \
    \
    \
    echo "===> clean up..."  && \
    rm -rf /var/cache/oracle-jdk8-installer  && \
    rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# ==================== Maven

RUN apt-get --assume-yes update && apt-get --assume-yes install maven

# ENV LEIN_ROOT true

WORKDIR /app

COPY pom.xml /app/

# resolve/download/cache all the dependencies, first by the book, then the non-elegant way that actually works
RUN mvn dependency:go-offline --quiet
RUN mvn package clean --fail-never --quiet 2>&1 > /dev/null

COPY scripts/ /app/scripts
COPY src/ /app/src

RUN mvn package --quiet
RUN mv target/*.jar .. && rm -fr src target pom.xml

ENTRYPOINT ["./scripts/entrypoint.sh"]
