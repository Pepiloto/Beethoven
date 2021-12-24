FROM jenkins/jenkins:lts-alpine

COPY jenkins/jenkins.yml jenkins/jenkins.yml

COPY jenkins/plugins.yml jenkins/plugins.yml

COPY .env .env

COPY agent.jar agent.jar

RUN jenkins-plugin-cli --plugin-file=./jenkins/plugins.yml
