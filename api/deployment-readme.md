- DATABASE DEPLOYMENT:

      I. Use the URL given by the existing Dev to connect to the SQL database.
      II. Take snapshot of the database (preferrably .sql file) and then paste that file in the api folder under the name 'dump.sql'
      III. Regarding creation of S3 buckets, the structure is - BUCKET --> { id_of_store } --> images --> photos of store images
                                                                                           --> logo --> logo image of store


- DEPLOYMENT STRATEGY:

          I. If deploying using docker-compose, please go through the docker-compose.yml file and deploy directly

          II. If deploying through individual docker services:
            1. Deploy the Dockerfile1.txt first. this is the DB -- note down the url of the database
            2. Insert said URL into the application.properties file in the src/main/resources/application.properties location
            3. Change the SPRING_DATASOURCE_URL variable in Dockerfile with the same database url obtained in step 1
            4. finally deploy Dockerfile.
