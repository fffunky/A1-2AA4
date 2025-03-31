#!/usr/bin/env bash
mvn clean package
java -jar target/mazerunner.jar -i ./examples/small.maz.txt -p F R F RR FF R FF R FF RR FFFF R FF R FFFF RR FF R FFFF R FF R FF RR FF L FFF L FFFFF R FF R FF RR FFFF R FF R FF RR FF R FF R FFFF R FF L FFF R FF L FF
