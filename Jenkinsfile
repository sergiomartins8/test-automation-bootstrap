#!groovy

/*
* This is a Jenkinsfile example using Jenkins on Kubernetes.
*
* Follow this article to have a similar environment:
* https://medium.com/@sergiomartins8/highly-scalable-jenkins-on-minikube-8cc289a31850
*
*/
String SONARQUBE_ADDRESS = "sonarqube-address-example.com"
String REMOTE_GRID_ADDRESS = "selenium-grid-address-example.com"

/*
* Jenkins slaves are built from a custom made docker image, which may be found at:
* https://github.com/sergiomartins8/jenkins-slave-base
*
* Feel free to use your own or another one that fits your needs.
*
*/
podTemplate(label: "jenkins-slave-base-pod", serviceAccount: "jenkins", containers: [
        containerTemplate(
                name: "base",
                image: "sergiomartins8/jenkins-slave-base:latest",
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
            stage("Build and Test") {
                parallel(
                        "Lint": {
                            sh "mvn -B validate"
                        },
                        "SonarQube Alanysis": {
                            sh "mvn -B sonar:sonar -Dsonar.host.url=${SONARQUBE_ADDRESS}"
                        },
                        "Compile": {
                            sh "mvn -B compile"
                        }
                )
            }
            stage("Testing") {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    sh """set -e; mvn -B test \
                        -Dselenide.remote=${REMOTE_GRID_ADDRESS} \
                        -Dlistener=io/company/utils/listeners/MockListener.java,io/company/utils/listeners/ExtentReportListener.java \
                        -Dselenide.browser=firefox"""
                }

                publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: "reports",
                    reportFiles: "ExtentReport.html",
                    reportName: "ExtentReport"
                ])
            }
        }
    }
}
