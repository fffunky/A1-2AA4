#!/usr/bin/env bash
mvn clean package
java -jar target/mazerunner.jar -i ./examples/straight.maz.txt