# ui-automation-bootstrap

![](docs/img/my_unicorn.png)


A template for selenium based ui automation projects using _[selenide](https://github.com/selenide/selenide)_ ✨

[![version](https://img.shields.io/maven-central/v/com.github.sergiomartins8/ui-automation-bootstrap?label=release)](https://search.maven.org/search?q=g:com.github.sergiomartins8)
[![release-candidate](https://github.com/sergiomartins8/ui-automation-bootstrap/workflows/release-candidate/badge.svg)](https://github.com/sergiomartins8/ui-automation-bootstrap/actions?query=workflow%3Arelease-candidate)
[![contributors](https://img.shields.io/github/contributors/sergiomartins8/ui-automation-bootstrap)](https://github.com/sergiomartins8/ui-automation-bootstrap/graphs/contributors)
[![issues](https://img.shields.io/github/issues/sergiomartins8/ui-automation-bootstrap)](https://github.com/sergiomartins8/ui-automation-bootstrap/issues)
[![license](https://img.shields.io/github/license/sergiomartins8/ui-automation-bootstrap)](http://www.apache.org/licenses/LICENSE-2.0)

* [About](#about)
* [Getting Started](#getting-started)
* [Changelog](#changelog)
* [Contributing](#contributing)

## What does a _ui-automation-bootstrap_ test look like?

This is a sample test where a user logs in, waits the page to be loaded and checks if the side bar is visible:

```java
@Test
@Mock(path = {"/mocks/example-google-search-expectation.json"})
public void shouldPerformSearch() {
    open("http://google.com");

    googleSearchPage.searchComponent()
            .searchFor("dogs");

    googleResultsPage.searchComponent()
            .self()
            .shouldHave(value("dogs"));
}
```

## About

The goal is to build a solid and generic template so that Test Automation Engineers as myself are able to bootstrap new or ongoing ui Selenium based automation projects with ease.

##### Using the goods of 
* _[Selenide](https://github.com/selenide/selenide)_ - A selenium wrapper for concise UI tests
* _[MockServer](https://www.mock-server.com/) 🐳_ - Enables the ability to mock _http_ requests and responses
* _[ExtentReports](https://extentreports.com/)_ - Provides full test reports. Takes screenshots upon test failure by default
* _[SonarQube](https://www.sonarqube.org/) 🐳_ - A static analysis tool
* _[SeleniumGrid](https://github.com/SeleniumHQ/docker-selenium) 🐳_ - Allows to scale the test execution as well as providing the required browser types
* _[Checkstyle](https://maven.apache.org/plugins/maven-checkstyle-plugin/)_ - Code linter

> _🐳 stands for dockerized_

## Getting Started

```shell script
Usage: $ mvn archetype:generate -DarchetypeGroupId=com.github.sergiomartins8 \ 
                                -DarchetypeArtifactId=ui-automation-bootstrap \
                                [args] [options] [features]

Args (mandatory):
   -DarchetypeVersion      # ui-automation-bootstrap's release version
   -DgroupId               # your custom group id
   -DartifactId            # your custom artifact id

Options:
   -DjavaVersion           # your java version (choices: 8, 11) [8]
   -Dpackage               # your package name [io.company]
   -Dversion               # your project's version [1.0.0-SNAPSHOT]

Features:
   -Dreports               # integration with Extent Reports [false]
   -Dcheckstyle            # integration with a default checkstyle [false]
   -Dsonarqube             # integration with SonarQube [false]
   -Dmockserver            # integration with Mock Server [false]
   -Dgithub-actions        # integration with a default CI pipeline using github actions [false]
   -Dtravis                # integration with a default CI pipeline using travis [false]
   -Djenkins               # integration with a default CI pipeline using jenkins [false]
```

##### Example

```shell script
$ mvn archetype:generate -DarchetypeGroupId=com.github.sergiomartins8 \
                         -DarchetypeArtifactId=ui-automation-bootstrap \
                         -DarchetypeVersion=1.5.0 \
                         -DgroupId=awesome.group.id \
                         -DartifactId=awesome-template \
                         -Dcheckstyle=true \
                         -Dreports=true
```

It's your project now. That easy! 🚀

Explore the [documentation](docs/documentation.md) and customize it for your needs.

## Changelog

Automatically generated by using [github-changes](https://github.com/lalitkapoor/github-changes).

Available [here](/docs/CHANGELOG.md).

## Contributing

Open source from the first commit ✨

Dive into ui-automation-bootstrap's [contribution guide](docs/CONTRIBUTING.md).

## Kuddos

Feel free to reach out on linkedin[@sergiomartins8](https://www.linkedin.com/in/sergiomartins8/) ‍🙌
