FROM gradle:jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# RUN gradle clean build || return 0
# COPY . .
RUN gradle clean build


FROM amazoncorretto:17-alpine3.18
EXPOSE 8080
ENV SPRING_DATASOURCE_URL: jdbc:mysql://host.docker.internal:3306/rewards?autoReconnect=true&useSSL=false
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]