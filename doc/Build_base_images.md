# Build base images

Thoses jobs builds and push the base images on the defined registry (by default the registry is on port 5000).

The goal of the base image is to have an image common to all the child images for the differents languages, to avoid using too much space and to avoid the need of pulling the image for every container that need to be created.

To add a new base image:

1. Create a new folder named as the language needed in images, for example : `images/rust`
2. Create a Dockerfile named `Dockerfile.base` in the newly created folder
3. Put the basic configuration you want to have when you will need that image for the child images
4. Edit this line in `jenkins/job_dsl.groovy` by adding your language (only use lowercase letters and no spaces) : `def languages = [ "c", "your_new_language" ]`

Now if you start again your instance you should see in the `Base images` folder in Jenkins the new job.