#!/usr/bin/env bash
mvn -q clean package
java -jar target/mazerunner.jar -i ./examples/small.maz.txt -p FFFF