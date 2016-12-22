#!/bin/sh
CATALINA_HOME=/home/igor/Documents/apache-tomcat-8.0.33
export CATALINA_HOME

CUR_DIR=$(pwd)
WAR_FILE_DIR="$(pwd)/build"
CATALINA_BASE="$(pwd)/tomcat"
export CATALINA_BASE
echo "App dir: " $WAR_FILE_DIR

$(sh $CATALINA_HOME/bin/catalina.sh run)

