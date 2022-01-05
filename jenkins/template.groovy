def languages = [ "c" ]

freeStyleJob("Projects/${DISPLAY_NAME}") {
    scm {
        git {
            extensions {
                cleanBeforeCheckout()
                cloneOptions {
                    depth(1)
                }
            }
            remote {
                github("${GITHUB_NAME}")
            }
        }
    }
    triggers {
        scm("* * * * *")
    }
    steps {
        for (language in languages) {
            steps {
                conditionalSteps {
                    condition {
                        fileExists("Dockerfile", BaseDir.WORKSPACE)
                    }
                    steps {
                        dockerBuildAndPublish {
                            dockerfileDirectory("Dockerfile")
                            dockerRegistryURL("http://localhost:5000")
                            repositoryName("${IMAGE_NAME}")
                            tag("${DISPLAY_NAME}")
                            forcePull(false)
                            createFingerprints(false)
                            forceTag(false)
                        }
                    }
                }
                conditionalSteps {
                    condition {
                        not {
                            fileExists("Dockerfile", BaseDir.WORKSPACE)
                        }
                    }
                    steps {
                        shell("python3 /jenkins/create_standalone.py " + language + " ${BINARY_NAME}")
                        dockerBuildAndPublish {
                            dockerfileDirectory("/images/" + language + "/Dockerfile.standalone")
                            dockerRegistryURL("http://localhost:5000")
                            repositoryName("${IMAGE_NAME}")
                            forcePull(false)
                            createFingerprints(false)
                            forceTag(false)
                        }
                    }
                }
            }
        }
    }
}