# Link project

This job create new jobs for the repository given as parameters.
The created job containerizes the repository's binary with a standard environment given by the base image.

The job takes 4 parameters:

1. The repository's name (GITHUB_NAME)

    Example: `Pepiloto/pushswap`

2. The name of the job created (DISPLAY_NAME)

    Best is to be precise.

3. The binary name (BINARY_NAME)

    Name of the binary created to be executed when the container is run. Example: `push_swap`

4. The image name (IMAGE_NAME)

    The name of the image created, precise and unique is better in case you want to run multiple times the container. Example: `pushswap`
