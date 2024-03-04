DEPLOYMENT STRATEGY:



I. If deploying using docker-compose, please go through the docker-compose.yml file and deploy directly


II. If deploying through individual docker services:
1. Deploy the Dockerfile1.txt first. this is the DB -- note down the url of the database
2. Insert said URL into the application.properties file in the src/main/resources/application.properties location
3. Change the SPRING_DATASOURCE_URL variable in Dockerfile with the same database url obtained in step 1
4. finally deploy Dockerfile.