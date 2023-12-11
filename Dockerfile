FROM openjdk:17

WORKDIR /build

ARG SECRET_KEY=secret
ARG DB_PASSWORD=password
ARG DB_USERNAME=postgres

ADD /target/TaskService.jar ./TaskService.jar

EXPOSE 8081

CMD java -jar UserService.jar