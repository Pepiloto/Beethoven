def languages = [ "c" ]

folder("Base images") {
    description("Contain all the base images")
}

folder("Projects") {
    description("Contains all the jobs created by Link-project")
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

freeStyleJob("Link-project") {
    customWorkspace("/")
    description("Link a project to a job that check if there is changes in the repo and if yes build and containerize the project")
    parameters {
        stringParam("GITHUB_NAME", null, "GitHub repository owner/repo_name (e.g.: \"Pepiloto/Pushswap\")")
        stringParam("DISPLAY_NAME", null, "Display name for the job (no spaces in the name)")
        stringParam("BINARY_NAME", "compiled-app", "Name of the binary to run")
        stringParam("IMAGE_NAME", "none", "Name of the image that will be built if there is a Dockerfile in the repo (no spaces in the name)")
    }
    steps {
        dsl(["jenkins/template.groovy"])
    }
}