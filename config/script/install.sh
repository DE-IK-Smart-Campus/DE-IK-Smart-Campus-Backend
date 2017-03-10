#!/bin/sh

if [ "$TRAVIS_BRANCH" = "master" ]; then
	mvn clean install -Pmaster -Dwildfly.hostname=$MASTER_HOSTNAME -Dwildfly.username=$WILDFLY_MASTER_USER -Dwildfly.password=$WILDFLY_MASTER_PASSWORD -Dmysql.connection.url=$MASTER_MYSQL_URL -Dmysql.username=$MASTER_MYSQL_USER -Dmysql.password=$MASTER_MYSQL_PASSWORD
else
	if [ "$TRAVIS_BRANCH" = "test" ]; then
		mvn clean install -Ptest -Dwildfly.hostname=$TEST_HOSTNAME -Dwildfly.username=$WILDFLY_TEST_USER -Dwildfly.password=$WILDFLY_TEST_PASSWORD -Dmysql.connection.url=$TEST_MYSQL_URL -Dmysql.username=$TEST_MYSQL_USER -Dmysql.password=$TEST_MYSQL_PASSWORD
	else
		mvn clean install -Pwith-wildfly
	fi
fi
