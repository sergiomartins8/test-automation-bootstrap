FROM alpine:3.11

ENV JAVA_VERSION="11.0.5_p10-r0"
ENV MAVEN_VERSION="3.6.3-r0"

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-11-openjdk/jre/bin:/usr/lib/jvm/java-11-openjdk/bin

RUN set -x \
    && apk update \
    && apk add --no-cache \
        openjdk11="${JAVA_VERSION}" \
        maven \
    && rm -rf /var/cache/* \
    && rm -rf /root/.cache/*

CMD ["/bin/sh"]
