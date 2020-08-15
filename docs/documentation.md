The following is a set of guidelines and documentation to better experience the ui-automation-bootstrap's features. 
These are mostly guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a pull request.

#### Table of contents

[POM](#pom---the-page-object-model)

[Suites](#suites)

[Parallel Test Execution](#parallel-test-execution)

[Extent Reports](#extent-reports)

[Mocking Responses](#mocking-responses)

[Checkstyle](#checkstyle)

[SonarQube](#sonarqube)

[Jenkins](#jenkins-)

## POM - the Page Object Model
*ui-automation-bootstrap* uses the Page Object Model (**POM**) (https://martinfowler.com/bliki/PageObject.html) to structure code.

Within page objects you may find two kinds:
1. `Pages` a complete page (eg. login page, home page)
1. `Components` Reusable components within a page (eg. search bar, login form)

> NOTE: Components are not supposed to be restricted to single pages. Components are designed to be reused throughout the framework. 
> Thus, if you've to group them, group them by component type, not page; eg. forms, sidebars, modals.

## Suites
You can have multiple suites under [/suites](../src/test/resources/suites). And, in order to run any of them you can use a system property `-Dsuite=<suite-name>`.

##### Example
```shell script
$ mvn clean test -Dsuite=suiteA
```

> NOTE: Change the default suite on [pom.xml](../pom.xml) properties.

## Parallel Test Execution
You can run tests in parallel, configuring your suite file or with system properties.
 
##### Example
 ```shell script
$ mvn clean test -Dparallel=<method> -DthreadCount=<n-threads>
```

## Extent Reports
Using [ExtentReports](https://extentreports.com/), you are able to automatically generate reports after test execution. These are stored under `reports/ExtentReport.html`. 
Furthermore, and by default, screenshots are taken upon test failure and attached to the report.

![](img/reports.gif)

> ⚠️ Requires the extent report listener property to be set.
>
> Example: `-Dlistener=${package}/utils/listeners/ExtentReportListener.java`

## Mocking Responses
In order to mock http requests the framework uses browserup proxy behind selenide. This allows you to intercept, filter and manipulate requests and responses.

![](img/mocked_response.png)

First you've to model your request, so you can work with it anyhow you see fit. 
Therefore, in order to create a new object to model a mocked request (eg. `ExampleMockModel.java`) it has to implement [MockDefinition](../src/test/java/io/company/utils/mocks/MockDefinition.java) interface.

##### Example
````java
public class ExampleMockModel implements MockDefinition { ... }
````

Then, use the [@Mock](../src/test/java/io/company/utils/mocks/Mock.java) annotation in order to apply it for a given test case.

Snippet
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {
    Class<? extends MockDefinition> clazz();
}
```

The annotation may be declared for methods or class types.

##### Example
````java
@Mock(clazz = ExampleMockModel.class)
@Test(groups = {TestTypes.REGRESSION, TestTypes.MOCKED})
public void exampleMockedTest() { ... }
````

> **NOTE:** Safari does not support this use case.
>
> ⚠️ Requires proxy to be enabled. Luckily selenide has a custom configuration for this.
> Programmatically `Configuration.proxyEnabled = true` or by system property `-Dselenide.proxyEnabled=true`

## Checkstyle
This feature integrates your project with a code linter, so that everyone follows the same code style within the team. 

##### Example
```shell script
$ mvn validate
```
 
## SonarQube
Using [SonarQube](https://www.sonarqube.org/) feature integration, it allows you to execute tasks such as static analysis, code coverage or even implement your code quality gate.

##### Example
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

## Jenkins 🤖
There is a [jenkinsfile](../Jenkinsfile) example available. Use it to get started. However, it might need some tailoring.

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
