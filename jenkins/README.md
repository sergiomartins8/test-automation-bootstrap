## Jenkins 🤖
Use this example to get started. However, it might need some tailoring.

##### Snippet
```groovy
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
    
            (...)

        }
    }
}
```

> The example above uses Jenkins on Kubernetes. Follow this [article](https://medium.com/@sergiomartins8/highly-scalable-jenkins-on-minikube-8cc289a31850) to have a similar environment in no time!
>
> Furthermore, the source code for the base image is open source and available [here](https://github.com/sergiomartins8/jenkins-slave-base).
