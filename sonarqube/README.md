## Sonarqube
Launch sonarqube with the command `$ docker-compose up -d`. It allows you to execute tasks such as static analysis, code coverage or even implement your code quality gate.

> Example using [ui-tests](../ui-tests)
```shell script
$ mvn -B clean verify sonar:sonar \
            -Dskip.validate=true \
            -Dmaven.test.skip=true \
            -Dsonar.host.url=${SONARQUBE_ADDRESS} \
            -Dsonar.qualitygate.wait=true \
            -Dsonar.sources=src/test/java \
            -Dsonar.tests=src/main/java \
            -Dsonar.inclusions=src/test/java/**/*.java \
            -Dsonar.tests.exclusions=src/test/java/**/*.java
```