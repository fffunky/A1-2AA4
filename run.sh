#!/usr/bin/env bash
mvn -q clean package
java -jar target/mazerunner.jar -i ./examples/direct.maz.txt