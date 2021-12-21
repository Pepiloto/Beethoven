FROM jenkins/jenkins:lts-alpine

COPY . .

RUN jenkins-plugin-cli --plugin-file=./jenkins/plugins.yml