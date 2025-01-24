#!/usr/bin/env bash
mvn -q clean package
java -jar target/mazerunner.jar -i ./examples/small.maz.txt -p 1F1L1F1R1F1F1L1F1F1F1F1F1F1R1F1F1F1F1R1F1F1L1F1F1R1F1F1L1F