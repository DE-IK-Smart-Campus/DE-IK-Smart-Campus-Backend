DE-IK SMART-CAMPUS [![Build Status](https://travis-ci.org/DE-IK-Smart-Campus/DE-IK-Smart-Campus-Web.svg?branch=master)](https://travis-ci.org/DE-IK-Smart-Campus/DE-IK-Smart-Campus-Web)

First configure your settings.xml in your .m2 folder with this profile:
"<profiles>
		<profile>
			<id>local</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<properties>
				<mysql.connection.url>jdbc:mysql://hostname:3306/smartcampus-db-schema-name</mysql.connection.url>
				<mysql.username>mysql-username</mysql.username>
				<mysql.password>mysql-password</mysql.password>
			</properties>
		</profile>
	</profiles>
	<activeProfiles>
		<activeProfile>local</activeProfile>
	</activeProfiles>"

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