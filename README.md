#Distributed Computing
## Prerequisites
* MySQL  
For this demo, we used a MySQL database. This can be installed anywhere but has to run and have a database with name roadtrip. 
In web\WEB-INF\glassfish-resources.xml you can change the server where MySQL is running, the port it is listening to, the database name, the MySQL user with access to the database and the URL.
* NetBeans EE  
We tested everything using NetBeans EE and GlassFish. This is the default configuration of NetBeans EE.

## Downloading
When you clone this repository, make sure you rename the folder to "RoadTrip". We use this name for all of our links.

## Installing
To install this project, just open it in NetBeans, and deploy it.

## Add test data
To populate our database, we use the populate.sql script found in the sqlScripts folder.

##Notes
Please note that this is just a demo version. 
When you can't get it to install, please restart NetBeans and try to redeploy the project.
Some errors may still exist.