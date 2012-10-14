#!/bin/sh
echo "GHERKIN SALAD"
echo mvn clean integration-test -Dapply-execution-plan.planfiles=$1
mvn clean integration-test -Dapply-execution-plan.planfiles=$1

echo mvn daveayan:gherkin-salad-mojo:1.0:archive-execution-results
mvn daveayan:gherkin-salad-mojo:1.0:archive-execution-results