FROM gradle:jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build || return 0
COPY . .
RUN gradle clean build

FROM mysql:8.0
ENV MYSQL_ROOT_PASSWORD=MountKilimanjaro
ENV MYSQL_DATABASE=rewards
COPY RewardsDump030224.sql /docker-entrypoint-initdb.d/
EXPOSE 3306

FROM amazoncorretto:17-alpine3.18
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]