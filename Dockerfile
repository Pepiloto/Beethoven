FROM jenkins/jenkins:lts-alpine

COPY images images

COPY jenkins/jenkins.yml jenkins/jenkins.yml

COPY jenkins/plugins.yml jenkins/plugins.yml

COPY jenkins/job_dsl.groovy jenkins/job_dsl.groovy

COPY .env .env

COPY agent.jar agent.jar

COPY --from=docker:20.10.11 /usr/local/bin/docker /usr/bin/

ENV DOCKER_HOST unix:///var/run/docker.sock

USER root

RUN mkdir /home/node1

USER jenkins

RUN jenkins-plugin-cli --plugin-file=./jenkins/plugins.yml
