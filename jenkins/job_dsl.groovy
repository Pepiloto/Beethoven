def languages = [ "c" ]

folder("Base images") {
    description("Contain all the base images")
}

for (language in languages) {
    freeStyleJob("Base images/" + language) {
        description("Build the base image of " + language)
        customWorkspace("/home/node1")
        steps {
            dockerBuildAndPublish {
                dockerfileDirectory("/images/" + language + "/Dockerfile.base")
                dockerRegistryURL("http://localhost:5000")
                repositoryName(language + "-base-image")
                forcePull(false)
                createFingerprints(false)
                forceTag(false)
            }
            shell("echo \"" + language + " base image built !\"")
        }
    }
}