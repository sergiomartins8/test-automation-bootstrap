## Load Tests
Here you'll find examples using [K6](https://k6.io/) load testing tool.

Following the k6 [documentation](https://k6.io/docs/getting-started/installation) for more details.

> Snippet using gitlab pipelines
```yaml
load_testing:
  stage: load-testing
  image:
    name: loadimpact/k6:latest
    entrypoint: [ '' ]
  script: |
    k6 run -e USER_EMAIL_ADDRESS=$USER_EMAIL_ADDRESS \
           -e USER_PASSWORD=$USER_PASSWORD \
           -e AWS_APP_CLIENT_ID=$AWS_APP_CLIENT_ID \
           load-tests/k6.js
```
