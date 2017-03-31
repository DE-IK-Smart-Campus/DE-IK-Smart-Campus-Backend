# DE-IK SMART-CAMPUS

Build status: [![Build Status](https://travis-ci.org/DE-IK-Smart-Campus/DE-IK-Smart-Campus-Web.svg?branch=master)](https://travis-ci.org/DE-IK-Smart-Campus/DE-IK-Smart-Campus-Web)

Code quality: [![Codacy Badge](https://api.codacy.com/project/badge/Grade/731cfa78c7f84c0ea2c3e40b364ae4b6)](https://www.codacy.com/app/holi60/DE-IK-Smart-Campus-Web?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DE-IK-Smart-Campus/DE-IK-Smart-Campus-Web&amp;utm_campaign=Badge_Grade)

Release: [![GitHub release](https://img.shields.io/github/release/DE-IK-Smart-Campus/DE-IK-Smart-Campus-Web.svg)](https://github.com/DE-IK-Smart-Campus/DE-IK-Smart-Campus-Web/releases/latest)

## Configuration
First configure your settings.xml in your .m2 folder with this profile, you can find an example in config/maven folder.


    <profiles>
		    <profile>
			    <id>local</id>
			    <activation>
				    <activeByDefault>true</activeByDefault>
			    </activation>
			    <properties>
					<mysql.connection.url></mysql.connection.url>
					<mysql.username></mysql.username>
					<mysql.password></mysql.password>
					<smartcampus.ejabberd.api.host></smartcampus.ejabberd.api.host>
					<smartcampus.ejabberd.api.port></smartcampus.ejabberd.api.port>
					<smartcampus.ejabberd.api.endpoint></smartcampus.ejabberd.api.endpoint>
					<smartcampus.xmpp.mucservice></smartcampus.xmpp.mucservice>
					<smartcampus.xmpp.nodes></smartcampus.xmpp.nodes>
					<smartcampus.xmpp.host></smartcampus.xmpp.host>
					<smartcampus.xmpp.domain></smartcampus.xmpp.domain>
					<smartcampus.xmpp.tcp.port></smartcampus.xmpp.tcp.port>
					<smartcampus.xmpp.bosh.port></smartcampus.xmpp.bosh.port>
					<smartcampus.xmpp.service></smartcampus.xmpp.service>
					<smartcampus.default.user></smartcampus.default.user>
					<smartcampus.default.password></smartcampus.default.password>
					<ldap.host></ldap.host>
					<ldap.port></ldap.port>
					<ldap.basedn></ldap.basedn>
					<ldap.passwordattribute></ldap.passwordattribute>
					<ldap.userpattern></ldap.userpattern>
					<ldap.groupsearchbase></ldap.groupsearchbase>
					<ldap.groupfilter></ldap.groupfilter>
					<backend.host></backend.host>
					<backend.port></backend.port>
					<backend.context></backend.context>
					<neptun.grant.type></neptun.grant.type>
					<neptun.client.id></neptun.client.id>
					<neptun.client.secret.value></neptun.client.secret.value>
					<neptun.url></neptun.url>
					<neptun.token.endpoint></neptun.token.endpoint>
					<converse.bosh.service.url></converse.bosh.service.url>
					<converse.credentials.url></converse.credentials.url>
					<converse.mucdomain></converse.mucdomain>
			    </properties>
		    </profile>
	</profiles>
    <activeProfiles>
	    <activeProfile>local</activeProfile>
    </activeProfiles>



Building the project:

`mvn clean install`

Running from the parent:

`mvn -pl smart-campus-web spring-boot:run`

Running from the smart-campus-web module:

`mvn spring-boot:run`

Running as jar:

`java -jar [smart-campus-web]/target/jarFileName.jar`

With Wildfly 10:

Build the project with:

`mvn clean install -Pwith-wildfly`

Running from the parent:

`mvn -pl smart-campus-web wildfly:run`

Running from the smart-campus-web module:

`mvn wildfly:run`

With the run goal the server starts and deploys the .war.
With start goal you can start the server.
With deploy goal you can deploy the app and etc...

If you want to run it in debug you have to run wildfly with -Pdebug maven profile.


Test data:

|  Username  |  Password  |
|: -------  :|:  ------  :|
|  admin  |  admin  |
|  adamkai  |  password  |
|  nolbi  |  password  |
|  holikai  |  password  |
|  filtikai  |  password  |
|  palu  |  password  |
|  butikai  |  password  |
|  gabai  |  password  |

Running tests:

You must have Selenium webdriver and Protractor installed!

First you must start the webdriver-manager with `webdriver-manager start`.
 
Then in another console you must go to `/smart-campus-web/src/test/protractor` and run `protractor protractor.conf.js`.
