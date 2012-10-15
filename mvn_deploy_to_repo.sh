cd gherkinsalad.core
mvn -DaltDeploymentRepository=repo::default::file:../../_mavenrepository/releases clean deploy -Dmaven.test.skip=true
cd ../gherkinsalad.jquery
mvn -DaltDeploymentRepository=repo::default::file:../../_mavenrepository/releases clean deploy -Dmaven.test.skip=true
cd ..
