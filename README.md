# ui-automation-bootstrap

A template for selenium based ui automation projects using _[selenide](https://github.com/selenide/selenide)_ ✨

[![badge-jdk](https://img.shields.io/badge/jdk-8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
![Languages](https://img.shields.io/github/languages/top/sergiomartins8/ui-automation-bootstrap)
[![Contributors badge](https://img.shields.io/github/contributors/sergiomartins8/ui-automation-bootstrap)](https://github.com/sergiomartins8/ui-automation-bootstrap/graphs/contributors)
[![Issues](https://img.shields.io/github/issues/sergiomartins8/ui-automation-bootstrap)](https://github.com/sergiomartins8/ui-automation-bootstrap/issues)
[![License badge](https://img.shields.io/github/license/sergiomartins8/ui-automation-bootstrap)](http://www.apache.org/licenses/LICENSE-2.0)
![Validate checkstyle](https://github.com/sergiomartins8/ui-automation-bootstrap/workflows/checkstyle/badge.svg)

* [About](#about)
* [Getting Started](#getting-started)
* [Documentation](#documentation)

## What does a _ui-automation-bootstrap_ test look like?

This is a sample test where a user logs in, waits the page to be loaded and checks if the side bar is visible:

```java
@Test
@Mock(path = {"/mocks/example-expectation.json"})
public void testExample() {
    open("example.com/login")
    new LoginPage()
            .login("username", "password")
            .waitPageLoaded()
            .sideBar()
            .self()
            .shouldBe(Condition.visible);
}
```

## About

The goal is to build a solid and generic template so that Test Automation Engineers as myself are able to bootstrap new or ongoing ui Selenium based automation projects with ease.

##### Using the goods of 
* _[Selenide](https://github.com/selenide/selenide)_ - A selenium wrapper for concise UI tests
* _[WebDriverManager](https://github.com/bonigarcia/webdrivermanager)_ - Downloads the required driver during runtime. May be configured on `DriverContext` under `/base`
* _[MockServer](https://www.mock-server.com/) 🐳_ - Enables the ability to mock _http_ requests and responses (check [MockServerListener](#mockserverlistener) section)
* _[ExtentReports](https://extentreports.com/)_ - Provides full test reports. Takes screenshots upon test failure by default (check [ExtentReportListener](#extentreportlistener) section)
* _[SonarQube](https://www.sonarqube.org/) 🐳_ - A static analysis tool. Executable through: `$ mvn sonar:sonar -Dsonar.host.url=http://<<docker_ip>>:9090`
* _[SeleniumGrid](https://github.com/SeleniumHQ/docker-selenium) 🐳_ - Allows to scale the test executing as well as providing the required browser types
* _[Checkstyle](https://maven.apache.org/plugins/maven-checkstyle-plugin/)_ - Code linter. Executable through: `$ mvn validate`

> _🐳 stands for dockerized_

## Getting Started

```shell script
$ git clone https://github.com/sergiomartins8/ui-automation-bootstrap.git
$ cd ui-automation-bootstrap/
```

It's your project now. That easy! 🚀

Customize it for your needs.

## Documentation

### Usage

```shell script
Usage: $ mvn test [options]

Options:
   -Dmock.server.address   # mock server address [null]
   -Dparallel              # enables parallel threads (choices: false, methods, tests, classes, instances) [false]
   -DthreadCount           # number of threads to use when running tests in parallel [1]
   -Dlistener              # comma-separated list of java classes that can be found on your classpath [null]
```

##### Example:
````shell script
$ mvn clean test -Dmock.server.address=localhost:3000 -Dlistener=utils/listeners/MockServerListener.java
````

### Usage (cont.)

Using the goods of selenide, you can also use its system properties alongside with the custom ones available.

##### Example:
```shell script
$ mvn test -Dparallel=methods \
           -DthreadCount=2 \
           -Dselenide.remote=http://localhost:4444/wd/hub \
           -Dselenide.headless=true \
           -Dselenide.browser=firefox \
           -Dselenide.baseUrl=http:/google.com
```

> More about selenide's configuration settings and documentation [here](https://selenide.org/javadoc/current/com/codeborne/selenide/Configuration.html)

### Listeners

There are a couple listeners available _(however, disabled by default)_. 
The `MockServerListener` and `ExtentReportListener`, located under `utils/listeners`.

#### MockServerListener

Using _[MockServer](https://www.mock-server.com/)_, it allows injecting mocks during runtime listening to `@Mock` annotations.

````java
@Mock(path = {"path1", "path2", ...})
````

> ⚠️ Requires the mock server address to be set using the property `-Dmock.server.address`

#### ExtentReportListener

Using _[ExtentReports](https://extentreports.com/)_, it automatically generates reports after test execution, which are stored under `reports/ExtentReport.html`. 
Furthermore, and by default, screenshots are taken upon test failure and attached to the report.

![](docs/img/reports.gif)

## Changelog

Automatically generated by using [github-changes](https://github.com/lalitkapoor/github-changes).

Available [here](/docs/CHANGELOG.md).

## Contributing

Open source from the first commit ✨

Dive into ui-automation-bootstrap [contribution guide](docs/CONTRIBUTING.md).

## Kuddos

Feel free to reach me out on linkedin[@sergiomartins8](https://www.linkedin.com/in/sergiomartins8/) ‍🙌
