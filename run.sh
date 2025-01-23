#!/usr/bin/env bash
mvn -q clean package
java -jar target/mazerunner.jar ./examples/small.maz.txt