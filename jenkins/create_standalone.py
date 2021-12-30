#!/usr/bin/env python3

import sys

with open("/jenkins/Dockerfile.template", "r") as file:
    fileData = file.read()

fileData = fileData.replace("FROM", "FROM localhost:5000/" + sys.argv[1] + "-base-image")

fileData = fileData.replace("CMD", "CMD [ \"./" + sys.argv[2] + "\" ]")

with open("/images/" + sys.argv[1] + "/Dockerfile.standalone", "w") as newFile:
    newFile.write(fileData)
