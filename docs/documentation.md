The following is a set of guidelines and documentation to better experience the ui-automation-bootstrap's features. 
These are mostly guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a pull request.

#### Table of contents

[Default](#default)
    
[ExtentReports](#extentreports)

[MockServer](#mockserver)

[Checkstyle](#checkstyle)

[SonarQube](#sonarqube)

[Github-actions](#github-actions-)

[Jenkins](#jenkins-)

## Default

The standard template without extra features will already enable you to use some interesting system properties.

```shell script
Usage: $ mvn test [options]

Options:
   -Dparallel              # enables parallel threads (choices: false, methods, tests, classes, instances) [false]
   -DthreadCount           # number of threads to use when running tests in parallel [1]
   -Dlistener              # comma-separated list of java classes that can be found on your classpath [null]
```

As we are using the goods of [Selenide](https://github.com/selenide/selenide) by default, you are also able to use its system properties alongside with the custom ones already available.

##### Example:
```shell script
$ mvn test -Dparallel=methods \
           -DthreadCount=2 \
           -Dselenide.remote=http://<<docker_ip>>:4444/wd/hub \
           -Dselenide.headless=true \
           -Dselenide.browser=firefox \
           -Dselenide.baseUrl=http:/google.com
```

> More about Selenide's configuration settings and documentation [here](https://selenide.org/javadoc/current/com/codeborne/selenide/Configuration.html)

## ExtentReports

Using [ExtentReports](https://extentreports.com/), you are able to automatically generate reports after test execution. These are stored under `reports/ExtentReport.html`. 
Furthermore, and by default, screenshots are taken upon test failure and attached to the report.

![](img/reports.gif)

> ⚠️ Requires the extent report listener property to be set.
>
> Example: `-Dlistener=${package}/utils/listeners/ExtentReportListener.java`

## MockServer

Using [MockServer](https://www.mock-server.com/), it allows injecting mocks during runtime by listening to `@Mock` annotations.

```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {
    String[] path();
}
```

The annotation may be declared for methods or class types.

##### Example
````java
@Mock(path = {"path1", "path2", ...})
@Test()
public void shouldDoThis() { ... }
````

> ⚠️ Requires the mock server address to be set using the property `-Dmock.server.address` and the mock server listener.
>
> Example: `-Dmock.server.address=0.0.0.0:3000  -Dlistener=${package}/utils/listeners/MockServerListener.java`

## Checkstyle

This feature integrates your project with a code linter, so that everyone follows the same code style within the team. 
Executable through: `$ mvn validate`.
 
## SonarQube

Using [SonarQube](https://www.sonarqube.org/) feature integration, it allows you to execute tasks such as static analysis, code coverage or even implement your code quality gate.
Executable through: `$ mvn sonar:sonar -Dsonar.host.url=http://<<docker_ip>>:9090`.

## Github-actions 🤖

Use this feature if you are using github for your source code. It provides a pretty handy continuous integration pipeline using [github-actions](https://help.github.com/en/actions), under `.github/workflows/`.
Executable using `-Dgithub-actions=true`.

##### Snippet
```yaml
name: github-actions

on:
  push:
    branches: [ master ]
  pull_request:
    branches: [ master ]

jobs:
  ci:
    runs-on: ubuntu-latest
    steps:
      
      (...)

      - name: Validate checkstyle
        run: mvn -B validate
      
      (...)

      - name: Run tests
        run: |
          mvn -B test \
          -Dselenide.remote=http://0.0.0.0:4444/wd/hub \
          -Dmock.server.address=0.0.0.0:3000 \
          -Dlistener=io/company/utils/listeners/MockServerListener.java,io/company/utils/listeners/ExtentReportListener.java

      - name: Publish reports (if failure)
        uses: actions/upload-artifact@v1
        if: failure()
        with:
          name: extent-report
          path: reports/ExtentReport.html
```

## Jenkins 🤖

This feature creates a Jenkinsfile example for you!
Executable using: `-Djenkins=true`.

##### Snippet
```groovy
podTemplate(label: "jenkins-slave-base-pod", serviceAccount: "jenkins", containers: [
        containerTemplate(
                name: "base",
                image: "sergiomartins8/jenkins-slave-base:1.0",
                ttyEnabled: true,
                command: "cat"
        )
],
        volumes: [
                hostPathVolume(mountPath: "/var/run/docker.sock", hostPath: "/var/run/docker.sock")
        ]
) {
    node("jenkins-slave-base-pod") {
        container("base") {
            stage("Checkout") {
                checkout scm
            }
    
            (...)

        }
    }
}
```

> Note that the example uses Jenkins on Kubernetes. Follow this [article](https://medium.com/@sergiomartins8/highly-scalable-jenkins-on-minikube-8cc289a31850) to have a similar environment in no time!
