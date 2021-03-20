FROM alpine:3.11

RUN set -x \
    && apk update \
    && apk add --no-cache \
        python3 \
    && pip3 install --no-cache-dir --upgrade pip \
    && rm -rf /var/cache/* \
    && rm -rf /root/.cache/*

CMD ["/bin/sh"]
