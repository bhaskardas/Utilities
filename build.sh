#!/bin/sh

echo Starting Build Processs
echo Setting Target Directory
export TARGET_DIR=/home/bhaskar/Projects/Utilities/code/build
echo Setting Target Directory Successful
echo Setting Maven Local Repository
export MAVEN_LOCAL_REPOSITORY=file:/home/bhaskar/.m2/repository
echo Setting Maven Local Repository Successful
echo Setting Maven OPTS
export MAVEN_OPTS="-Xmx384m -XX:MaxPermSize=128m"
echo Setting Maven OPTS Successful
echo Setting Maven Central Repository
export MAVEN_CENTRAL_REPOSITORY=http://repo1.maven.org/maven2/
echo Setting Maven Central Repository Successful
echo Executing clean and build and package commands
$M2_HOME/bin/mvn -e "-Djdk_ver=1.6" "-Dorg.apache.maven.user-settings=settings.xml" "-Dmaven.test.skip=true" clean install

echo Executing clean and build and package commands successful
echo Done
