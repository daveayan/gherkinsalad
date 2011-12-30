mvn -DaltDeploymentRepository=repo::default::file:../../_mavenrepository/releases clean deploy
cd ../../_mavenrepository/
git commit
git push
cd ../gherkin-salad/gherkin-salad