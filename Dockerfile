# Use an official MySQL runtime as a parent image
FROM mysql:8.0 as build

# Set the root password for MySQL
ENV MYSQL_ROOT_PASSWORD=MountKilimanjaro

# Set the MySQL database name
ENV MYSQL_DATABASE=rewards

# Copy the dump.sql file to the container's initialization directory
COPY api/dump.sql /docker-entrypoint-initdb.d/

# Expose the MySQL port
EXPOSE 3306

# Build the Spring Boot application
FROM gradle:jdk17-jammy AS buildd
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# RUN gradle clean build || return 0
# COPY . .
RUN gradle clean build



FROM amazoncorretto:17-alpine3.18
EXPOSE 8080
ENV SPRING_DATASOURCE_URL: jdbc:mysql://host.docker.internal:3306/rewards?autoReconnect=true&useSSL=false

# Set environment variables for Spring Boot application
# ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysqldb:$MYSQLDB_DOCKER_PORT/$MYSQLDB_DATABASE?useSSL=false&allowPublicKeyRetrieval=true
#ENV SPRING_DATASOURCE_USERNAME=root
#ENV SPRING_DATASOURCE_PASSWORD=MountKilimanjaro
#ENV SPRING_JPA_HIBERNATE_NAMING_IMPLICIT_STRATEGY=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
#ENV SPRING_JPA_HIBERNATE_NAMING_PHYSICAL_STRATEGY=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
#ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
#ENV SPRING_JPA_HIBERNATE_DDL_AUTO=none
 
RUN mkdir /app
COPY --from=buildd /home/gradle/src/build/libs/api/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]

