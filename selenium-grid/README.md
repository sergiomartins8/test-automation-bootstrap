## Selenium Grid
Launch a Google Chrome and Firefox selenium grid with compose using `$ docker-compose up -d`. Then execute your tests.

> Example using [ui-tests](../ui-tests)
```shell
$ mvn -B clean test \
  -Dselenide.browser=firefox \
  -Dselenide.headless=true \
  -Dselenide.remote=http://0.0.0.0:4444/wd/hub
```
